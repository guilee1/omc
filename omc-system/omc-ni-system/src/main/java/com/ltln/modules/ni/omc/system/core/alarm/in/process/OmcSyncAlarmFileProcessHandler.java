package com.ltln.modules.ni.omc.system.core.alarm.in.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.ICommandHandler;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.AckSyncAlarmFile;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.AckSyncAlarmFileResult;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmFile;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsCmd;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.ICmdParser;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.scheduler.IScheduler;
import com.ltln.modules.ni.omc.system.core.scheduler.SyncAlarmFileTask;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsgBuilder;

@Component
public class OmcSyncAlarmFileProcessHandler implements ICommandHandler {

	@Autowired
	IAlmContext clients;
	
	@Resource(name="syncAlarmFileParser")
	ICmdParser parser;
	
	@Autowired
	IScheduler scheduler;
	
	@Override
	public void process(final ChannelHandlerContext channelHandlerContex, NmsMsg cmd) {
		ReqSyncAlarmFile request = (ReqSyncAlarmFile)parser.decode(cmd.getBody());
		AckSyncAlarmFile response = new AckSyncAlarmFile();
		response.setReqID(request.getReqId());
		String resDesc = StringUtils.EMPTY;
		response.setResult(AbsNmsCmd.RESULT_FAIL);
		final AlarmClient client = clients.getClient(channelHandlerContex.channel().id());
		if(client == null){
			resDesc = String.format("not login");
		}else{
			if(request.badFormat()){
				resDesc = request.getErrorMsg();
			}else{
				if(client.getReqType()==AlarmClient.FILE_SYNC_LOGIN){
					if(!client.isSyncBefore()){
						scheduler.submit(new SyncAlarmFileTask(request,client)).addListener(new GenericFutureListener<Future<? super AckSyncAlarmFileResult>>() {
							@Override
							public void operationComplete(Future<? super AckSyncAlarmFileResult> paramF)throws Exception {
								if(paramF.isSuccess()){
									AckSyncAlarmFileResult finalResponse = (AckSyncAlarmFileResult)paramF.get();
									//setSyncBefore true if search and make file successfully~
									if(AbsNmsCmd.RESULT_SUCC.equalsIgnoreCase(finalResponse.getResult()))
											client.setSyncBefore(true);
									channelHandlerContex.writeAndFlush(NmsMsgBuilder.buildAckSyncAlarmFileResultMsg(parser.encode(finalResponse)));
								}
							}
						});
//						client.setSyncBefore(true);
						Logger.info(String.format("receive a reqSyncAlarmFile from [%s],begin making file", channelHandlerContex.channel().remoteAddress().toString()));
						response.setResult(AbsNmsCmd.RESULT_SUCC);
					}else{
						resDesc = String.format("has sync alarm before");
					}
				}else{
					resDesc = String.format("this connection is msg TYPE");
				}
			}
		}
		if(StringUtils.isNotEmpty(resDesc)){
			Logger.info(resDesc);
			response.setResDesc(resDesc);
		}
		channelHandlerContex.writeAndFlush(NmsMsgBuilder.buildAckSyncAlarmFileMsg(parser.encode(response)));
	}

}
