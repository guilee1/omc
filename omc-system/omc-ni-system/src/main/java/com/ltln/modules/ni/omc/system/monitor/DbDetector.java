package com.ltln.modules.ni.omc.system.monitor;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;

public class DbDetector implements IDetect {

	IOmcDao dao;
	
	@Override
	public boolean detect() {
		dao = SelfBeanFactoryAware.getDao();
		try{
			dao.heartBeat();
		}catch (Exception e) {
			return false;
		}
		return true;
	}
}
