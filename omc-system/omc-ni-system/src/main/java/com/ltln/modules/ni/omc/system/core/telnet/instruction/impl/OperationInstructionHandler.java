package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.client.DefaultTelnetReader;
import com.ltln.modules.ni.omc.system.core.telnet.client.ITelnetClient;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.OperationNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class OperationInstructionHandler implements InstructionHandler {

	@Override
	public void process(Operator user,ChannelHandlerContext channelHandlerContex, AbsNmsReq Cmd) {
		OperationNmsReq request = (OperationNmsReq)Cmd;
		String response = StringUtils.EMPTY;
		if(user.isLogin()){
			ITelnetClient telnetClient = user.getTelClient();
//			if(telnetClient.isConnected()){
//				response = "last command is running!Only [more] command can be executed!";
//			}else{
				try{
					telnetClient.registerCallBack(new DefaultTelnetReader(user,channelHandlerContex,Cmd,telnetClient));
					telnetClient.connect(request.getIp());
				}catch (Exception e) {
					response = String.format("connect fail!reason is %s"+Constants.END_TAG, e.getMessage());
					telnetClient.close(true);
				}
//			}
		}else{
			response = String.format("please login first!");
		}
		if(StringUtils.isNotEmpty(response)){
			Logger.info(response);
			channelHandlerContex.writeAndFlush(response);
		}
	}


}
