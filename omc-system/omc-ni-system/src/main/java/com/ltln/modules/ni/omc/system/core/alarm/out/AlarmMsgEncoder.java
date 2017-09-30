package com.ltln.modules.ni.omc.system.core.alarm.out;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;

public class AlarmMsgEncoder extends MessageToByteEncoder<NmsMsg> {

	@Override
	protected void encode(ChannelHandlerContext paramChannelHandlerContext,
			NmsMsg msgObj, ByteBuf paramByteBuf) throws Exception {
		paramByteBuf.writeBytes(msgObj.toBytes());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
        Logger.error("catch error during writing", cause);
        ctx.close();
	}
}
