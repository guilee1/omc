package com.ltln.modules.ni.omc.system.monitor;

import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;


public class JmsDetector implements IDetect {


	@Override
	public boolean detect() {
		DefaultMessageListenerContainer jmsContainer = SelfBeanFactoryAware.getBean("alarmListenerContainer");
		return jmsContainer.isRunning();
	}

}
