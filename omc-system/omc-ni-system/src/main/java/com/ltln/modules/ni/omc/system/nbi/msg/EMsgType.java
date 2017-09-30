package com.ltln.modules.ni.omc.system.nbi.msg;

public enum EMsgType {

	realTimeAlarm("realTimeAlarm", 0,""),
	reqLoginAlarm("reqLoginAlarm", 1,"omcLoginProcessHandler"), 
	ackLoginAlarm("ackLoginAlarm", 2,""), 
	reqSyncAlarmMsg("reqSyncAlarmMsg", 3,"omcSyncAlarmMsgProcessHandler"), 
	ackSyncAlarmMsg("ackSyncAlarmMsg", 4,""), 
	reqSyncAlarmFile("reqSyncAlarmFile", 5,"omcSyncAlarmFileProcessHandler"), 
	ackSyncAlarmFile("ackSyncAlarmFile", 6,""), 
	ackSyncAlarmFileResult("ackSyncAlarmFileResult", 7,""), 
	reqHeartBeat("reqHeartBeat", 8,"omcHeartBeatMsgProcessHandler"), 
	ackHeartBeat("ackHeartBeat", 9,""), 
	closeConnAlarm("closeConnAlarm", 10,"omcCloseConnProcessHandler");

	public int value = -1;
	public String name = null;
	public String handler;

	EMsgType(String name, int value,String handlerName) {
		this.name = name;
		this.value = value;
		this.handler = handlerName;
	}

	public static EMsgType from(int msgTypeValue) {
		for (EMsgType msgType : EMsgType.values()) {
			if (msgType.value == msgTypeValue) {
				return msgType;
			}
		}
		throw new RuntimeException("invalid param!");
	}

//	public static EMsgType getMsgTypeName(String msgTypeName) {
//		msgTypeName = msgTypeName.toLowerCase();
//		for (EMsgType msgType : EMsgType.values()) {
//			if (msgType.name.toLowerCase().equals(msgTypeName)) {
//				return msgType;
//			}
//		}
//		throw new RuntimeException("invalid param!");
//	}


}
