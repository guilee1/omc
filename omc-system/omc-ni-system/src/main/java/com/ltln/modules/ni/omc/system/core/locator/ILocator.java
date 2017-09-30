package com.ltln.modules.ni.omc.system.core.locator;

import org.springframework.stereotype.Service;

@Service("locator")
public interface ILocator {

	<T> T getInstances(Class<T> infClazz);
	 
	void  setInterface(Class<?> infClazz,Object inf);
}
