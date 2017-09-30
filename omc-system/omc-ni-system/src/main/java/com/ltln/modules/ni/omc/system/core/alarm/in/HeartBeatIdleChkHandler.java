package com.ltln.modules.ni.omc.system.core.alarm.in;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.ltln.modules.ni.omc.system.core.log.Logger;

public class HeartBeatIdleChkHandler extends ChannelInboundHandlerAdapter {

	private long hbIdleTimeNanos;
	private ScheduledFuture<?> idleTimeoutFuture;
	private volatile long lastHeartbeatTime;
	

	public HeartBeatIdleChkHandler(int hbIdleTimeSeconds) {
		if (hbIdleTimeSeconds <= 0L)
			this.hbIdleTimeNanos = 0L;
		else
			this.hbIdleTimeNanos = TimeUnit.SECONDS.toNanos(hbIdleTimeSeconds);
	}
	
	private void initialize(ChannelHandlerContext ctx) {
		if (this.hbIdleTimeNanos > 0L)
			this.idleTimeoutFuture = schedule(ctx, new HeartbeatIdleTask(ctx),this.hbIdleTimeNanos, TimeUnit.NANOSECONDS);
	}
	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		initialize(ctx);
		super.channelActive(ctx);
	}

	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		destroy(ctx);
		super.channelInactive(ctx);
	}
	
	private void destroy(ChannelHandlerContext ctx) {
		if (this.idleTimeoutFuture != null) {
			this.idleTimeoutFuture.cancel(false);
			this.idleTimeoutFuture = null;
		}
		Logger.info(String.format("connection %s stop heartbeat chk",ctx.channel().remoteAddress().toString()));
	}

	ScheduledFuture<?> schedule(ChannelHandlerContext ctx, Runnable task,long delay, TimeUnit unit) {
		Logger.info(String.format("connection %s start heartbeat chk next delay is %s",ctx.channel().remoteAddress().toString(),TimeUnit.SECONDS.convert(delay, unit)));
		return ctx.executor().schedule(task, delay, unit);
	}
	
	public long getLastHeartbeatTime() {
		return lastHeartbeatTime;
	}

	public void setLastHeartbeatTime(long lastHeartbeatTime) {
		this.lastHeartbeatTime = lastHeartbeatTime;
	}
	
	private final  class HeartbeatIdleTask implements Runnable {
		private final ChannelHandlerContext ctx;

		HeartbeatIdleTask(ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		public void run() {
			if (!(this.ctx.channel().isOpen())) {
				return;
			}
			chk();
		}

		long ticksInNanos() {
			return System.nanoTime();
		}
		
		 void chk(){
			 long nextDelay = hbIdleTimeNanos;
			 nextDelay -= ticksInNanos() - lastHeartbeatTime;
			 if(nextDelay <= 0){
				 Logger.info(String.format("connection %s exceed max idle time %s seconds from the time of lastheartbeat updated till now. so disconnect it!",this.ctx.channel().remoteAddress().toString(), TimeUnit.SECONDS.convert(hbIdleTimeNanos,TimeUnit.NANOSECONDS)));
				 this.ctx.close();
			 }else{
				 schedule(ctx, this, nextDelay, TimeUnit.NANOSECONDS);
			 }
		 }
	}

	public void setHbIdleTimeNanos(long hbIdleTimeNanos) {
		this.hbIdleTimeNanos = hbIdleTimeNanos;
	}
}
