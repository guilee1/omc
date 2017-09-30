package com.ltln.modules.ni.omc.system.core.telnet.instruction.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.CmdParserAdapter;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.cmd.LoginNmsReq;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.EInsCmdType;

@Component
public class LoginCmdParser extends CmdParserAdapter {

	final String LOGIN_CMD = "login user=#{user} pwd=#{pwd}";

	@Override
	public AbsNmsReq decode(String msg) {
		LoginNmsReq request = new LoginNmsReq();
		String[] triple = StringUtils.split(msg);
		if(triple.length==3){
			request.setUserName(triple[1].replace("user=", "").trim());
			request.setPassword(triple[2].replace("pwd=", "").trim());
			if(StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getPassword())){
				String errorMsg = "login message# user and pwd must not be empty!";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
			}else{
				request.setType(EInsCmdType.LOGIN);
			}
		}else{
			String errorMsg = "login message# split size not equals 3!";
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}
	

}
