package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class ReqSyncAlarmFile extends AbsNmsReq {
	String reqId;
	int alarmSeq = -1;
	String startTime;
	String endTime;
	int syncSource;
	
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public int getAlarmSeq() {
		return alarmSeq;
	}
	public void setAlarmSeq(int alarmSeq) {
		this.alarmSeq = alarmSeq;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getSyncSource() {
		return syncSource;
	}
	public void setSyncSource(int syncSource) {
		this.syncSource = syncSource;
	}
}
