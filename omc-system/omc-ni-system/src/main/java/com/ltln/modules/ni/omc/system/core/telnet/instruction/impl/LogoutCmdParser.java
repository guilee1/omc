package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.CmdParserAdapter;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.LogoutNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;

@Component
public class LogoutCmdParser extends CmdParserAdapter {

	final String LOGOUT_CMD = "logout";
	
	@Override
	public AbsNmsReq decode(String request) {
		LogoutNmsReq cmdRequest = new LogoutNmsReq();
		if(!LOGOUT_CMD.equals(request))
			cmdRequest.setErrorMsg("bad logout command format!just 'logout' will be processed!");
		cmdRequest.setType(EInsCmdType.LOGOUT);
		return cmdRequest;
	}

}
