package com.ltln.modules.ni.omc.system.core.listener;

public interface IConnListenerContainer {

	void registerListener(IConnectionListener list);
	
	void unRegisterListener(IConnectionListener list);
}
