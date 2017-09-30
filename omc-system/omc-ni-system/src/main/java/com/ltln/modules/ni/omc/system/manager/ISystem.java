package com.ltln.modules.ni.omc.system.manager;

public interface ISystem {

	
	boolean startUp();
	
	boolean shutDown();
	
	String getName();
}
