package com.ltln.modules.ni.omc.system.core.telnet.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class LoginNmsReq extends AbsNmsReq {

	String userName;
	String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
