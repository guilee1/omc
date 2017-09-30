package com.ltln.modules.ni.omc.system.core.telnet.instruction;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

import com.ltln.modules.ni.omc.system.util.Constants;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.string.StringEncoder;
@Sharable
public class LineWrapStringEncoder extends StringEncoder {

	@Override
	protected void encode(ChannelHandlerContext ctx, CharSequence msg,
			List<Object> out) throws Exception {
		super.encode(ctx, msg, out);
		out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap("\r\n"),
				Charset.forName(Constants.ENCODING)));
	}
}
