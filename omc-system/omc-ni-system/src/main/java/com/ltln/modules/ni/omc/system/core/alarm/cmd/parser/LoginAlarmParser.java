package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;

import static com.ltln.modules.ni.omc.system.util.Toolkit.removeEqualAnd;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.EStatus;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqLoginAlarm;
import com.ltln.modules.ni.omc.system.core.log.Logger;

@Component
public class LoginAlarmParser extends CmdParserAdapter {

	@Override
	public ReqLoginAlarm decode(String msg) {
		ReqLoginAlarm request = new ReqLoginAlarm();
		String[] params = msg.split(";");
		if(params.length==4){
			request.setUserName(removeEqualAnd(params[1],"user"));
			request.setPwd(removeEqualAnd(params[2],"key"));
			String type = removeEqualAnd(params[3],"type");
			EStatus estatus = null;
			if("msg".equalsIgnoreCase(type))
				estatus = EStatus.RT_ALM_SEND;
			else if("ftp".equalsIgnoreCase(type))
				estatus = EStatus.SUSPEND;
			else{
				String errorMsg = "type must be msg or ftp";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
			request.setEstatus(estatus);
			if(StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getPwd())){
				String errorMsg = "empty user or key";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
			}
		}else{
			String errorMsg = "split size not equals 4";
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}

}
