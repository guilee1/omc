package com.ltln.modules.ni.omc.framework.aware.alarm;

import java.util.HashMap;
import java.util.Map;

public class AlarmChannelFactory {
	
	public static Map<String, AlarmChannel> map = new HashMap<String, AlarmChannel>();

	public synchronized static AlarmChannel getMessageChannel(String key, Integer size){
		if(map.get(key) != null){
			return map.get(key);
		}
		AlarmChannel mc = null;
		if(size != null && size > 0){
			mc = new AlarmChannel(size);
		}else{
			mc = new AlarmChannel();
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
