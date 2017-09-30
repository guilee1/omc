package com.ltln.modules.ni.omc.core;

import com.ltln.modules.ni.omc.core.annotation.Facade;

/**
 * 
 * Management Interface
 */
@Facade(serviceName="IMgrInf",serviceBean="mgrInfImpl")
public interface IMgrInf {

	/**
	 * ping OMC-EMS
	 */
	void heartBeat();
	
	/**
	 * authentication user account
	 * @param userName
	 * @param pwd
	 * @return
	 */
	boolean authentication(String userName, String pwd); 
	
	/**
	 * get ntp time from omc ems
	 */
	long getNtpTime();
}
