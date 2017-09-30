package com.ltln.modules.ni.omc.system.core.scheduler;

import io.netty.util.concurrent.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public interface IScheduler {

	void schedule(Runnable paramRunnable,long paramLong, TimeUnit paramTimeUnit);
	
	void scheduleAtFixedRate(Runnable paramRunnable, long paramLong1, long paramLong2,TimeUnit paramTimeUnit);

	void close();

	<T> Future<T> submit(Callable<T> syncAlarmFileTask);
}
