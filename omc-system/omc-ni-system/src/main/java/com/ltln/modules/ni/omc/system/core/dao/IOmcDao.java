package com.ltln.modules.ni.omc.system.core.dao;

import java.util.List;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;

public interface IOmcDao {

	void insertAlarmVo(List<AlarmVo> alm);
	
	void insertAlarmLog(List<AlarmLog> log);
	
	void insertSysLog(SysOperLog log);

	List<AlarmVo> queryAlarmBySeq(int alarmSeq,int startLine,int maxLine);

	List<AlarmVo> queryAlarmByTime(long startTime, long endTime,int startLine,int maxLine);

	void heartBeat();

	void truncateTable(String tblName);

	Integer getMaxSeqNo();

	int getUserCount(String userName, String pwd);
	
	void insertOmcUser(List<OmcUser> users);
	
	void delAllUsers();
	
	List<OmcUser> getAllUser();

	int queryAlarmCountBySeq(int alarmSeq);

	int queryAlarmCountByTime(long time, long time2);
	
	int getTotalSize(String tblName);
	
	List<SysOperLog> querySysOperLog(int from,int pageSize);
	
	List<AlarmVo> queryAlmLog(int from,int pageSize);

	List<AlarmLog> querySenderLog(int from, int pageSize);
}
