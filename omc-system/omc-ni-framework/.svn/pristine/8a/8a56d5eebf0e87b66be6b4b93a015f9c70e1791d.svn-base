package com.ltln.modules.ni.omc.framework.cq;

import java.util.HashMap;
import java.util.Map;

public class CqAlarmChannelFactory {
	
	public static Map<String, CqAlarmChannel> map = new HashMap<String, CqAlarmChannel>();

	public synchronized static CqAlarmChannel getMessageChannel(String key, Integer size){
		if(map.get(key) != null){
			return map.get(key);
		}
		CqAlarmChannel mc = null;
		if(size != null && size > 0){
			mc = new CqAlarmChannel(size);
		}else{
			mc = new CqAlarmChannel();
		}
		
		map.put(key, mc);
		return mc;
	}
	
	public synchronized static boolean destroyMessageChannel(String key){
		if(map.get(key) != null){
			map.remove(key);
			return true;
		}
		return false;
	}
	
	public synchronized static void clean(){
		map.clear();
	}
}
