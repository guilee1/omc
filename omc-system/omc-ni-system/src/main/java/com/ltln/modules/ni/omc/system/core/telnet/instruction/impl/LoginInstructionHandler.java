package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.listener.ConnectionModel;
import com.ltln.modules.ni.omc.system.core.listener.EConnectionType;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.TelnetServerHandler;
import com.ltln.modules.ni.omc.system.core.telnet.TelnetServerInitializer;
import com.ltln.modules.ni.omc.system.core.telnet.client.ITelnetClient;
import com.ltln.modules.ni.omc.system.core.telnet.client.TinyTelnetClient;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.LoginNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class LoginInstructionHandler implements InstructionHandler {

	@SuppressWarnings("deprecation")
	@Override
	public void process(final Operator user,final ChannelHandlerContext channelHandlerContex, AbsNmsReq Cmd) {
		LoginNmsReq request = (LoginNmsReq)Cmd;
		String response = StringUtils.EMPTY;
		user.setUserName(request.getUserName());
		user.setPassword(request.getPassword());
		if(user.isLogin()){
			response = String.format("user has login at time: %s", user.getLoginTime());
		}else{
			ITelnetClient telnetClient = new TinyTelnetClient();
			user.setTelClient(telnetClient);
			if(StringUtils.equals(user.getUserName(), Constants.INSTRUCTION_DEFAULT_DEVICE_USERNAME) &&
					StringUtils.equals(user.getPassword(), Constants.INSTRUCTION_DEFAULT_DEVICE_PWD)){
				user.setLogin(true);
				user.setLoginTime(new Date().toGMTString());
				response = "login success!";
				final TelnetServerHandler handler = (TelnetServerHandler)channelHandlerContex.pipeline().get(TelnetServerInitializer.TELNET_SERVER_HANDLER);
				if(handler.getListener()!=null){
					SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
						@Override
						public void run() {
							handler.getListener().ConnectionLogin(new ConnectionModel(channelHandlerContex.channel().remoteAddress().toString(), EConnectionType.INSTRU, user.getUserName()));
						}
					});
				}
			}else{
				response = "error username or pwd!";
			}
		}
		if(StringUtils.isNotEmpty(response)){
			Logger.info(response);
			channelHandlerContex.writeAndFlush(response+Constants.END_TAG);
		}
	}


}
