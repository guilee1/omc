package com.ltln.modules.ni.omc.system.core.alm.parser;

import org.junit.Test;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqHeartBeat;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmMsg;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.SyncAlarmMsgParser;

import junit.framework.Assert;

public class SyncAlarmMsgParserTest {
	
	SyncAlarmMsgParser parser=new SyncAlarmMsgParser();
	
	@Test
	public void testDecode_NullString(){
		ReqSyncAlarmMsg rsa =  parser.decode(null);
		Assert.assertEquals(true, rsa.badFormat());
	}
	
	@Test
	public void testDecode_EmptyString(){
		ReqSyncAlarmMsg rsa =  parser.decode("");
		Assert.assertEquals(true, rsa.badFormat());
	}
	
	//测试if->try->if->over
	@Test
	public void testDecode_AString(){
		ReqSyncAlarmMsg rsa =  parser.decode("0;reqID=0;alarmSeq=0");
		Assert.assertEquals(true, rsa.badFormat());
	}
	
	//测试if->try->over
	@Test
	public void testDecode_DString(){
		ReqSyncAlarmMsg rsa =  parser.decode("0;reqId=0;alarmSeq=1");
		Assert.assertEquals(false, rsa.badFormat());
	}
	//测试if->try{request.setReqID(Integer.parseInt(reqId));->catch->if->over
	@Test
	public void testDecode_BString(){
		ReqSyncAlarmMsg rsa =  parser.decode("0;reqId=a;alarmSeq=0");
		Assert.assertEquals(true, rsa.badFormat());
	}
	
	//测试if->	try{
//	request.setReqID(Integer.parseInt(reqId));
//	request.setAlarmSeq(Integer.parseInt(almSeq));->catch->if->over
	@Test
	public void testDecode_CString(){
		ReqSyncAlarmMsg rsa =  parser.decode("0;reqId=0;alarmSeq=a");
		Assert.assertEquals(true, rsa.badFormat());
	}
	
//	测试else->over
	@Test
	public void testDecode_EString(){
		ReqSyncAlarmMsg rsa =  parser.decode("0");
		Assert.assertEquals(true, rsa.badFormat());
	}
}
