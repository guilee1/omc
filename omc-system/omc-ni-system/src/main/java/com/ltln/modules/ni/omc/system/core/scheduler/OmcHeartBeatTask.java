package com.ltln.modules.ni.omc.system.core.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IMgrInf;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;

@Component
public class OmcHeartBeatTask {

	@Autowired
	ILocator locator;
	
//	@Scheduled(cron="0/10 * *  * * ? ")
	public void executeJob(){
		IMgrInf mgrInf = locator.getInstances(IMgrInf.class);
		try{
			mgrInf.heartBeat();
		}catch (Exception e) {
			Logger.error("heartbeat invoke error,time out,try it again!", e);
		}
	}
}
