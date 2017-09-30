package com.ltln.modules.ni.omc.system.core.alarm;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsgBuilder;
import com.ltln.modules.ni.omc.system.util.Constants;

public class AlarmClient implements Runnable {

	public static final int REAL_TIME_LOGIN = 0;
	public static final int FILE_SYNC_LOGIN = 1;
	
	final String userName;
	
	final Date connTime;
	
	final int reqType;
	
	final ChannelHandlerContext socketContext;
	
	final BlockingQueue<AlarmVo> rtQueue = new LinkedBlockingQueue<>(Constants.ALM_RT_QUEUE_SIZE);
	
	final BlockingQueue<AlarmVo> syncQueue = new LinkedBlockingQueue<>();
	
	volatile EStatus status;

	private final String remoteIp;
	
	volatile boolean syncBefore = false;
	
	final IAlmContext almContext;
	
	Future<?> future;
	
	public AlarmClient(IAlmContext almContext,ChannelHandlerContext channelHandlerContex,
			int ackloginalarm, String userName2,EStatus status) {
		this.almContext = almContext;
		this.socketContext = channelHandlerContex;
		this.reqType = ackloginalarm;
		this.userName = userName2;
		this.connTime = new Date();
		this.remoteIp = this.socketContext.channel().remoteAddress().toString();
		this.status = status;
	}


	public EStatus getStatus() {
		return status;
	}

	/**
	 * here to use before-happened rule to make sure status has been changed before wake up
	 * the rtQueue
	 * @param status
	 */
	public void setStatus(EStatus status) {
		boolean rt_to_suspend = EStatus.RT_ALM_SEND==this.status && EStatus.SUSPEND==status;
		this.status = (status);
		if(rt_to_suspend)
			this.wakeUpRtQueue();
	}

	private void wakeUpRtQueue() {
		AlarmVo poisonMsg = new AlarmVo(-1);
		this.rtQueue.offer(poisonMsg);
	}


	public ChannelFuture WaitToWrite(NmsMsg msg) throws InterruptedException{
		while(!socketContext.channel().isWritable()){
			Logger.info("touch the WRITE_BUFFER_HIGH_WATER_MARK! sleep 10 ms");
			Thread.sleep(10);
		}
		return socketContext.writeAndFlush(msg);
	}


	public ChannelHandlerContext getSocketContext() {
		return socketContext;
	}


	public String getUserName() {
		return userName;
	}


	public int getReqType() {
		return reqType;
	}


	public void pushSyncQueue(List<AlarmVo> syncMsgList) {
		this.syncQueue.clear();
		this.syncQueue.addAll(syncMsgList);
	}


	public Date getConnTime() {
		return connTime;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public boolean isSyncBefore() {
		return syncBefore;
	}

	public void setSyncBefore(boolean syncBefore) {
		this.syncBefore = syncBefore;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()){
			AlarmVo message = null;
			try{
				if(EStatus.RT_ALM_SEND==this.getStatus()){
					message = this.rtQueue.take();
				}else if(EStatus.SYNC_ALM_SEND==this.getStatus()){
					message = this.syncQueue.take();
					if(this.syncQueue.isEmpty()){
						this.setStatus(EStatus.RT_ALM_SEND);//if end of the sync-queue,change the state to rt_alm_send
					}
				}
			}catch (InterruptedException e) {
				Logger.error("alarm sender Interrupted!", e);
				return;
			}
			if(message!=null && message.getAlarmSeq()!=-1){
				final int alarmSeq = message.getAlarmSeq();
				ChannelFuture future = null;
				try {
					future = this.WaitToWrite(NmsMsgBuilder.buildRealTimeAlarmMsg(message));
				} catch (InterruptedException e) {
					Logger.error("alarm sender Interrupted!", e);
					return;
				}
				if(future!=null){
					future.addListener(new ChannelFutureListener() {
						@Override
						public void operationComplete(ChannelFuture future) throws Exception {
							if(future.isSuccess()){
								final AlarmLog log = new AlarmLog(alarmSeq,getUserName(),getRemoteIp());
								almContext.fifo(log);
							}
						}
					});
				}
			}
		}
	}


	public void addRtQueue(AlarmVo msg) {
		if(!this.rtQueue.offer(msg)){
			AlarmVo polled = this.rtQueue.poll();
			if(polled!=null)
				Logger.info(String.format("%s 's rt_queue is full! drop a alarmVo which seq is %s", this.remoteIp,polled.getAlarmSeq()));
			this.addRtQueue(msg);
		}
	}


	public Future<?> getFuture() {
		return future;
	}


	public void setFuture(Future<?> future) {
		this.future = future;
	}
}
