package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;


public abstract class CmdParserAdapter implements ICmdParser {


	@Override
	public String encode(AbsNmsCmd cmd) {
		return cmd.toString();
	}

}
