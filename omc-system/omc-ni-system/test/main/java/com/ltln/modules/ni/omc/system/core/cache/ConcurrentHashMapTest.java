package com.ltln.modules.ni.omc.system.core.cache;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

	static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
	
	
	public static void main(String[] args) {
		new Thread(new AddThread(map)).start();
		for(;;)
			System.out.println(map.values().size());
	}
	
	static class AddThread implements Runnable{
		ConcurrentHashMap<String, String> map;
		
		public AddThread(ConcurrentHashMap<String, String> m) {
			this.map = m;
		}
		@Override
		public void run() {
			for(;;)
				map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
		}
	}
}
