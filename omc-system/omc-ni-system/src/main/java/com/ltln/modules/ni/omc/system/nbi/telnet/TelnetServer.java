package com.ltln.modules.ni.omc.system.nbi.telnet;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.TelnetServerInitializer;
import com.ltln.modules.ni.omc.system.util.Constants;

public final class TelnetServer implements Runnable{

	static final boolean SSL = Constants.INSTRUCTION_SSL;
	static final int PORT = SSL?Constants.INSTRUCTION_SSL_PORT:Constants.INSTRUCTION_PORT;

	public  void startInstructionServer()  {
		// Configure SSL.
		SslContext sslCtx = null;
		if (SSL) {
			SelfSignedCertificate ssc;
			try {
				ssc = new SelfSignedCertificate();
				sslCtx = SslContextBuilder.forServer(ssc.certificate(),ssc.privateKey()).build();
			}catch (CertificateException e) {
				Logger.error("CertificateException in InstructionServer", e);
			}catch (SSLException e) {
				Logger.error("SSLException in InstructionServer", e);
			}
		} else {
			sslCtx = null;
		}

		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new TelnetServerInitializer(sslCtx));

			b.bind(PORT).sync().channel().closeFuture().sync();
		} catch (InterruptedException e) {
			Logger.error("InterruptedException in InstructionServer", e);
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void run() {
		this.startInstructionServer();
	}
}
