package com.ltln.modules.ni.omc.system.core.telnet.instruction;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.ICmdParser;

import io.netty.channel.ChannelHandlerContext;

public interface IPipeline {

	void execute(Operator user,ChannelHandlerContext ctx,String fullCmd);
	
	ICmdParser getParser(String strItem);
	
	InstructionHandler getHandler(EInsCmdType type);
}
