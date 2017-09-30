package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;

import static com.ltln.modules.ni.omc.system.util.Toolkit.removeEqualAnd;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmMsg;
import com.ltln.modules.ni.omc.system.core.log.Logger;

@Component
public class SyncAlarmMsgParser extends CmdParserAdapter {

	@Override
	public ReqSyncAlarmMsg decode(String msg) {
		ReqSyncAlarmMsg request = new ReqSyncAlarmMsg();
		String[] params = msg.split(";");
		if(params.length==3){
			String reqId = removeEqualAnd(params[1],"reqid");
			String almSeq = removeEqualAnd(params[2],"alarmseq");
			request.setReqID(reqId);
			try{
				request.setAlarmSeq(Integer.parseInt(almSeq));
			}catch (NumberFormatException e) {
				String errorMsg = "alarmSeq must be number";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
			if(request.getAlarmSeq()<=0){
				String errorMsg = "alarmSeq <= 0";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
			}
		}else{
			String errorMsg = "split size must equals 3";
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}


}
