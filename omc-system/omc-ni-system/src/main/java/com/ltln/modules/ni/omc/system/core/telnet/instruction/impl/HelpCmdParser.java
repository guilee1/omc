package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.CmdParserAdapter;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.HelpNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;

@Component
public class HelpCmdParser extends CmdParserAdapter {

	final String HELP_CMD = "help";
	
	@Override
	public AbsNmsReq decode(String request) {
		HelpNmsReq cmdRequest = new HelpNmsReq();
		if(!HELP_CMD.equals(request))
			cmdRequest.setErrorMsg("bad help command format!just 'help' will be processed!");
		cmdRequest.setType(EInsCmdType.HELP);
		return cmdRequest;
	}

}
