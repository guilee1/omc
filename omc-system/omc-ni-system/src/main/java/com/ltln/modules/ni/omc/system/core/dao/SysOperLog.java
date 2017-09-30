package com.ltln.modules.ni.omc.system.core.dao;

import java.util.Date;

public class SysOperLog {

	private long logId;
	private String nmsIp;
	private String userName;
	private Date connectTime;
	private Date disConnectTime;
	private String addInfo;
	
	public SysOperLog() {
		// TODO Auto-generated constructor stub
	}
	
	public SysOperLog(String ip,String name,Date connectTime,Date disConnectTime,String add) {
		this.nmsIp = ip;
		this.userName = name;
		this.connectTime = connectTime;
		this.disConnectTime = disConnectTime;
		this.addInfo = add;
	}
	
	
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public String getNmsIp() {
		return nmsIp;
	}
	public void setNmsIp(String nmsIp) {
		this.nmsIp = nmsIp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getConnectTime() {
		return connectTime;
	}
	public void setConnectTime(Date connectTime) {
		this.connectTime = connectTime;
	}
	public Date getDisConnectTime() {
		return disConnectTime;
	}
	public void setDisConnectTime(Date disConnectTime) {
		this.disConnectTime = disConnectTime;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
}
