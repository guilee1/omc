package com.ltln.modules.ni.omc.system.core.cache;

import java.util.UUID;

import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.EStatus;

public class AlarmClientContextTest {

	public static void main(String[] args) throws Exception {
		AlarmClientContext context = new AlarmClientContext();

		new Thread(new SenderThread(context)).start();
		new Thread(new ConnThread(context)).start();
		new Thread(new DisconThread(context)).start();

		Thread.currentThread().join();

	}
	static class ConnThread implements Runnable{
		AlarmClientContext context;
		
		public ConnThread(AlarmClientContext context) {
			this.context = context;
		}
		
		@Override
		public void run() {
			while(true){
				AlarmClient one = new AlarmClient(null,null,EStatus.RT_ALM_SEND.getValue(),UUID.randomUUID().toString(),EStatus.RT_ALM_SEND);
				context.add(one);
			}
		}
	}
	static class DisconThread implements Runnable{
		AlarmClientContext context;
		
		public DisconThread(AlarmClientContext context) {
			this.context = context;
		}
		
		@Override
		public void run() {
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				context.clear();
			}
		}
	}
	static class SenderThread implements Runnable{
		AlarmClientContext context;
		
		public SenderThread(AlarmClientContext context) {
			this.context = context;
		}
		
		@Override
		public void run() {
			for(;;);
//				context.send();
		}
	}
	
}
