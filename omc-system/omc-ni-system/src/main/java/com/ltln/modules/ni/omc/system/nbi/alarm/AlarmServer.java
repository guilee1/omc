package com.ltln.modules.ni.omc.system.nbi.alarm;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.AlarmServerInitializer;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class AlarmServer implements Runnable {

	/*
     * High/low write watermarks, in KiB.
     */
    private static final int DEFAULT_WRITE_HIGH_WATERMARK = 640;
    private static final int DEFAULT_WRITE_LOW_WATERMARK = 320;
    /*
     * Write spin count. This tells netty to immediately retry a non-blocking
     * write this many times before moving on to selecting.
     */
    private static final int DEFAULT_WRITE_SPIN_COUNT = 16;
    
	@SuppressWarnings("deprecation")
	public void startAlmServer() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(1);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY , true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, DEFAULT_WRITE_HIGH_WATERMARK * 1024)
                    .childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, DEFAULT_WRITE_LOW_WATERMARK * 1024)
                    .childOption(ChannelOption.WRITE_SPIN_COUNT, DEFAULT_WRITE_SPIN_COUNT)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new AlarmServerInitializer());
			try {
				b.bind(Constants.ALARM_PORT).sync().channel().closeFuture().sync();
			} catch (InterruptedException e) {
				Logger.error("InterruptedException in AlmServer", e);
			}
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void run() {
		this.startAlmServer();
	}

}
