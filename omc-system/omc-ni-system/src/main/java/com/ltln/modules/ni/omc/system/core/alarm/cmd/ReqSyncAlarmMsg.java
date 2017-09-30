package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class ReqSyncAlarmMsg extends AbsNmsReq {

	String reqID;
	int alarmSeq;
	
	public int getAlarmSeq() {
		return alarmSeq;
	}
	public void setAlarmSeq(int alarmSeq) {
		this.alarmSeq = alarmSeq;
	}
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}

	
}
