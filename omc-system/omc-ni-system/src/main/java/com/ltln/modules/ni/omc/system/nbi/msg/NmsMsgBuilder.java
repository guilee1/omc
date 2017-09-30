package com.ltln.modules.ni.omc.system.nbi.msg;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;


public final class NmsMsgBuilder {

	static NmsMsg buildNmsMsg(EMsgType ackloginalarm, String body) {
		return new NmsMsg(ackloginalarm, body);
	}
	
	public static NmsMsg buildAckHeartBeatMsg(String body) {
		return buildNmsMsg(EMsgType.ackHeartBeat,body);
	}
	
	public static NmsMsg buildAckLoginMsg(String body){
		return buildNmsMsg(EMsgType.ackLoginAlarm,body);
	}

	public static NmsMsg buildAckSyncAlarmMsg(String body) {
		return buildNmsMsg(EMsgType.ackSyncAlarmMsg,body);
	}

	public static NmsMsg buildRealTimeAlarmMsg(AlarmVo txtMsg) {
		return new NmsMsg(EMsgType.realTimeAlarm, txtMsg.toJson());
	}
	
	public static NmsMsg buildAckSyncAlarmFileMsg(String body) {
		return new NmsMsg(EMsgType.ackSyncAlarmFile, body);
	}
	
	public static NmsMsg buildAckSyncAlarmFileResultMsg(String body) {
		return new NmsMsg(EMsgType.ackSyncAlarmFileResult, body);
	}
}
