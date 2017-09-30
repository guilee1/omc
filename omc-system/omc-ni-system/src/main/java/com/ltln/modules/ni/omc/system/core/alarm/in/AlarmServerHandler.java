package com.ltln.modules.ni.omc.system.core.alarm.in;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.CommandDispatcher;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.listener.ConnectionModel;
import com.ltln.modules.ni.omc.system.core.listener.EConnectionType;
import com.ltln.modules.ni.omc.system.core.listener.IConnListenerContainer;
import com.ltln.modules.ni.omc.system.core.listener.IConnectionListener;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;

@Component
@Sharable
public class AlarmServerHandler extends SimpleChannelInboundHandler<NmsMsg> implements IConnListenerContainer {

	IConnectionListener listener;
	
	public IConnectionListener getListener() {
		return listener;
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		Logger.info("channel active! comming on a connection");
		if(this.listener != null){
			SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					listener.ConnectionActive(new ConnectionModel(ctx.channel().remoteAddress().toString(),EConnectionType.ALM,StringUtils.EMPTY));
				}
			});
		}
		super.channelActive(ctx);
	}

	@Override
	protected void channelRead0(
			ChannelHandlerContext paramChannelHandlerContext, NmsMsg msgObj)
			throws Exception {
		Logger.info(String.format("receive a nms message type is: %s,body is %s", msgObj.getMsgType().name,msgObj.getBody()));
		CommandDispatcher dispatcher = SelfBeanFactoryAware.getBean("commandDispatcher");
		dispatcher.dispatch(paramChannelHandlerContext, msgObj);
	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Logger.error("catch error during connection", cause);
        ctx.close();
    }
	
	@Override
	public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
		Logger.info("channel inactive!");
		IAlmContext clients = SelfBeanFactoryAware.getBean("alarmClientContext");
		final AlarmClient client = clients.getClient(ctx.channel().id());
		clients.remove(ctx.channel().id());
		if(this.listener != null){
			SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					listener.ConnectionInactive(new ConnectionModel(ctx.channel().remoteAddress().toString(),
							EConnectionType.ALM,client==null?StringUtils.EMPTY:client.getUserName()));
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
