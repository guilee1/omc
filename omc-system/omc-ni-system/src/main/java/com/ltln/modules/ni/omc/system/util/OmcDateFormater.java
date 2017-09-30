package com.ltln.modules.ni.omc.system.util;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class OmcDateFormater {

	private final static SimpleDateFormat baseDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat fileDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat dirDateFormat = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat perfDirDateFormat = new SimpleDateFormat("yyyyMMddHH");
	private final static SimpleDateFormat delayDateFormat = new SimpleDateFormat("HH:mm:ss");
	private final static Format deFormat = new DecimalFormat("000");
	
	public static synchronized Date parseBasicDate(String d) throws ParseException{
		return baseDateFormat.parse(d);
	}
	
	public static synchronized String formatBasicDate(Object d){
		return baseDateFormat.format(d);
	}
	
	public static synchronized String formatFileDate(Object d){
		return fileDateFormat.format(d);
	}
	
	public static synchronized String formatDirDate(Date d){
		return dirDateFormat.format(d);
	}
	
	public static synchronized String formatPerfDirDate(Date d){
		return perfDirDateFormat.format(d);
	}
	
	public static synchronized Date parseDelayDate(String d) throws ParseException{
		return delayDateFormat.parse(d);
	}
	
	public static synchronized String formatDelayDate(Date d){
		return delayDateFormat.format(d);
	}
	
	public static synchronized String formatDecimal(int num){
		return deFormat.format(num);
	}
	
}
