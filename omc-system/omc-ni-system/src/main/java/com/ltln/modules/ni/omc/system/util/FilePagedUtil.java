package com.ltln.modules.ni.omc.system.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.core.vo.PerformanceVo;
import com.ltln.modules.ni.omc.system.core.log.Logger;

public class FilePagedUtil {
	

	public static <T> List<List<T>> pageAlarmList(List<T> alarmList,long maxFileSize, int maxSingleSize) {
		List<List<T>> result = new ArrayList<>();
		long sizeOf = sizeOf(alarmList);
		if(sizeOf <= maxFileSize)
			result.add(alarmList);
		else{
			List<T> onePageAlmList = cutOff(alarmList,maxFileSize,maxSingleSize);
			if(!onePageAlmList.isEmpty())
				result.add(onePageAlmList);
			if(!alarmList.isEmpty())
				result.addAll(pageAlarmList(alarmList,maxFileSize,maxSingleSize));
		}
		return result;
	}

	static <T> List<T> cutOff(List<T> alarmList,long maxFileSize, int maxSingleSize) {
		int numberOfMaxSingle = (int) ((maxFileSize / maxSingleSize) + ((maxFileSize % maxSingleSize==0)?0:1));
		List<T> expectList = new LinkedList<>();
		if(alarmList.isEmpty())
			return expectList;
		for(int i=0;i<numberOfMaxSingle;++i){
			if(alarmList.isEmpty())
				break;
			expectList.add(alarmList.remove(0));
		}
		long sizeOfExpect = sizeOf(expectList);
		if(sizeOfExpect<maxFileSize){
			List<T> moreExpectList = cutOff(alarmList,maxFileSize-sizeOfExpect,maxSingleSize);
			if(!moreExpectList.isEmpty())
				expectList.addAll(moreExpectList);
		}
		return expectList;
	}

	public  static <T> long sizeOf(final List<T> alarmList) {
		//here are a geek code~
		if(alarmList.size() >= Constants.MAX_FILE_LINE)
			return Constants.MAX_FILE_SIZE + 1;
		
		long sizeOf = 0;
		for(T vo : alarmList){
			try {
				String str = formatObj2Str(vo);
				sizeOf += str.getBytes(Constants.ENCODING).length;
			} catch (UnsupportedEncodingException e) {
				Logger.error(e);
			}
		}
		return sizeOf;
	}

	private static <T> String formatObj2Str(T vo) {
		if(vo instanceof AlarmVo)
			return vo.toString();
		else if(vo instanceof PerformanceVo)
			return PerfFileParser.getData((PerformanceVo)vo);
		else
			return ResFileUtil.getObjectToXml(ResFileUtil.buildFieldObject((BaseVO)vo));
	}

}
