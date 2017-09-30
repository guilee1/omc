package com.ltln.modules.ni.omc.system.manager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DetectorInvocationHandler implements InvocationHandler {

	Object proxiedObj;

	public DetectorInvocationHandler(Object proxiedObj) {
		this.proxiedObj = proxiedObj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if(ContainerManager.app==null)
			return false;
		return method.invoke(proxiedObj, args);
	}


	@SuppressWarnings("unchecked")
	public static <T> T createProxiedIntf(Class<T> intf, Object proxiedObj) {
		DetectorInvocationHandler handler = new DetectorInvocationHandler(proxiedObj);
		return (T) Proxy.newProxyInstance(intf.getClassLoader(),
				new Class[] { intf }, handler);
	}

}
