package com.ltln.modules.ni.omc.system.core.alarm;

import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;

import io.netty.channel.ChannelHandlerContext;

public interface ICommandHandler {

	void process(ChannelHandlerContext channelHandlerContex,NmsMsg cmd);
}
