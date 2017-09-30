package com.ltln.modules.ni.omc.system.core.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;

@Component
public class AlarmLogPersistorTask implements Runnable {

	@Autowired
	IAlmContext clients;
	
	@Autowired
	IOmcDao dao;
	
	
	public void execute(){
		List<AlarmLog> vo = clients.takeAllLog();
		if(!vo.isEmpty())
			dao.insertAlarmLog(vo);
	}


	@Override
	public void run() {
		this.execute();
	}

}
