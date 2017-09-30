package com.ltln.modules.ni.omc.system.sbi.msg;

import org.springframework.beans.factory.annotation.Autowired;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.cache.ActiveAlmCache;
import com.ltln.modules.ni.omc.system.core.cache.IAlmCache;

public class JmsAyncReceiver {
	
	@Autowired
	IAlmCache cache;
	
	@Autowired
	ActiveAlmCache activeCache;
	
	public void receive(final Object obj){
		AlarmVo txtMsg = (AlarmVo)obj;
		cache.put(txtMsg);
		activeCache.receiveAlarm(txtMsg);
	}

}
