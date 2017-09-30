package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class HelpInstructionHandler implements InstructionHandler {

	@Override
	public void process(Operator user,
			ChannelHandlerContext channelHandlerContex, AbsNmsReq Cmd) {
		StringBuilder sbResponse = new StringBuilder();
		sbResponse.append("All support command below:").append("\r\n").
		append("-----------------------------------------------------").append("\r\n").
		append("[help] --show all support command.").append("\r\n").
		append("[login user=#{user} pwd=#{pwd}] --login use specified name and pwd.").append("\r\n").
		append("[logout] --user exit.").append("\r\n").
//		append("[more] --show more contents if exists.").append("\r\n").
//		append("[config more #{open|close}] --if paged the all responses when received lots of message.").append("\r\n").
		append("[config timeout #{time}] --set the idel timeout.(s)").append("\r\n").append(Constants.END_TAG);
		channelHandlerContex.writeAndFlush(sbResponse.toString());
	}


}
