package com.ltln.modules.ni.omc.system.core.locator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public final class ServiceLocator implements ILocator {

	Map<Class<?>, Object> infMap = new ConcurrentHashMap<Class<?>, Object>();
	
	
	public  void setInterface(Class<?> infClazz, Object inf) {
		infMap.put(infClazz, inf);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getInstances(Class<T> infClazz) {
		return (T)infMap.get(infClazz);
	}

}
