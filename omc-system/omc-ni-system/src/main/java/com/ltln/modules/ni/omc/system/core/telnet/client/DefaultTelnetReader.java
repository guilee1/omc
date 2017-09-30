package com.ltln.modules.ni.omc.system.core.telnet.client;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.OperationNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

public class DefaultTelnetReader implements ITelnetReader {

	private static final String MORE_TAG = "  ----- More -----  ";
	ITelnetClient telnetClient;
	Operator user;
	AbsNmsReq request;
	ChannelHandlerContext context;
	
	StringBuilder sbResponse = new StringBuilder();
	boolean waitResponse = false;
	boolean waitFirstResponse = false;
	public DefaultTelnetReader(Operator user2,
			ChannelHandlerContext channelHandlerContex, AbsNmsReq cmd,
			ITelnetClient telnetClient2) {
		this.user = user2;
		this.context = channelHandlerContex;
		this.request = cmd;
		this.telnetClient = telnetClient2;
	}

	@Override
	public void readComplete(String response) {
		OperationNmsReq operation = (OperationNmsReq)request;
		String object = operation.getObject();
		if(StringUtils.contains(response, "Unknown command")){
			Logger.info(response);
			context.writeAndFlush(response+Constants.END_TAG);
			telnetClient.close(true);
		}else if(StringUtils.endsWith(response, "Username:")){
			telnetClient.sendCommand(user.getUserName());
		}else if(StringUtils.endsWith(response, "Password:")){
			telnetClient.sendCommand(user.getPassword());
		}else if(StringUtils.endsWith(response, ">") && isConfigCmd(object)){
			telnetClient.sendCommand("sys");
		}else if(StringUtils.endsWith(response, MORE_TAG)){
			if(user.isMore()){
				sbResponse.append(response.replace(MORE_TAG, ""));
				telnetClient.sendCommand(" \r\n");
			}else{
				context.writeAndFlush(response+Constants.END_TAG);
			}
		}else if(StringUtils.contains(response, "Continue?[Y/N]:")){
			telnetClient.sendCommand("Y");
		}else if(StringUtils.endsWith(response, "]") || (StringUtils.endsWith(response, ">") && !isConfigCmd(object))){
			if(waitResponse){
				if(sbResponse.length()>0){
					sbResponse.append(response.replace(MORE_TAG, ""));
					context.writeAndFlush(sbResponse.toString()+Constants.END_TAG);
				}
				else
					context.writeAndFlush(response+Constants.END_TAG);
				telnetClient.close(waitFirstResponse);
			}else if(waitFirstResponse){
				telnetClient.sendCommand(operation.getCmd());//here to execute config cmd
				waitResponse = true;
			}else{
				if(isConfigCmd(object)){
					if("more".equalsIgnoreCase(object))
						telnetClient.sendCommand("user-interface vty 1-3");
					else
						telnetClient.sendCommand("interface "+ object);
					waitFirstResponse = true;
				}else{
					if("reboot".equalsIgnoreCase(operation.getCmd()))
						telnetClient.sendCommand(operation.getCmd());
					else
						telnetClient.sendCommand(operation.getCmd()+" " + operation.getObject());
					waitResponse = true;
				}
			}
		}else{
			Logger.info("receive no need msg~ & quit");
			telnetClient.close(true);
		}
	}

	private boolean isConfigCmd(String object) {
		return object.indexOf("/")!=-1 || "more".equalsIgnoreCase(object);
	}

}
