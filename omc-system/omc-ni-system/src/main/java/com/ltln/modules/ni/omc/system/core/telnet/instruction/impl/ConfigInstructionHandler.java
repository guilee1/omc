package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.in.HeartBeatIdleChkHandler;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.TelnetServerInitializer;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.ConfigNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.InstructionHandler;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class ConfigInstructionHandler implements InstructionHandler {

	@Override
	public void process(Operator user,
			ChannelHandlerContext channelHandlerContex, AbsNmsReq Cmd) {
		ConfigNmsReq request = (ConfigNmsReq)Cmd;
		String response = StringUtils.EMPTY;
		if(request.isShow()){
			response = String.format("user #%s# [more] status is: %s,[timeout] is: %s ", user.getUserName(),user.isMore(),user.getTimeOut());
		}else{
			if(request.isMoreCfg())
				user.setMore(request.isMore());
			else{
				user.setTimeOut(request.getTimeout());
				HeartBeatIdleChkHandler chkHandler = (HeartBeatIdleChkHandler)channelHandlerContex.pipeline().get(TelnetServerInitializer.TELNET_IDLE_HANDLER);
				chkHandler.setHbIdleTimeNanos(TimeUnit.SECONDS.toNanos(user.getTimeOut()));
			}
			response = String.format("user #%s# config success!", user.getUserName());
		}
		Logger.info(response);
		channelHandlerContex.writeAndFlush(response+Constants.END_TAG);
	}


}
