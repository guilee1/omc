package com.ltln.modules.ni.omc.system.core.listener;

public interface IConnectionListener {

	void ConnectionActive(ConnectionModel client);
	
	void ConnectionLogin(ConnectionModel client);
	
	void ConnectionInactive(ConnectionModel client);
}
