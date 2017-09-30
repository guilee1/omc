package com.ltln.modules.ni.omc.system.core.alarm.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsCmd;

public class AckLoginAlarm extends AbsNmsCmd {
	
	String  result;
	String resDesc = "null";

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
		sb.append("ackLoginAlarm;result=").append(this.result)
		.append(";resDesc=").append(this.resDesc);
		return sb.toString();
	}
}
