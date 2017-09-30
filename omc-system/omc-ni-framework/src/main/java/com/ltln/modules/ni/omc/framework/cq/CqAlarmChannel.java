package com.ltln.modules.ni.omc.framework.cq;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


public class CqAlarmChannel<T> {
	
	//time out 
	private long timeout = 100;
	private BlockingQueue<T> queue = null;
	
	public CqAlarmChannel(int size){
		if(size>0){
			queue = new  LinkedBlockingQueue<T>(size);
		}else{
			queue = new  LinkedBlockingQueue<T>();
		}
	}
	
	public CqAlarmChannel(){
		queue = new  LinkedBlockingQueue<T>();
	}
        
	public  void put(T alarm) throws InterruptedException{
		while(!queue.offer(alarm)){
			queue.poll();
		}
	}
	
	public  T get() throws InterruptedException{
		return queue.take();
	}
	
	public  T poll() throws InterruptedException{
		return queue.poll(timeout, TimeUnit.MILLISECONDS);
	}
	
        public  boolean remove(T alarm) {
		return queue.remove(alarm);
	}
        
	public  int size(){
		return queue.size();
	}

	public  void clear(){
		queue.clear();
	}
	
        public T peek() {
            return queue.peek();
        }
        
        public Iterator<T> getItrator() {
            return queue.iterator();
        }
}
