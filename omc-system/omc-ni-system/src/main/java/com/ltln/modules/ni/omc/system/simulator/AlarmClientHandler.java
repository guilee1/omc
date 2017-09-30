package com.ltln.modules.ni.omc.system.simulator;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.EMsgType;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;

public class AlarmClientHandler extends SimpleChannelInboundHandler<NmsMsg> {

	int seqNo = 0;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NmsMsg loginMsg = new NmsMsg(EMsgType.reqLoginAlarm, "reqLoginAlarm;user=admin;key=admin;type=msg");
		ctx.writeAndFlush(loginMsg);
	}

	@Override
	protected void channelRead0(
			ChannelHandlerContext paramChannelHandlerContext, NmsMsg msgObj)
			throws Exception {
		Logger.info(String.format(
				"[%s] receive a nms message type is: %s,body is %s",paramChannelHandlerContext.channel().localAddress().toString(),
				msgObj.getMsgType().name, msgObj.getBody()));
		int alarmSeq = parseSeq(msgObj.getBody());
		if(seqNo==0)
			seqNo = alarmSeq;
		else{
			if(alarmSeq!= ++seqNo){
				Logger.info("here error~ alarmSeq should be "+seqNo +"but actrul is "+alarmSeq);
				seqNo = alarmSeq;
			}
		}
	}

	private int parseSeq(String body) {
		String splite[] = body.split("\"alarmSeq\":");
		if(splite.length!=2)return 0;
		String no = splite[1].substring(0, splite[1].indexOf(","));
		return Integer.parseInt(no);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		Logger.error("catch error during connection", cause);
		ctx.close();
	}

}
