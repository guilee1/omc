package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.CmdParserAdapter;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.LogoutNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;

@Component
public class MoreCmdParser extends CmdParserAdapter {

	final String MORE_CMD = "more";
	
	@Override
	public AbsNmsReq decode(String request) {
		LogoutNmsReq cmdRequest = new LogoutNmsReq();
		if(!MORE_CMD.equals(request))
			cmdRequest.setErrorMsg("bad more command format!just 'more' will be processed!");
		cmdRequest.setType(EInsCmdType.MORE);
		return cmdRequest;
	}

}
