package com.ltln.modules.ni.omc.system.core.scheduler;

import java.util.List;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.cache.IAlmCache;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;

public class AlarmSeqLogPersistTask implements Runnable{
	
	public void execute(){
		IAlmCache cache = SelfBeanFactoryAware.getBean("alarmCache");
		IAlmContext clients = SelfBeanFactoryAware.getBean("alarmClientContext");
		IOmcDao dao = SelfBeanFactoryAware.getDao();
		for(;;){
			List<AlarmVo> vo = cache.takeAlarm();
			if(!vo.isEmpty()){
				try{
					dao.insertAlarmVo(vo);
				}catch (Exception e) {
					Logger.error(e);
				}
			}
			List<AlarmLog> logVo = clients.takeAllLog();
			if(!logVo.isEmpty()){
				try{
					dao.insertAlarmLog(logVo);
				}catch (Exception e) {
					Logger.error(e);
				}
			}
			try {
				Thread.sleep(Constants.ALM_SCHEDULER_ALMSEQ_PERIOD);
			} catch (InterruptedException e) {
				Logger.error("sleep interrupted ~ in AlarmSeqLogPersistTask",e);
				break;
			}
			
		}
	}

	@Override
	public void run() {
		this.execute();
	}
	
}
