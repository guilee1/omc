package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;

import static com.ltln.modules.ni.omc.system.util.Toolkit.removeEqualAnd;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqHeartBeat;
import com.ltln.modules.ni.omc.system.core.log.Logger;

@Component
public class HeartBeatParser extends CmdParserAdapter {

	@Override
	public ReqHeartBeat decode(String msg) {
		ReqHeartBeat request = new ReqHeartBeat();
		String[] params = msg.split(";");
		if(params.length==2){
			request.setReqID(removeEqualAnd(params[1],"reqid"));
		}else{
			String errorMsg = "split size not equals 2";
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}



}
