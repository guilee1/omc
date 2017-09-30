package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsCmd;

public class AckSyncAlarmFileResult extends AbsNmsCmd {

	String reqID;
	String  result;
	String fileName;
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
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("ackSyncAlarmFileResult;reqId=").append(this.reqID).append(";result=")
		.append(this.result).append(";fileName=").append(this.fileName)
		.append(";resDesc=").append(this.resDesc);
		return sb.toString();
	}
}
