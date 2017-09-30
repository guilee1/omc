package com.ltln.modules.ni.omc.system.core.alarm.in.process;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.EStatus;
import com.ltln.modules.ni.omc.system.core.alarm.ICommandHandler;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.AckSyncAlarmMsg;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmMsg;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsCmd;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.ICmdParser;
import com.ltln.modules.ni.omc.system.core.cache.IAlmCache;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsgBuilder;
@Component
public class OmcSyncAlarmMsgProcessHandler implements ICommandHandler {

	@Autowired
	IAlmContext clients;
	
	@Autowired
	IAlmCache alarmCache;
	
	@Resource(name="syncAlarmMsgParser")
	ICmdParser parser;
	
	@Override
	public void process(ChannelHandlerContext channelHandlerContex, NmsMsg cmd) {
		ReqSyncAlarmMsg request = (ReqSyncAlarmMsg)parser.decode(cmd.getBody());
		AckSyncAlarmMsg response = new AckSyncAlarmMsg();
		response.setReqID(request.getReqID());
		String resDesc = StringUtils.EMPTY;
		response.setResult(AbsNmsCmd.RESULT_FAIL);
		AlarmClient client = clients.getClient(channelHandlerContex.channel().id());
		if(client==null){
			resDesc = String.format("not login");
		}else{
			if(request.badFormat()){
				resDesc = request.getErrorMsg();
			}else{
				if(client.getReqType()==AlarmClient.REAL_TIME_LOGIN){
					if(!client.isSyncBefore()){
						client.setStatus(EStatus.SUSPEND);
						List<AlarmVo> syncMsgList = alarmCache.find(request.getAlarmSeq());
						if(syncMsgList.isEmpty()){
							resDesc = String.format("not find seq start at %s",request.getAlarmSeq());
							client.setStatus(EStatus.RT_ALM_SEND);
						}else{
							client.setSyncBefore(true);
							client.pushSyncQueue(syncMsgList);
							response.setResult(AbsNmsCmd.RESULT_SUCC);
							Logger.info(String.format("connection [%s],find alarm list from cache which seq start at %s",channelHandlerContex.channel().remoteAddress().toString(), request.getAlarmSeq()));
						}
					}else{
						resDesc = String.format("has sync alarm before");
					}
				}else{
					resDesc = String.format("this is ftp TYPE");
				}
			}
		}
		if(StringUtils.isNotEmpty(resDesc)){
			Logger.info(resDesc);
			response.setResDesc(resDesc);
		}
		channelHandlerContex.writeAndFlush(NmsMsgBuilder.buildAckSyncAlarmMsg(parser.encode(response)));
		if(AbsNmsCmd.RESULT_SUCC.equals(response.getResult()))
			client.setStatus(EStatus.SYNC_ALM_SEND);
	}

}
