package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.CmdParserAdapter;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.ConfigNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;

@Component
public class ConfigCmdParser extends CmdParserAdapter {

	final String CONFIG_CMD_MORE = "config more #{open|close}";
	final String CONFIG_CMD_TIMEOUT = "config timeout #{time}";
	
	@Override
	public AbsNmsReq decode(String msg) {
		ConfigNmsReq request = new ConfigNmsReq();
		request.setType(EInsCmdType.CONFIG);
		String errorMsg = StringUtils.EMPTY;
		String[] triple = StringUtils.split(msg);
		if(triple.length==3){
			if(StringUtils.equals(triple[1], "more")){
				if(StringUtils.equals(triple[2], "open")){
					request.setMore(true);
				}else if(StringUtils.equals(triple[2], "close")){
					request.setMore(false);
				}else{
					errorMsg = "config more #{open|close} only can be supported!";
				}
			}else if(StringUtils.equals(triple[1], "timeout")){
				try{
					int timeOut = Integer.parseInt(triple[2]);
					if(timeOut<0){
						errorMsg = "config timeout #{time} must large than zero!";
					}else{
						request.setTimeout(timeOut);
					}
				}catch (NumberFormatException e) {
					errorMsg = "config timeout #{time} must be Integer!";
				}
			}else{
				errorMsg = "config command# only support {more | timeout} !";
			}
		}else if(triple.length==1){
			request.setShow(true);
		}else{
			errorMsg = "config command# split size not equals 1 or 3!";
		}
		if(StringUtils.isNotEmpty(errorMsg)){
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}

}
