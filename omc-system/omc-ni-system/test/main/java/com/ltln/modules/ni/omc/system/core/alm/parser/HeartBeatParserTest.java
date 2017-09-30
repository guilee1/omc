package com.ltln.modules.ni.omc.system.core.alm.parser;

import junit.framework.Assert;

import org.junit.Test;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqHeartBeat;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.HeartBeatParser;

public class HeartBeatParserTest {

	HeartBeatParser parser = new HeartBeatParser();
	
	@Test
	public void testDecode_NullString(){
		ReqHeartBeat rhb =  parser.decode(null);
		Assert.assertEquals(true, rhb.badFormat());
	}
	
	@Test
	public void testDecode_EmptyString(){
		ReqHeartBeat rhb =  parser.decode("");
		Assert.assertEquals(true, rhb.badFormat());
	}
	
	//测试if中的try，catch方法
	
	@Test
	public void testDecode_CharIntString(){
		ReqHeartBeat rhb =  parser.decode("'abc';reqId='abc'");
		Assert.assertEquals(true, rhb.badFormat());
	}

	//测试if中的try方法
	@Test
	public void testDecode_IntCharString(){
		ReqHeartBeat rhb =  parser.decode("123;reqId=10");
		Assert.assertEquals(false, rhb.badFormat());
	}
	
	
	//测试else方法
	@Test
	public void testDecode_CharString(){
		ReqHeartBeat rhb =  parser.decode("-------");
		Assert.assertEquals(true, rhb.badFormat());
	}
}
