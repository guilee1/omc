package com.ltln.modules.ni.omc.system.core.telnet.instruction;

import org.apache.commons.lang3.StringUtils;

public enum EInsCmdType {

	HELP("helpInstructionHandler","helpCmdParser"),
	LOGIN("loginInstructionHandler","loginCmdParser"),
	LOGOUT("logoutInstructionHandler","logoutCmdParser"),
	MORE("moreInstructionHandler","moreCmdParser"),
	CONFIG("configInstructionHandler","configCmdParser"),
	OPERATION("operationInstructionHandler","operationCmdParser");
	
	public String handler;
	
	public String parser;
	
	EInsCmdType(String handler,String parser) {
		this.handler = handler;
		this.parser = parser;
	}

	public static String parserByLowerCaseCmd(String lowerCaseStrCommand) {
		for(EInsCmdType type : EInsCmdType.values()){
			if(StringUtils.startsWithIgnoreCase(lowerCaseStrCommand, type.name()))
					return type.parser;
		}
		return OPERATION.parser;
	}
	
	
}
