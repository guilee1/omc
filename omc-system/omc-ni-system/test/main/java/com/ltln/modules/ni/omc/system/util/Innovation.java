package com.ltln.modules.ni.omc.system.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;

public class Innovation {

	public static void test(List<AlarmVo> objList){
		objList = new ArrayList<>();
	}
	
	public static void doTest(){
		List<AlarmVo> actualList = new ArrayList<>();
		actualList.add(new AlarmVo());
		test(actualList);
		System.out.println(actualList.size());
	}
	
	public static void main(String[] args) throws Exception {
//		Calendar c = Calendar.getInstance();
//		c.setTime();
		Date dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2017-5-11 13:50:54");
		System.out.println(dd.getTime());
		
		Date d = new Date(1494918200727L);
		System.out.println(d.toLocaleString());
	}

	static class Test implements Runnable{

		@Override
		public void run() {
			throw new RuntimeException("error here for ~");
		}
		
	}
//	List<AlarmVo> cutOff(List<AlarmVo> alarmList,long maxFileSize, int maxSingleSize) {
//		int numberOfMaxSingle = (int) ((maxFileSize / maxSingleSize) + ((maxFileSize % maxSingleSize==0)?0:1));
//		List<AlarmVo> actualList = alarmList.subList(0, numberOfMaxSingle);
//		alarmList = alarmList.subList(numberOfMaxSingle, alarmList.size());
//		long sizeOfActual = sizeOfActual(actualList);
//		if(sizeOfActual < maxFileSize)
//				actualList.addAll(cutOff(alarmList,maxFileSize-sizeOfActual,maxSingleSize));
//		return actualList;
//	}
//
//	long sizeOfActual(List<AlarmVo> actualList) {
//		long sizeOf = 0;
//		for (AlarmVo vo : actualList)
//			sizeOf += vo.toString().getBytes().length;
//		return sizeOf;
//	}

	
	
}
