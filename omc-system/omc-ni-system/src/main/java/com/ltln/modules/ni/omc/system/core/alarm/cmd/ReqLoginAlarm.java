package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.EStatus;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class ReqLoginAlarm extends AbsNmsReq {

	String userName;
	String pwd;
	EStatus estatus;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public EStatus getEstatus() {
		return estatus;
	}
	public void setEstatus(EStatus estatus) {
		this.estatus = estatus;
	}
	
}
