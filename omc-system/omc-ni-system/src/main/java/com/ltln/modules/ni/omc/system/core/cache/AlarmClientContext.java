package com.ltln.modules.ni.omc.system.core.cache;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.EStatus;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqLoginAlarm;
import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;
import com.ltln.modules.ni.omc.system.core.listener.ISlave;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class AlarmClientContext implements IAlmContext,ISlave{

	@Resource(name="telnetReaderThreadPool")
	ThreadPoolTaskExecutor threadPool;
	
	private final Map<ChannelId,AlarmClient> clients = new ConcurrentHashMap<>();
	private final BlockingQueue<AlarmLog> almLogs = new LinkedBlockingQueue<>(Constants.ALM_LOG_SIZE);
	
	void add(AlarmClient client){
		clients.put(client.getSocketContext().channel().id(), client);
		client.setFuture(threadPool.submit(client));
	}

	public  void remove(ChannelId id){
		AlarmClient client = this.clients.remove(id);
		if(client!=null){
			Future<?>  future = client.getFuture();
			if(future!=null)
				future.cancel(true);
		}
	}
	

	
	public  void clear(){
		this.clients.clear();
	}
	
	private Collection<AlarmClient> getClients(){
		return this.clients.values();
	}

	public  List<AlarmClient> getClients(ReqLoginAlarm login) {
		List<AlarmClient> result = new ArrayList<>();
		for(AlarmClient key : this.getClients()){
			if(key.getUserName().equals(login.getUserName()))
				result.add(key);
		}
		return result;
	}

	public  boolean isLogin(ChannelId id) {
		return this.getClient(id)!=null;
	}

	public  AlarmClient getClient(ChannelId id) {
		return this.clients.get(id);
	}
	

	public  void add(ChannelHandlerContext channelHandlerContex,int ackloginalarm, String userName ,EStatus status) {
		AlarmClient client = new AlarmClient(this,channelHandlerContex,ackloginalarm,userName,status);
		this.add(client);
	}


	@Override
	public void notify(AlarmVo msg) {
		//just notify real-time socket channel
		for(AlarmClient client : this.getClients()){
			if(client.getReqType() == AlarmClient.REAL_TIME_LOGIN)
				client.addRtQueue(msg);
		}
	}
	
	public List<AlarmLog> takeAllLog(){
		List<AlarmLog> allLog =  new ArrayList<>();
		this.almLogs.drainTo(allLog);
		return allLog;
	}

	@Override
	public void fifo(AlarmLog log) {
		if(!this.almLogs.offer(log)){
			AlarmLog polled = this.almLogs.poll();
			Logger.info(String.format("AlarmClientContext 's almLogs is full! drop a alarmVo which seq is %s", polled.getAlarmSequenceId()));
			this.fifo(log);
		}
	}

}
