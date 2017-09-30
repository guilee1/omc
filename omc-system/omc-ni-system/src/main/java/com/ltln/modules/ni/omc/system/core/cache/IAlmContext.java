package com.ltln.modules.ni.omc.system.core.cache;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;

import java.util.List;

import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.EStatus;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqLoginAlarm;
import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;

public interface IAlmContext {

	void remove(ChannelId id);
	
	void clear();
	
	boolean isLogin(ChannelId id);
	
	List<AlarmClient> getClients(ReqLoginAlarm login);
	 
	AlarmClient getClient(ChannelId id);
	
	void add(ChannelHandlerContext channelHandlerContex,int ackloginalarm, String userName ,EStatus status);
	
	List<AlarmLog> takeAllLog();

	void fifo(AlarmLog log);
}
