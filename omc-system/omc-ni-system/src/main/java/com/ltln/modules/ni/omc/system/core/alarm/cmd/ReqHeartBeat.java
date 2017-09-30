package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class ReqHeartBeat extends AbsNmsReq {

	String reqID;
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
}
