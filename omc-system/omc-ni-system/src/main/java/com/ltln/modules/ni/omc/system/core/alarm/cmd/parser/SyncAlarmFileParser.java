package com.ltln.modules.ni.omc.system.core.alarm.cmd.parser;

import static com.ltln.modules.ni.omc.system.util.Toolkit.removeEqualAnd;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmFile;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;

@Component
public class SyncAlarmFileParser extends CmdParserAdapter {

	@Override
	public ReqSyncAlarmFile decode(String msg) {
		ReqSyncAlarmFile request = new ReqSyncAlarmFile();
		String[] params = msg.split(";");
		if (params.length == 4) {
			String reqId = removeEqualAnd(params[1],"reqid");
			String almSeq = removeEqualAnd(params[2],"alarmseq");
			String syncSource = removeEqualAnd(params[3],"syncsource");
			request.setReqId(reqId);
			try {
				request.setAlarmSeq(Integer.parseInt(almSeq));
				request.setSyncSource(Integer.parseInt(syncSource));
			} catch (NumberFormatException e) {
				String errorMsg = "must be number format";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
			if (request.getAlarmSeq() <= 0) {
				String errorMsg = "alarmSeq <=0";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
			if (request.getSyncSource() != 1) {
				String errorMsg = "sync must be 1 in Seq model";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
		} else if (params.length == 5) {
			String reqId = removeEqualAnd(params[1],"reqid");
			request.setStartTime(removeEqualAnd(params[2],"starttime"));
			request.setEndTime(removeEqualAnd(params[3],"endtime"));
			String syncSource = removeEqualAnd(params[4],"syncsource");
			request.setReqId(reqId);
			try {
				request.setSyncSource(Integer.parseInt(syncSource));
			} catch (NumberFormatException e) {
				String errorMsg = "sync must be number";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
			Date start = new Date(0);
			Date end = new Date();
			try {
				if (StringUtils.isNotEmpty(request.getStartTime()))
					start = OmcDateFormater.parseBasicDate(request.getStartTime());
				else
					request.setStartTime(OmcDateFormater.formatBasicDate(start));
				if (StringUtils.isNotEmpty(request.getEndTime()))
					end = OmcDateFormater.parseBasicDate(request.getEndTime());
				else
					request.setEndTime(OmcDateFormater.formatBasicDate(end));
			} catch (ParseException e) {
				String errorMsg = "err time format";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
				return request;
			}
			if (end.before(start)) {
				String errorMsg = "endTime > startTime";
				Logger.info(errorMsg);
				request.setErrorMsg(errorMsg);
			}
		} else {
			String errorMsg = "split size must equals 4/5";
			Logger.info(errorMsg);
			request.setErrorMsg(errorMsg);
		}
		return request;
	}

}
