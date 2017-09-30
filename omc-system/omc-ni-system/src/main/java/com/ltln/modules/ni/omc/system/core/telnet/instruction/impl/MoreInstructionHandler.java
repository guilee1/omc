package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.client.ITelnetClient;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class MoreInstructionHandler implements InstructionHandler {

	@Override
	public void process(Operator user,ChannelHandlerContext channelHandlerContex, AbsNmsReq Cmd) {
		String response = StringUtils.EMPTY;
		if(user.isLogin()){
			ITelnetClient telnetClient = user.getTelClient();
			boolean sendResult = telnetClient.sendCommand(" ");
			if(!sendResult)
				response = String.format("please connect a device first!");
		}else{
			response = String.format("please login first!");
		}
		if(StringUtils.isNotEmpty(response)){
			Logger.info(response);
			channelHandlerContex.writeAndFlush(response+Constants.END_TAG);
		}
	}

}
