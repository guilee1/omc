package com.ltln.modules.ni.omc.system.manager;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;

public class JmsManager implements ISystem {

	@Override
	public boolean startUp() {
		try{
			ActiveMQConnectionFactory connFactory = SelfBeanFactoryAware.getBean("jmsConnectionFactory");
			connFactory.setBrokerURL("nio://"+Constants.RMI_IP_ADDR+":61616");
			DefaultMessageListenerContainer jmsContainer = SelfBeanFactoryAware.getBean("alarmListenerContainer");
			jmsContainer.start();
		}catch (Exception e) {
			Logger.error("error occu in JmsManager startUp", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean shutDown() {
		try{
			DefaultMessageListenerContainer jmsContainer = SelfBeanFactoryAware.getBean("alarmListenerContainer");
			jmsContainer.shutdown();
		}catch (Exception e) {
			Logger.error("error occu in JmsManager shutDown", e);
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		return "JMS Service";
	}

}
