package com.ltln.modules.ni.omc.system.core.alarm.in.process;

import io.netty.channel.ChannelHandlerContext;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.AlarmServerInitializer;
import com.ltln.modules.ni.omc.system.core.alarm.ICommandHandler;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.AckHeartBeat;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqHeartBeat;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.ICmdParser;
import com.ltln.modules.ni.omc.system.core.alarm.in.HeartBeatIdleChkHandler;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsgBuilder;

@Component
public class OmcHeartBeatMsgProcessHandler implements ICommandHandler {

	@Resource(name="heartBeatParser")
	ICmdParser parser;
	
	@Autowired
	IAlmContext context;
	
	@Override
	public void process(ChannelHandlerContext channelHandlerContex, NmsMsg cmd) {
		if(!context.isLogin(channelHandlerContex.channel().id())){
			Logger.info(String.format("receive a heartbeat msg from %s,but,not login user~", channelHandlerContex.channel().remoteAddress().toString()));
			return;
		}
		HeartBeatIdleChkHandler handler = (HeartBeatIdleChkHandler)channelHandlerContex.pipeline().get(AlarmServerInitializer.HEARTBEAT_IDLE_HANDLER);
		handler.setLastHeartbeatTime(System.nanoTime());
		ReqHeartBeat request = (ReqHeartBeat)parser.decode(cmd.getBody());
		if(!request.badFormat()){
			Logger.info(String.format("receive a heartbeat msg from %s", channelHandlerContex.channel().remoteAddress().toString()));
			AckHeartBeat response = new AckHeartBeat();
			response.setReqID(request.getReqID());
			channelHandlerContex.writeAndFlush(NmsMsgBuilder.buildAckHeartBeatMsg(parser.encode(response)));
		}
	}

}
