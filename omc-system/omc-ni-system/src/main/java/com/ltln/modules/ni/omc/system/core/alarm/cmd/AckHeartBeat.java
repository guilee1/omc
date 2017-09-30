package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsCmd;

public class AckHeartBeat extends AbsNmsCmd {

	String reqID;
	
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ackHeartBeat;reqId=").append(this.reqID);
		return sb.toString();
	}
}
