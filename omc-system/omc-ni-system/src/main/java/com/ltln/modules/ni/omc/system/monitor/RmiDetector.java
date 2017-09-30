package com.ltln.modules.ni.omc.system.monitor;

import com.ltln.modules.ni.omc.core.IMgrInf;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;

public class RmiDetector implements IDetect {

	ILocator locator;
	IMgrInf mgrInf;
	
	@Override
	public boolean detect() {
		locator = SelfBeanFactoryAware.getBean("serviceLocator");
		mgrInf = locator.getInstances(IMgrInf.class);
		try{
			mgrInf.heartBeat();
		}catch (Exception e) {
			return false;
		}
		return true;
	}

}
