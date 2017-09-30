package com.ltln.modules.ni.omc.system.monitor;

public interface IDetector {

	boolean RmiDetect();
	
	boolean JmsDetect();
	
	boolean DbDetect();
	
	boolean AlmServerDetect();
	
	boolean InstructionServerDetect();
	
	boolean FtpDetect();
}
