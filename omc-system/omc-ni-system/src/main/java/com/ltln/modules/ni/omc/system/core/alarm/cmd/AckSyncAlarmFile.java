package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsCmd;

public class AckSyncAlarmFile extends AbsNmsCmd {

	String reqID;
	String  result;
	String resDesc = "null";
	
	public String getReqID() {
		return reqID;
	}
	public void setReqID(String reqID) {
		this.reqID = reqID;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResDesc() {
		return resDesc;
	}
	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ackSyncAlarmFile;reqId=").append(this.reqID).append(";result=")
		.append(this.result).append(";resDesc=").append(this.resDesc);
		return sb.toString();
	}
}
