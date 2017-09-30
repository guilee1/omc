package com.ltln.modules.ni.omc.system.core.alarm;

import io.netty.channel.ChannelHandlerContext;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.EMsgType;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;

@Component
public class CommandDispatcher {

	public void dispatch(ChannelHandlerContext paramChannelHandlerContext,NmsMsg msgObj) {
		ICommandHandler handler = getHandler(msgObj.getMsgType());
		if(handler == null){
			Logger.info(String.format("can not find handler [%s] to process request?", msgObj.getMsgType()));
			return;
		}
		handler.process(paramChannelHandlerContext,msgObj);
	}

	private ICommandHandler getHandler(EMsgType type) {
		return SelfBeanFactoryAware.getBean(type.handler);
	}


}
