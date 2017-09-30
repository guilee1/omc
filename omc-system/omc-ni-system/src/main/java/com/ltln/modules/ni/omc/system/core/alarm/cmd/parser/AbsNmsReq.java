package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;

public abstract class AbsNmsReq extends AbsNmsCmd{

	protected String errorMsg;
	protected EInsCmdType type;
	
	public boolean badFormat(){
		return StringUtils.isNotEmpty(errorMsg);
	}
	
	public void setErrorMsg(String error){
		this.errorMsg = error;
	}
	
	public String getErrorMsg(){
		return this.errorMsg;
	}

	public EInsCmdType getType() {
		return type;
	}

	public void setType(EInsCmdType type) {
		this.type = type;
	}
}
