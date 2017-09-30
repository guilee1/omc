package com.ltln.modules.ni.omc.system.core.telnet;

import java.nio.charset.Charset;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

import com.ltln.modules.ni.omc.system.core.alarm.in.HeartBeatIdleChkHandler;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.LineWrapStringEncoder;
import com.ltln.modules.ni.omc.system.util.Constants;

public class TelnetServerInitializer extends ChannelInitializer<SocketChannel> {

	public final static String TELNET_SERVER_HANDLER = "telnet_server_handler";
	public final static String TELNET_IDLE_HANDLER = "telnet_idle_handler";
	
	private static final StringDecoder DECODER = new StringDecoder(Charset.forName(Constants.ENCODING));
	private static final StringEncoder ENCODER = new LineWrapStringEncoder();

	private static final TelnetServerHandler SERVER_HANDLER = SelfBeanFactoryAware.getBean("telnetServerHandler");

	private final SslContext sslCtx;

	public TelnetServerInitializer(SslContext sslCtx) {
		this.sslCtx = sslCtx;
	}

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		if (sslCtx != null) {
			pipeline.addLast(sslCtx.newHandler(ch.alloc()));
		}

		// Add the text line codec combination first,
		pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters
				.lineDelimiter()));
		// the encoder and decoder are static as these are sharable
		pipeline.addLast(DECODER);
		pipeline.addLast(ENCODER);
		pipeline.addLast(TELNET_IDLE_HANDLER, new HeartBeatIdleChkHandler(Constants.TELNET_IDLE_TIME_SECONDS));

		// and then business logic.
		pipeline.addLast(TELNET_SERVER_HANDLER,SERVER_HANDLER);
	}
}
