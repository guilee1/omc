package com.ltln.modules.ni.omc.system.core.telnet;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.in.HeartBeatIdleChkHandler;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.listener.ConnectionModel;
import com.ltln.modules.ni.omc.system.core.listener.EConnectionType;
import com.ltln.modules.ni.omc.system.core.listener.IConnListenerContainer;
import com.ltln.modules.ni.omc.system.core.listener.IConnectionListener;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.IPipeline;
import com.ltln.modules.ni.omc.system.core.telnet.instruction.Operator;

@Component
@Sharable
public class TelnetServerHandler extends SimpleChannelInboundHandler<String> implements IConnListenerContainer {

	IConnectionListener listener;
	
	public IConnectionListener getListener() {
		return listener;
	}

	@Autowired
	IPipeline pipeline;
	
	Map<String ,Operator> userMap = new ConcurrentHashMap<>();
	
	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		Logger.info("Instruction channel active! comming on a connection");
		if (this.listener != null) {
			SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					listener.ConnectionActive(new ConnectionModel(ctx.channel().remoteAddress().toString(), EConnectionType.INSTRU,StringUtils.EMPTY));
				}
			});
		}
		Operator newGuy = new Operator();
		newGuy.setUid(ctx.channel().id().asLongText());
		newGuy.setIpAddr(ctx.channel().remoteAddress().toString());
		userMap.put(newGuy.getUid(),newGuy);
		ctx.write("Welcome to OMC Instruction Server!\r\n");
		ctx.write("It is " + new Date() + " now.\r\n");
		ctx.flush();
		super.channelActive(ctx);
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, String request)throws Exception {
		HeartBeatIdleChkHandler handler = (HeartBeatIdleChkHandler)ctx.pipeline().get(TelnetServerInitializer.TELNET_IDLE_HANDLER);
		handler.setLastHeartbeatTime(System.nanoTime());
		Operator user = userMap.get(ctx.channel().id().asLongText());
		pipeline.execute(user,ctx,request);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Logger.error("catch error during connection", cause);
		ctx.close();
	}
	
	@Override
	public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
		Logger.info("Instruction channel inactive!");
		final Operator user = userMap.get(ctx.channel().id().asLongText());
		userMap.remove(user.getUid());
		if(user.getTelClient()!=null)
			user.getTelClient().close(true);
		if(this.listener != null){
			SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					listener.ConnectionInactive(new ConnectionModel(ctx.channel().remoteAddress().toString(),EConnectionType.INSTRU,user.getUserName()));
				}
			});
		}
		super.channelInactive(ctx);
	}

	@Override
	public void registerListener(IConnectionListener list){
		this.listener = list;
	}
	
	@Override
	public void unRegisterListener(IConnectionListener list) {
		this.listener = null;
	}
}