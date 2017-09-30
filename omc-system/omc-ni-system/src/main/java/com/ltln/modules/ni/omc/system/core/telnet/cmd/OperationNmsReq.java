package com.ltln.modules.ni.omc.system.core.telnet.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class OperationNmsReq extends AbsNmsReq {

	private String ip;
	private String cmd;
	private String object;
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	
}
