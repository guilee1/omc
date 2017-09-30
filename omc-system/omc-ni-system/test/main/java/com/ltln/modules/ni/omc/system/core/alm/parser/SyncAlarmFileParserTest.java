package com.ltln.modules.ni.omc.system.core.alm.parser;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqHeartBeat;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmFile;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.SyncAlarmFileParser;

import junit.framework.Assert;

public class SyncAlarmFileParserTest {
	SyncAlarmFileParser parser=new SyncAlarmFileParser();
	@Test
	public void testDecode_NullString(){
		ReqSyncAlarmFile rsaf =  parser.decode(null);
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	@Test
	public void testDecode_EmptyString(){
		ReqSyncAlarmFile rsaf =  parser.decode("");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	/*
	 * 测试只执行if代码块
	 */
	//测试if-》try->over
	@Test
	public void testDecode_AString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='2';alarmSeq='1';syncSource='1'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试if->try->if (request.getAlarmSeq() <= 0)->over 
	@Test
	public void testDecode_BString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='2';alarmSeq='0';syncSource='1'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试if->try->if (request.getSyncSource() != 1)->over
	@Test
	public void testDecode_CString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='2';alarmSeq='1';syncSource='0'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试if->try->if (request.getAlarmSeq() <= 0)->if (request.getSyncSource() != 1)->over
	@Test
	public void testDecode_DString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='2';alarmSeq='0';syncSource='0'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试if->try->catch->if (request.getAlarmSeq() <= 0)->if (request.getSyncSource() != 1)
	@Test
	public void testDecode_EString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='a';alarmSeq='0';syncSource='0'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试if->try {request.setReqId(Integer.parseInt(reqId));->request.setAlarmSeq(Integer.parseInt(almSeq));->if (request.getAlarmSeq() <= 0)->if (request.getSyncSource() != 1) 
	@Test
	public void testDecode_FString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='0';alarmSeq='a';syncSource='0'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试if->try{
//	request.setReqId(Integer.parseInt(reqId));
//	request.setAlarmSeq(Integer.parseInt(almSeq));
//	request.setSyncSource(Integer.parseInt(syncSource));->if (request.getSyncSource() != 1)
	@Test
	public void testDecode_GString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='0';alarmSeq='1';syncSource='0'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	//测试if->try{
//	request.setReqId(Integer.parseInt(reqId));
//	request.setAlarmSeq(Integer.parseInt(almSeq));
//	request.setSyncSource(Integer.parseInt(syncSource));->if (request.getAlarmSeq() <= 0)->if (request.getSyncSource() != 1)
	@Test
	public void testDecode_HString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId='0';alarmSeq='0';syncSource='0'");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	
	/**
	 * else if中，第一个try全部执行
	 */
	//测试else if->try->try->if->over
	@Test
	public void testDecode_IString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=2017-01-01 01:01:01;endTime=2016-01-01 01:01:01;syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->try->over
	@Test
	public void testDecode_JString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=2017-01-01 01:01:01;endTime='2018-01-01 01:01:01';syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->over
	@Test
	public void testDecode_KString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime=;syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if ->try->try {
//	if (StringUtils.isNotEmpty(request.getStartTime()))
//		start = dateFormat.parse(request.getStartTime());->if->over
	@Test
	public void testDecode_LString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=2018-01-01 01:01:01;endTime=;syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if ->try->try {
//	if (StringUtils.isNotEmpty(request.getStartTime()))
//	start = dateFormat.parse(request.getStartTime());->over
	@Test
	public void testDecode_MString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=2014-01-01 01:01:01;endTime=;syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->try{if (StringUtils.isNotEmpty(request.getStartTime()))->over
	@Test
	public void testDecode_NString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2014-1-1/1/1/1';endTime=;syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->if (StringUtils.isNotEmpty(request.getEndTime()))
//	end = dateFormat.parse(request.getEndTime())->if->over;
	@Test
	public void testDecode_OString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime=1960-01-01 01:01:01;syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->if (StringUtils.isNotEmpty(request.getEndTime()))
//	end = dateFormat.parse(request.getEndTime())->over;
	@Test
	public void testDecode_PString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime='1990-01-01 01:01:01';syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->if (StringUtils.isNotEmpty(request.getEndTime()))->over
	@Test
	public void testDecode_QString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime='90/01/01/01/01/01';syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->try {
//	if (StringUtils.isNotEmpty(request.getStartTime()))
//		start = dateFormat.parse(request.getStartTime());
//	if (StringUtils.isNotEmpty(request.getEndTime()))->if->over
	@Test
	public void testDecode_RString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2018-01-01 01:01:01';endTime='90/01/01/01/01/01';syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	//测试else if->try->try{
//	if (StringUtils.isNotEmpty(request.getStartTime()))
//		start = dateFormat.parse(request.getStartTime());
//	if (StringUtils.isNotEmpty(request.getEndTime()))->over
	@Test
	public void testDecode_SString(){
		ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2010-01-01 01:01:01';endTime='90/01/01/01/01/01';syncSource=1");
		Assert.assertEquals(true, rsaf.badFormat());
	}
	
	/**
	 * else if中，第一个try执行request.setReqId(Integer.parseInt(reqId));时，进入catch
	 */
	//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->try->if->over
		@Test
		public void testDecode_IIString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime=2017-01-01 01:01:01;endTime=2016-01-01 01:01:01;syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->try->over
		@Test
		public void testDecode_JJString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime='2017-01-01 01:01:01';endTime='2018-01-01 01:01:01';syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->over
		@Test
		public void testDecode_KKString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime=;endTime=;syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if ->try{request.setReqId(Integer.parseInt(reqId))->catch->try {
//		if (StringUtils.isNotEmpty(request.getStartTime()))
//			start = dateFormat.parse(request.getStartTime());->if->over
		@Test
		public void testDecode_LLString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime='2018-01-01 01:01:01';endTime=;syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if ->try{request.setReqId(Integer.parseInt(reqId))->catch->try {
//		if (StringUtils.isNotEmpty(request.getStartTime()))
//		start = dateFormat.parse(request.getStartTime());->over
		@Test
		public void testDecode_MMString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime='2014-01-01 01:01:01';endTime=;syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->try{if (StringUtils.isNotEmpty(request.getStartTime()))->over
		@Test
		public void testDecode_NNString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime='2014-1-1/1/1/1';endTime=;syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->if (StringUtils.isNotEmpty(request.getEndTime()))
//		end = dateFormat.parse(request.getEndTime())->if->over;
		@Test
		public void testDecode_OOString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime=;endTime='1960-01-01 01:01:01';syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->if (StringUtils.isNotEmpty(request.getEndTime()))
//		end = dateFormat.parse(request.getEndTime())->over;
		@Test
		public void testDecode_PPString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime=;endTime='1990-01-01 01:01:01';syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->if (StringUtils.isNotEmpty(request.getEndTime()))->over
		@Test
		public void testDecode_QQString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime=;endTime='90/01/01/01/01/01';syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->try {
//		if (StringUtils.isNotEmpty(request.getStartTime()))
//			start = dateFormat.parse(request.getStartTime());
//		if (StringUtils.isNotEmpty(request.getEndTime()))->if->over
		@Test
		public void testDecode_RRString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime='2018-01-01 01:01:01';endTime='90/01/01/01/01/01';syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->catch->try{
//		if (StringUtils.isNotEmpty(request.getStartTime()))
//			start = dateFormat.parse(request.getStartTime());
//		if (StringUtils.isNotEmpty(request.getEndTime()))->over
		@Test
		public void testDecode_SSString(){
			ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=a;startTime='2010-01-01 01:01:01';endTime='90/01/01/01/01/01';syncSource=1");
			Assert.assertEquals(true, rsaf.badFormat());
		}
	
		
		/**
		 * else if中，第一个try执行request.setReqId(Integer.parseInt(reqId));
		 * request.setSyncSource(Integer.parseInt(syncSource));时，进入catch
		 */
		
		//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try->if->over
				@Test
				public void testDecode_IIIString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=2017-01-01 01:01:01;endTime=2016-01-01 01:01:01;syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try->over
				@Test
				public void testDecode_JJJString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2017-01-01 01:01:01';endTime='2018-01-01 01:01:01';syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->over
				@Test
				public void testDecode_KKKString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime=;syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if ->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try {
//				if (StringUtils.isNotEmpty(request.getStartTime()))
//					start = dateFormat.parse(request.getStartTime());->if->over
				@Test
				public void testDecode_LLLString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2018-01-01 01:01:01';endTime=;syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if ->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try {
//				if (StringUtils.isNotEmpty(request.getStartTime()))
//				start = dateFormat.parse(request.getStartTime());->over
				@Test
				public void testDecode_MMMString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2014-01-01 01:01:01';endTime=;syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try{if (StringUtils.isNotEmpty(request.getStartTime()))->over
				@Test
				public void testDecode_NNNString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2014-1-1/1/1/1';endTime=;syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->if (StringUtils.isNotEmpty(request.getEndTime()))
//				end = dateFormat.parse(request.getEndTime())->if->over;
				@Test
				public void testDecode_OOOString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime='1960-01-01 01:01:01';syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->if (StringUtils.isNotEmpty(request.getEndTime()))
//				end = dateFormat.parse(request.getEndTime())->over;
				@Test
				public void testDecode_PPPString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime='1990-01-01 01:01:01';syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				//测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->if (StringUtils.isNotEmpty(request.getEndTime()))->over
				@Test
				public void testDecode_QQQString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime=;endTime='90/01/01/01/01/01';syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				/*测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try {
				if (StringUtils.isNotEmpty(request.getStartTime()))
					start = dateFormat.parse(request.getStartTime());
				if (StringUtils.isNotEmpty(request.getEndTime()))->if->over
 
                */
				@Test
				public void testDecode_RRRString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2018-01-01 01:01:01';endTime='90/01/01/01/01/01';syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				/*测试else if->try{request.setReqId(Integer.parseInt(reqId))->request.setSyncSource(Integer.parseInt(syncSource));->catch->try{
				if (StringUtils.isNotEmpty(request.getStartTime()))
					start = dateFormat.parse(request.getStartTime());
				if (StringUtils.isNotEmpty(request.getEndTime()))->over
				*/
				@Test
				public void testDecode_SSSString(){
					ReqSyncAlarmFile rsaf =  parser.decode("0;reqId=1;startTime='2010-01-01 01:01:01';endTime='90/01/01/01/01/01';syncSource=a");
					Assert.assertEquals(true, rsaf.badFormat());
				}
				
				/**
				 * 
				 * 
				 * 测试else代码块的内容
				 * 
				 */
				@Test
				public void testDecode_String(){
					ReqSyncAlarmFile rsaf =  parser.decode("0");
					Assert.assertEquals(true, rsaf.badFormat());
				}
}
