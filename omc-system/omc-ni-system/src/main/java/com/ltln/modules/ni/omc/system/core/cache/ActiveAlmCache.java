package com.ltln.modules.ni.omc.system.core.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IAlmInf;
import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;

@Component
public class ActiveAlmCache implements Runnable{
	final String startTime = OmcDateFormater.formatBasicDate(new Date(0));
	ReferenceQueue<Map<String,AlarmVo>> refQueue = new ReferenceQueue<>();
	ActiveAlmRef ref;
	
	@Autowired
	ILocator locator;
	
	static class ActiveAlmRef extends SoftReference<Map<String,AlarmVo>>{
		public ActiveAlmRef(Map<String,AlarmVo> referent,ReferenceQueue<? super Map<String,AlarmVo>> q) {
			super(referent, q);
		}
	}
	
	Long getTimeValue(String time) {
		Date date = new Date();
		try {
			date = OmcDateFormater.parseBasicDate(time);
		} catch (ParseException e) {
			// unable to here
		}
		return date.getTime();
	}
	
	public void receiveAlarm(AlarmVo almObj){
		Map<String,AlarmVo> softMap = getCurrentActiveAlm();
		if(softMap==null)
			return;
		if(almObj.getAlarmStatus()==0)
			softMap.put(almObj.getAlarmId(), almObj);
		else
			softMap.remove(almObj.getAlarmId());
	}
	 
	public List<AlarmVo> getCurrentActiveAlm(String startTime,String endTime){
		Map<String,AlarmVo> allAlmList = this.getCurrentActiveAlm();
		if(allAlmList==null)
			return new ArrayList<>();
		List<AlarmVo> filterAlmList = new ArrayList<>();
		for(AlarmVo item : allAlmList.values()){
			if (getTimeValue(item.getEventTime()) >= getTimeValue(startTime) && (getTimeValue(item.getEventTime()) <= getTimeValue(endTime))) {
				filterAlmList.add(item);
            }
		}
		return filterAlmList;
	}
	
	private synchronized Map<String,AlarmVo> getCurrentActiveAlm() {
		if (ref == null)
			return null;
		return  ref.get();
	}
	
	private synchronized void cacheActiveAlm(Map<String,AlarmVo> almVo){
		cleanCacheRef();
		ref = new ActiveAlmRef(almVo, refQueue);
	}


	private synchronized void cleanCacheRef() {
		while (refQueue.poll() != null) {
			this.ref = null;
		}
	}

	@Override
	public void run() {
		Map<String, AlarmVo> almVoMap = new HashMap<>();
		List<AlarmVo> alarmList = queryOmcCurrentActiveAlm();
		for (AlarmVo item : alarmList)
			almVoMap.put(item.getAlarmId(), item);
		this.cacheActiveAlm(almVoMap);
	}

	
	private List<AlarmVo> queryOmcCurrentActiveAlm() {
		List<AlarmVo> alarmList = new ArrayList<>();
		String endTime = OmcDateFormater.formatBasicDate(new Date());
		try{
			IAlmInf almInf = locator.getInstances(IAlmInf.class);
			alarmList = almInf.queryCurrentActiveAlarm(startTime, endTime);
		}catch (Exception e) {
			Logger.error("remote call IAlmInf.queryCurrentActiveAlarm exception~", e);
		}
		return alarmList;
	}
}
