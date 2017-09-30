package com.ltln.modules.ni.omc.system.core.alarm.log;

import java.util.Date;

public class AlarmLog {
	
	private long logId;
	private String nmsIp;
	private String userName;
	private String alarmSequenceId;
	private Date pushTime;
	private String addInfo;
	
	public AlarmLog() {
		// TODO Auto-generated constructor stub
	}
	public AlarmLog(int alarmSeq, String userName2, String remoteIp) {
		this.alarmSequenceId = String.valueOf(alarmSeq);
		this.userName = userName2;
		this.nmsIp = remoteIp;
		this.pushTime = new Date();
	}
	public String getAlarmSequenceId() {
		return alarmSequenceId;
	}
	public void setAlarmSequenceId(String alarmSequenceId) {
		this.alarmSequenceId = alarmSequenceId;
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

	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
}
