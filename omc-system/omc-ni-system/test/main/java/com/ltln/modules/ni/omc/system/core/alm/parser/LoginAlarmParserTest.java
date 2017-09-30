package com.ltln.modules.ni.omc.system.core.alm.parser;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqHeartBeat;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqLoginAlarm;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.LoginAlarmParser;

import junit.framework.Assert;

public class LoginAlarmParserTest {
	LoginAlarmParser parser=new LoginAlarmParser();
	@Test
	public void testDecode_NullString(){
		ReqLoginAlarm rlga =  parser.decode(null);
		Assert.assertEquals(true, rlga.badFormat());
	}
	
	@Test
	public void testDecode_EmptyString(){
		ReqLoginAlarm rlga =  parser.decode("");
		Assert.assertEquals(true, rlga.badFormat());
	}
	
	//测试if ("msg".equalsIgnoreCase(type))
	@Test
	public void testDecode_MsgString(){
		ReqLoginAlarm rlga =  parser.decode("'aa';user='u';key='k';type='msg'");
		Assert.assertEquals(true, rlga.badFormat());
	}
	//测试if ("msg".equalsIgnoreCase(type))-》if (StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getPwd()))
	@Test
	public void testDecode_MsgUserNullString(){
		ReqLoginAlarm rlga =  parser.decode("'aa';user=;key=;type='msg'");
		Assert.assertEquals(true, rlga.badFormat());
	}
	//测试else if ("ftp".equalsIgnoreCase(type))
	@Test
	public void testDecode_FtpString(){
		ReqLoginAlarm rlga =  parser.decode("'aa';user='u';key='k';type='ftp'");
		Assert.assertEquals(true, rlga.badFormat());
		
}
	//测试else if ("ftp".equalsIgnoreCase(type))-》if (StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getPwd()))
	@Test
	public void testDecode_FtpUserNullString(){
		ReqLoginAlarm rlga =  parser.decode("'aa';user=;key='k';type='ftp'");
		Assert.assertEquals(true, rlga.badFormat());
	}
	//测试if->else
	@Test
	public void testDecode_IfOtherString(){
		ReqLoginAlarm rlga =  parser.decode("'aa';user='u';key='k';type='bb'");
		Assert.assertEquals(true, rlga.badFormat());
	}
	//测试else-》if (StringUtils.isEmpty(request.getUserName()) || StringUtils.isEmpty(request.getPwd()))
	@Test
	public void testDecode_OtherUserNullString(){
		ReqLoginAlarm rlga =  parser.decode("'aa';user=;key='k';type='bb'");
		Assert.assertEquals(true, rlga.badFormat());
	}
	//测试else
	@Test
	public void testDecode_OtherString(){
		ReqLoginAlarm rlga =  parser.decode("user=;key='k';type='bb'");
		Assert.assertEquals(true, rlga.badFormat());
	}
}
