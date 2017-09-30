package com.ltln.modules.ni.omc.system.core.scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.core.IAlmInf;
import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.AckSyncAlarmFileResult;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqSyncAlarmFile;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.cache.ActiveAlmCache;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.file.FileWriterReturnValue;
import com.ltln.modules.ni.omc.system.core.file.IFileWriter;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class SyncAlarmFileTask implements Callable<AckSyncAlarmFileResult> {

	ReqSyncAlarmFile request;
	
	ILocator locator;
	
	IOmcDao dao;
	
	IFileWriter<AlarmVo> fileWriter;
	
	ActiveAlmCache cache;
	
	AlarmClient client;
	
	public SyncAlarmFileTask(ReqSyncAlarmFile request,AlarmClient c) {
		this.request = request;
		locator = SelfBeanFactoryAware.getBean("serviceLocator");
		dao = SelfBeanFactoryAware.getDao();
		fileWriter = SelfBeanFactoryAware.getBean("alarmFileWriter");
		cache = SelfBeanFactoryAware.getBean("activeAlmCache");
		client = c;
	}
	
	@Override
	public AckSyncAlarmFileResult call() throws Exception {
		AckSyncAlarmFileResult finalResponse = new AckSyncAlarmFileResult();
		finalResponse.setReqID(request.getReqId());
		List<AlarmVo> alarmList = new ArrayList<>();
		int totalCount = 0;
		if(request.getSyncSource()==0){
			alarmList = Constants.ALM_ACTIVE_ALM_QUERY_MODEL?
						cache.getCurrentActiveAlm(request.getStartTime(), request.getEndTime())
						:queryOmcCurrentActiveAlm(locator);
		}else {
			totalCount = queryCountFromDb(request,dao);
		}
		if(alarmList.isEmpty() && totalCount==0){
			finalResponse.setResult(AckSyncAlarmFileResult.RESULT_FAIL);
			String resDesc = String.format("can not search special alarm list!");
			Logger.info(resDesc);
			finalResponse.setResDesc(resDesc);
		}else{
			List<String> fileFullPath = new ArrayList<>();
			if(request.getSyncSource()==0){
				alarmList.get(0).setIpAddress(client.getUserName());//set userName
				FileWriterReturnValue<AlarmVo> fwrValue = fileWriter.makeFile(alarmList,1);
				fileFullPath = fwrValue.getFilePath();
			}else {
				int maxSingleQuery = Constants.ALM_FILE_MAX_QUERY_LINE;
				int pagedNo = (int) ((totalCount / maxSingleQuery) + ((totalCount % maxSingleQuery==0)?0:1));
				int startNo = 1;
				List<AlarmVo> lastQueryUnPagedList = new ArrayList<>();
				for(int i=0;i<pagedNo;++i){
					List<AlarmVo> onePagedAlarm = queryFromDb(request, dao,maxSingleQuery*i,maxSingleQuery);
					if(!lastQueryUnPagedList.isEmpty()){
						onePagedAlarm.addAll(0,lastQueryUnPagedList);
					}
					onePagedAlarm.get(0).setIpAddress(client.getUserName());//set userName
					FileWriterReturnValue<AlarmVo> fwrValue = fileWriter.makeFile(onePagedAlarm,startNo);
					startNo = fwrValue.getFileNo();
					lastQueryUnPagedList = fwrValue.getLastReturnValue();
					if(!fwrValue.getFilePath().isEmpty()){
						fileFullPath.addAll(fwrValue.getFilePath());
					}
				}
				if(!lastQueryUnPagedList.isEmpty()){
					lastQueryUnPagedList.get(0).setIpAddress(client.getUserName());//set userName
					FileWriterReturnValue<AlarmVo> fwrValue = fileWriter.makeFile(lastQueryUnPagedList,startNo);
					if(!fwrValue.getFilePath().isEmpty()){
						fileFullPath.addAll(fwrValue.getFilePath());
					}
				}
			}
			Thread.sleep(Constants.FILE_TMP_KEEP_SEC*1000);
			//remove zip file suffix here
			fileFullPath = Toolkit.removeSuffix(fileFullPath);
			if(!fileFullPath.isEmpty()){
				String allRelatedPath = StringUtils.EMPTY;
				for(String filePath : fileFullPath){
					String relatedPath = "/"+filePath.replace(Constants.rootDir, "");
					if(StringUtils.isNotEmpty(allRelatedPath))
						allRelatedPath += ","+relatedPath;
					else
						allRelatedPath += relatedPath;
				}
				finalResponse.setFileName(allRelatedPath);
				finalResponse.setResult(AckSyncAlarmFileResult.RESULT_SUCC);
				Logger.info(String.format("succ search alm list file is:%s",allRelatedPath));
			}else{
				finalResponse.setResult(AckSyncAlarmFileResult.RESULT_FAIL);
				String resDesc = String.format("make file error!pls see omc-ni log to chk~");
				Logger.info(resDesc);
				finalResponse.setResDesc(resDesc);
			}
		}
		return finalResponse;
	}
	

	private int queryCountFromDb(ReqSyncAlarmFile request2, IOmcDao dao2) {
		try{
			if(request.getAlarmSeq()!=-1){
				return dao.queryAlarmCountBySeq(request.getAlarmSeq());
			}else{
				return dao.queryAlarmCountByTime(OmcDateFormater.parseBasicDate(request.getStartTime()).getTime(), OmcDateFormater.parseBasicDate(request.getEndTime()).getTime());
			}
		}catch (Exception e) {
			Logger.error("invoke mysql exception~", e);
		}
		return 0;
	}


	private List<AlarmVo> queryFromDb(ReqSyncAlarmFile request,IOmcDao dao,int startLine,int maxLine) {
		List<AlarmVo> alarmList = new ArrayList<>();
		try{
			if(request.getAlarmSeq()!=-1){
				alarmList = dao.queryAlarmBySeq(request.getAlarmSeq(),startLine,maxLine);
			}else{
				alarmList = dao.queryAlarmByTime(OmcDateFormater.parseBasicDate(request.getStartTime()).getTime(), OmcDateFormater.parseBasicDate(request.getEndTime()).getTime(),startLine,maxLine);
			}
		}catch (Exception e) {
			Logger.error("invoke mysql exception~", e);
		}
		return alarmList;
	}

	private List<AlarmVo> queryOmcCurrentActiveAlm(ILocator locator) {
		List<AlarmVo> alarmList = new ArrayList<>();
		try{
			IAlmInf almInf = locator.getInstances(IAlmInf.class);
			alarmList = almInf.queryCurrentActiveAlarm(request.getStartTime(), request.getEndTime());
		}catch (Exception e) {
			Logger.error("remote call exception~", e);
		}
		return alarmList;
	}


}
