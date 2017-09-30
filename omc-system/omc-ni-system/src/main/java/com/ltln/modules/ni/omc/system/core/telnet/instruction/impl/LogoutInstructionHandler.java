package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class LogoutInstructionHandler implements InstructionHandler {

	@Override
	public void process(Operator user,
			ChannelHandlerContext channelHandlerContex, AbsNmsReq Cmd) {
		String response = String.format("Good bye~ %s", user.getUserName());
		user.setLogin(false);
		Logger.info(response);
		channelHandlerContex.writeAndFlush(response+Constants.END_TAG);
		channelHandlerContex.close();
	}

}
