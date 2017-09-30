package com.ltln.modules.ni.omc.system.core.scheduler;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class DefaultScheduler implements IScheduler{

	EventExecutorGroup defaultScheduler;
	
	public DefaultScheduler() {
		defaultScheduler = new DefaultEventExecutorGroup(Constants.SCHEDULER_THREAD_NUMBER);
	}

	@Override
	public void schedule(Runnable paramRunnable, long paramLong,
			TimeUnit paramTimeUnit) {
		defaultScheduler.schedule(paramRunnable, paramLong, paramTimeUnit);
	}

	@Override
	public void scheduleAtFixedRate(Runnable paramRunnable, long paramLong1,long paramLong2, TimeUnit paramTimeUnit) {
		defaultScheduler.scheduleAtFixedRate(paramRunnable, paramLong1, paramLong2, paramTimeUnit);
	}

	@Override
	public void close() {
		defaultScheduler.shutdownGracefully();
	}

	@Override
	public <T> Future<T> submit(Callable<T> syncAlarmFileTask) {
		return defaultScheduler.submit(syncAlarmFileTask);
	}
	
}
