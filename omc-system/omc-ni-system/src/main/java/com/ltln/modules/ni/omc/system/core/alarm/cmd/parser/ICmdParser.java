package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;



public interface ICmdParser {

	AbsNmsReq decode(String msg);
	
	String encode(AbsNmsCmd cmd); 
}
