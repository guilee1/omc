package com.ltln.modules.ni.omc.system.core.cache;

import java.util.List;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;

public interface IAlmCache {

	public void init();
	
	public void put(AlarmVo vo);
	
	public List<AlarmVo> takeAlarm();
	
	public List<AlarmVo> find(int alarmSeq);
}
