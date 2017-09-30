package com.ltln.modules.ni.omc.system.core.alarm.in.process;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.ICommandHandler;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
@Component
public class OmcCloseConnProcessHandler implements ICommandHandler {

	@Override
	public void process(ChannelHandlerContext channelHandlerContex, NmsMsg cmd) {
		channelHandlerContex.close();
	}

}
