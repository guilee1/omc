package com.ltln.modules.ni.omc.system.core.telnet.instruction;

import io.netty.channel.ChannelHandlerContext;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public interface InstructionHandler {

	void process(Operator user,ChannelHandlerContext channelHandlerContex,AbsNmsReq Cmd);
}
