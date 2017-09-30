package com.ltln.modules.ni.omc.system.core.telnet.client;

public interface ITelnetClient {

	void connect(String string) throws Exception;

	boolean sendCommand(String command);

	void close(boolean forceClosed);

	void registerCallBack(ITelnetReader reader);

	boolean isConnected();

}
