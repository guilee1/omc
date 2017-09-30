package com.ltln.modules.ni.omc.system.core.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.listener.IMaster;
import com.ltln.modules.ni.omc.system.core.listener.ISlave;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class AlarmCache implements IAlmCache,IMaster, ApplicationListener<ContextRefreshedEvent> {

	private int almSeq = 0;
	private int minSeq = 1;
	private final BlockingQueue<AlarmVo> msgQueue = new LinkedBlockingQueue<>(Constants.ALM_CACHE_SIZE);
	private final BlockingQueue<AlarmVo> alarmSeqLog = new LinkedBlockingQueue<>(Constants.ALM_SEQ_SIZE);
	
	@Resource(name="alarmClientContext")
	private ISlave slaver;
	
	@Autowired
	private IOmcDao dao;
	
	
	
	@Override
	public synchronized void init() {
		try{
			Integer maxNo = dao.getMaxSeqNo();
			almSeq = maxNo==null?0:maxNo;
		}catch (Exception e) {
			throw new RuntimeException("init cache error:",e);
		}
		minSeq = almSeq + 1;
	}
	
	public synchronized void put(AlarmVo vo){
		vo.setAlarmSeq(incrementAndGet(almSeq));
		vo.setEventTimeMills(System.currentTimeMillis());//the time of ni received
		fifoSeqLog(vo);
		fifoMsgQueue(vo);
		fireMsgEvent(vo);
	}

	private int incrementAndGet(int almSeq2) {
		return almSeq2 == Integer.MAX_VALUE?1:++almSeq;
	}

	private void fifoSeqLog(AlarmVo msg) {
		if(!this.alarmSeqLog.offer(msg)){
			AlarmVo polled = this.alarmSeqLog.poll();
			Logger.info(String.format("ALMCache 's alarmSeqLog is full! drop a alarmVo which seq is %s", polled.getAlarmSeq()));
			this.fifoSeqLog(msg);
		}
	}
	
	private void fifoMsgQueue(AlarmVo msg) {
		if(!this.msgQueue.offer(msg)){
			AlarmVo polled = this.msgQueue.poll();
			minSeq = polled.getAlarmSeq()+1;
			this.fifoMsgQueue(msg);
		}
	}
	
	
	public List<AlarmVo> takeAlarm(){
		List<AlarmVo> result = new ArrayList<>();
		this.alarmSeqLog.drainTo(result);
		return result;
	}

	@Override
	public void fireMsgEvent(AlarmVo msg) {
		if(slaver!=null)
			slaver.notify(msg);
	}


	public synchronized List<AlarmVo> find(int alarmSeq) {
		List<AlarmVo> result = new ArrayList<>();
		if(alarmSeq>=minSeq && alarmSeq<=almSeq){
			List<AlarmVo> target = new ArrayList<>(this.msgQueue);
			int index = Collections.binarySearch(target, new AlarmVo(alarmSeq));
			if(index==-1)
				Logger.info("bug here`");
			else
				result = target.subList(index, target.size());
			Logger.info(String.format("find alarm queue from %s---%s",alarmSeq,almSeq));
		}
		return result;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		this.init();
	}

}
