package com.ltln.modules.ni.omc.system.telnet;

import com.ltln.modules.ni.omc.system.core.telnet.client.ITelnetClient;
import com.ltln.modules.ni.omc.system.core.telnet.client.ITelnetReader;
import com.ltln.modules.ni.omc.system.core.telnet.client.TinyTelnetClient;

public class TinyTelnetClientTest implements ITelnetReader {

	void test()throws Exception{
		ITelnetClient telnetClient = new TinyTelnetClient();
		telnetClient.registerCallBack(this);
		telnetClient.connect("10.11.1.208");
	}
	
	public static void main(String[] args) throws Exception{
		new TinyTelnetClientTest().test();
	}

	@Override
	public void readComplete(String response) {
		System.out.println(response);
		
	}
}
