package com.ltln.modules.ni.omc.system.sbi.rmi;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.annotation.Facade;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.scanner.ClassScaner;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class RmiProxy implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	ILocator locator;
	
	void scanRmiProxy(){
		Set<Class<?>> clazzes = new ClassScaner().scan(Constants.INF_PKG_NAME, Facade.class);
		Logger.info(String.format("#RmiProxy# ClassScanner scan %s,result is %d", Constants.INF_PKG_NAME,clazzes.size()));
		CollectCallerInterface(clazzes);
	}
	
	void CollectCallerInterface(Set<Class<?>> clazzes) {
		for (Class<?> clazz : clazzes) {
			RmiProxyFactoryBean rmiProxy = new RmiProxyFactoryBean();
			rmiProxy.setLookupStubOnStartup(false);
			rmiProxy.setRefreshStubOnConnectFailure(true);
			rmiProxy.setServiceInterface(clazz);
			String url = "rmi://" + Constants.RMI_IP_ADDR + "/" + clazz.getAnnotation(Facade.class).serviceName();
			rmiProxy.setServiceUrl(url);
			try {
				rmiProxy.afterPropertiesSet();
				locator.setInterface(clazz,rmiProxy.getObject());
			} catch (Exception ex) {
				Logger.error("#getRmiProxyFactoryBean error#", ex);
			}
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		scanRmiProxy();
	}
}
