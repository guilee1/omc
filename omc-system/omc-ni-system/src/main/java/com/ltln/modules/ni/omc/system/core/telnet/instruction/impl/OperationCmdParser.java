package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.CmdParserAdapter;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.OperationNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.Toolkit;

@Component
public class OperationCmdParser extends CmdParserAdapter {

	final String OPERATION_CMD = "IP::CMD::OBJ";

	@Override
	public AbsNmsReq decode(String msg) {
		OperationNmsReq request = new OperationNmsReq();
		request.setType(EInsCmdType.OPERATION);
		String[] triple = StringUtils.split(msg,Constants.INSTRUCTION_OPERATION_SPLITER);
		String errorMsg = StringUtils.EMPTY;
		if(triple.length==3){
			request.setIp(triple[0].trim());
			request.setCmd(triple[1].trim());
			request.setObject(triple[2].trim());
			if(StringUtils.isEmpty(request.getIp()) || StringUtils.isEmpty(request.getCmd())){
				errorMsg = "operation command# ip and cmd must not be empty!";
			}else if(!Toolkit.chkIp(request.getIp())){
				errorMsg = "invalid ip address!";
			}
		}else{
			errorMsg = "operation command# split size not equals 3!";
		}
		if(StringUtils.isNotEmpty(errorMsg)){
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}

	
	

}
