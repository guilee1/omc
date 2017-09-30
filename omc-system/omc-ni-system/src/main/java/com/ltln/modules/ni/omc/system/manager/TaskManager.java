package com.ltln.modules.ni.omc.system.manager;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.cache.ActiveAlmCache;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.scheduler.AlarmSeqLogPersistTask;
import com.ltln.modules.ni.omc.system.nbi.alarm.AlarmServer;
import com.ltln.modules.ni.omc.system.nbi.telnet.TelnetServer;
import com.ltln.modules.ni.omc.system.util.Constants;

public class TaskManager implements ISystem {

	
	@Override
	public boolean startUp() {
		ThreadPoolTaskExecutor executor = SelfBeanFactoryAware.getTaskThreadPool();
		executor.execute(new AlarmSeqLogPersistTask());
		Future<?> fAlmServer = executor.submit(new AlarmServer());
		boolean finalResult = true;
		try {
			fAlmServer.get(1000, TimeUnit.MILLISECONDS);
		}
		catch (TimeoutException e) {
		} catch (Exception e) {
			finalResult = false;
		}
		Future<?> fInstructionServer = executor.submit(new TelnetServer());
		try {
			fInstructionServer.get(1000, TimeUnit.MILLISECONDS);
		}
		catch (TimeoutException e) {
		} catch (Exception e) {
			finalResult = false;
		}
		ActiveAlmCache cache = SelfBeanFactoryAware.getBean("activeAlmCache");
		if(Constants.ALM_ACTIVE_ALM_QUERY_MODEL){
			Logger.info("ALM_ACTIVE_ALM_QUERY_MODEL is true,so start query schedule~");
			executor.submit(cache);
		}
		return finalResult;
	}

	@Override
	public boolean shutDown() {
		SelfBeanFactoryAware.getTaskThreadPool().shutdown();
		SelfBeanFactoryAware.getTelnetReaderThreadPool().shutdown();
		return true;
	}

	@Override
	public String getName() {
		return "Task Service[almSenderTask,almPersistTask,AlmServerTask,InstructionServerTask]";
	}

}
