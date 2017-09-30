package com.ltln.modules.ni.omc.system.core.alarm;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import com.ltln.modules.ni.omc.system.core.alarm.in.AlarmMsgDecoder;
import com.ltln.modules.ni.omc.system.core.alarm.in.AlarmServerHandler;
import com.ltln.modules.ni.omc.system.core.alarm.in.HeartBeatIdleChkHandler;
import com.ltln.modules.ni.omc.system.core.alarm.out.AlarmMsgEncoder;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.util.Constants;

/**
 * Creates a newly configured {@link ChannelPipeline} for a server-side channel.
 */
public class AlarmServerInitializer extends ChannelInitializer<SocketChannel> {
	
	public final static String ALARM_SERVER_HANDLER = "alarm_server_handler";
	public final static String HEARTBEAT_IDLE_HANDLER = "heartbeat_idle_handler";
	
	final AlarmServerHandler shareableHandler = SelfBeanFactoryAware.getBean("alarmServerHandler");
	
	@Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new AlarmMsgDecoder(1048576,7,2,0,0,false));
        pipeline.addLast(new AlarmMsgEncoder());
        pipeline.addLast(ALARM_SERVER_HANDLER,shareableHandler);
        pipeline.addLast(HEARTBEAT_IDLE_HANDLER, new HeartBeatIdleChkHandler(Constants.ALM_HEARTBEAT_IDLE_TIME_SECONDS));
    }
}
