package com.ltln.modules.ni.omc.system.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Constants {

	
	public static String rootDir = null;
	public static String usersDir;
	public static Map<String, FTPUserObj> FTPUSERS = new HashMap<>();
	public static Map<String, ResTaskObj> RESTASK = new HashMap<>();
	
	static Properties constantsProp = new Properties();
	static {
		rootDir = System.getProperty("user.dir");
		rootDir = rootDir.replace(File.separatorChar, '/');
		rootDir += "/";
		usersDir = null;
		usersDir = System.getProperty("user.dir");
		usersDir = usersDir.replace(File.separatorChar, '/');
		usersDir += "/";
		
//		init();
	}
	
	
	public static final String DUMP_DEFAULT_DIR = rootDir + "DB_BACKUP";
	
	
	
	//---------scheduler
	public static int SCHEDULER_THREAD_NUMBER;
	public static long ALM_SCHEDULER_ALMSEQ_PERIOD;
	
	public static  int ALARM_PORT;
	public static  int ALM_CACHE_SIZE;
	public static int ALM_RT_QUEUE_SIZE;
	public static int ALM_SEQ_SIZE;
	public static int ALM_LOG_SIZE;
	public static final String INF_PKG_NAME = "com.ltln.modules.ni.omc.core";
	public static  String END_TAG = "@";
	public static  String RMI_IP_ADDR;
	public static  String RMI_PORT;
	public static  String ENCODING;
	
	//--------------ftp cfg-----------------

	public static  int FTP_SERVER_PORT;
	public static  String FTP_SERVER_PATH;
	public static  String FTP_SERVER_ROOT_PATH;
	public static int FTP_SERVER_IDL_SEC;
	/*
	 * 	Resource CM
		Alarm	FM
		Performance	PM
	 */
	public static  String FTP_SERVER_PATH_FM;
	public static  String FTP_SERVER_PATH_CM;
	public static  String FTP_SERVER_PATH_PM;
	
	
	public static  String HOST_NO ;
	public static  String VERSION ;
	
	//------------------alarm file setting
	public static boolean ALM_FILE_HEADER_WITH_NAME;
	public static  long MAX_FILE_SIZE;
	public static  int MAX_SINGLE_SIZE;//0x1288;
	public static  int MAX_FILE_LINE;
	
	
	public static  int ALM_HEARTBEAT_IDLE_TIME_SECONDS;
	public static  String MYSQL_USERNAME;
	public static  String MYSQL_PASSWORD;
	
	public static int FILE_TMP_KEEP_SEC;
	
	//-----------------instruction
	public static  int TELNET_IDLE_TIME_SECONDS;
	public static int TELNET_WAIT_TIME_MS;
	public static  boolean INSTRUCTION_SSL;
	public static  int INSTRUCTION_SSL_PORT;
	public static  int INSTRUCTION_PORT;
	public static  String INSTRUCTION_CMD_SPLITER;
	public static String INSTRUCTION_OPERATION_SPLITER;
	
	public static int INSTRUCTION_DEFAULT_DEVICE_PORT;
	public static  String INSTRUCTION_DEFAULT_DEVICE_USERNAME;
	public static  String INSTRUCTION_DEFAULT_DEVICE_PWD;
	
	
	//-----------DUMP----
	public static  boolean DUMP_ALM_SEQ_TBL;
	public static String DUMP_ALM_SEQ_TBL_DIRECTORY;
	public static String DUMP_ALM_SEQ_TBL_TIME;
	public static int DUMP_ALM_SEQ_TBL_CYCLE;
	public static String DUMP_ALM_SEQ_TBL_NAME;
	
	public static  boolean DUMP_ALM_LOG_TBL;
	public static String DUMP_ALM_LOG_TBL_DIRECTORY;
	public static String DUMP_ALM_LOG_TBL_TIME;
	public static int DUMP_ALM_LOG_TBL_CYCLE;
	public static String DUMP_ALM_LOG_TBL_NAME;
	
	public static  boolean DUMP_SYS_LOG_TBL;
	public static String DUMP_SYS_LOG_TBL_DIRECTORY;
	public static String DUMP_SYS_LOG_TBL_TIME;
	public static int DUMP_SYS_LOG_TBL_CYCLE;
	public static String DUMP_SYS_LOG_TBL_NAME;
	
	public static boolean DUMP_FILEBACKUP_TBL;
	public static String DUMP_FILEBACKUP_TBL_DIRECTORY;
	public static String DUMP_FILEBACKUP_TBL_TIME;
	public static int DUMP_FILEBACKUP_TBL_CYCLE;
	public static String DUMP_FILEBACKUP_TBL__NAME;
	
	//语言选择
	public static int LANGUAGE_SELECT;
	public static boolean ALM_LOGIN_MODEL;
	public static int ALM_FILE_MAX_QUERY_LINE;
	public static boolean ALM_ACTIVE_ALM_QUERY_MODEL;
	public static boolean FILE_PAGED_MODEL;
	public static String VENDOR_NAME;
	public static String ELEMENT_TYPE;
	public static String PM_VERSION;
	
	public static void init(){
		InputStream constantsIn = null;
		try {
			constantsIn = new BufferedInputStream(new FileInputStream(Constants.rootDir +"envconf/omc-ni-system.properties"));
			try {
				constantsProp.load(constantsIn);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				if(null!=constantsIn)
					constantsIn.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		try {
			constantJoin(constantsProp);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static void constantJoin(Properties constantsProp)throws Exception{
		Map<String, String> constantsMap = new HashMap<String, String>();
		
		Set<String> constantsNames = constantsProp.stringPropertyNames();
		for(String name : constantsNames){
			String constantValue = constantsProp.getProperty(name);
			constantsMap.put(name, constantValue);
			if(name.startsWith("FTP.")){
				String key = name.substring(name.lastIndexOf(".")+1);
				FTPUserObj userObj = creIfNotExist(FTPUSERS,key,FTPUserObj.class);
				setFtpUserProperty(userObj,name,constantValue);
			}
			else if(name.startsWith("DUMP_")){
				String key = StringUtils.substringBetween(name, "DUMP_", "_TBL");
				ResTaskObj resObj = creIfNotExist(RESTASK,key,ResTaskObj.class);
				setTaskUserProperty(resObj,name,constantValue);
			}
		}
		

		SCHEDULER_THREAD_NUMBER = Integer.parseInt(constantsMap.get("SCHEDULER_THREAD_NUMBER"));

		ALM_SCHEDULER_ALMSEQ_PERIOD = Long.parseLong(constantsMap.get("ALM_SCHEDULER_ALMSEQ_PERIOD"));
		
		ALM_FILE_HEADER_WITH_NAME = StringUtils.equals(constantsMap.get("ALM_FILE_HEADER_WITH_NAME"),"true");
		ALARM_PORT = Integer.parseInt(constantsMap.get("ALARM_PORT"));
		ALM_CACHE_SIZE = Integer.parseInt(constantsMap.get("ALM_CACHE_SIZE"));
		ALM_RT_QUEUE_SIZE = Integer.parseInt(constantsMap.get("ALM_RT_QUEUE_SIZE"));
		ALM_SEQ_SIZE = Integer.parseInt(constantsMap.get("ALM_SEQ_SIZE"));
		ALM_LOG_SIZE = Integer.parseInt(constantsMap.get("ALM_LOG_SIZE"));
		ALM_FILE_MAX_QUERY_LINE = Integer.parseInt(constantsMap.get("ALM_FILE_MAX_QUERY_LINE"));
		
		RMI_IP_ADDR = constantsMap.get("RMI_IP_ADDR");
		RMI_PORT = constantsMap.get("RMI_PORT");
		ENCODING = constantsMap.get("ENCODING");
		
		END_TAG = constantsMap.get("END_TAG");
		
		VENDOR_NAME = constantsMap.get("VENDOR_NAME");
		ELEMENT_TYPE = constantsMap.get("ELEMENT_TYPE");
		PM_VERSION = constantsMap.get("PM_VERSION");
		
		FTP_SERVER_PORT = Integer.parseInt(constantsMap.get("FTP_SERVER_PORT"));
		FTP_SERVER_IDL_SEC = Integer.parseInt(constantsMap.get("FTP_SERVER_IDL_SEC"));
		FTP_SERVER_ROOT_PATH = rootDir+constantsMap.get("FTP_SERVER_ROOT_PATH");
		FTP_SERVER_PATH = FTP_SERVER_ROOT_PATH+constantsMap.get("FTP_SERVER_PATH");
		FTP_SERVER_PATH_FM = FTP_SERVER_PATH+constantsMap.get("FTP_SERVER_PATH_FM");
		FTP_SERVER_PATH_CM = FTP_SERVER_PATH+constantsMap.get("FTP_SERVER_PATH_CM");
		FTP_SERVER_PATH_PM = FTP_SERVER_PATH+constantsMap.get("FTP_SERVER_PATH_PM");
		HOST_NO = constantsMap.get("HOST_NO");
		VERSION = constantsMap.get("VERSION");
		
//		判断MAX_FILE_SIZE值是否为正确16进制数
		String longreg = "^0x[0-9,a-f,A-F]{1,}$"; 
		Pattern longpat = Pattern.compile(longreg);
		Matcher longmat = longpat.matcher(constantsMap.get("MAX_FILE_SIZE"));
		boolean flag = longmat.find();
		if(flag){
			String[] longstr = constantsMap.get("MAX_FILE_SIZE").split("x");
			MAX_FILE_SIZE = Long.parseLong(longstr[1],16);
		}else{
			MAX_FILE_SIZE = 0;
		}
		
		
		MAX_SINGLE_SIZE = Integer.parseInt(constantsMap.get("MAX_SINGLE_SIZE"));
		MAX_FILE_LINE = Integer.parseInt(constantsMap.get("MAX_FILE_LINE"));
		ALM_HEARTBEAT_IDLE_TIME_SECONDS = Integer.parseInt(constantsMap.get("ALM_HEARTBEAT_IDLE_TIME_SECONDS"));
		MYSQL_USERNAME = constantsMap.get("MYSQL_USERNAME");
		MYSQL_PASSWORD = constantsMap.get("MYSQL_PASSWORD");
		
		FILE_TMP_KEEP_SEC = Integer.parseInt(constantsMap.get("FILE_TMP_KEEP_SEC"));
		
		TELNET_IDLE_TIME_SECONDS = Integer.parseInt(constantsMap.get("TELNET_IDLE_TIME_SECONDS"));
		TELNET_WAIT_TIME_MS = Integer.parseInt(constantsMap.get("TELNET_WAIT_TIME_MS"));
		INSTRUCTION_SSL = StringUtils.equals(constantsMap.get("INSTRUCTION_SSL"),"true");
		INSTRUCTION_SSL_PORT = Integer.parseInt(constantsMap.get("INSTRUCTION_SSL_PORT"));
		INSTRUCTION_PORT = Integer.parseInt(constantsMap.get("INSTRUCTION_PORT"));
		INSTRUCTION_CMD_SPLITER = constantsMap.get("INSTRUCTION_CMD_SPLITER");
		INSTRUCTION_OPERATION_SPLITER = constantsMap.get("INSTRUCTION_OPERATION_SPLITER");
		INSTRUCTION_DEFAULT_DEVICE_PORT = Integer.parseInt(constantsMap.get("INSTRUCTION_DEFAULT_DEVICE_PORT"));
		INSTRUCTION_DEFAULT_DEVICE_USERNAME = constantsMap.get("INSTRUCTION_DEFAULT_DEVICE_USERNAME");
		INSTRUCTION_DEFAULT_DEVICE_PWD = constantsMap.get("INSTRUCTION_DEFAULT_DEVICE_PWD");
	
		DUMP_ALM_SEQ_TBL = StringUtils.equals(constantsMap.get("DUMP_ALM_SEQ_TBL"),"true");
		DUMP_ALM_SEQ_TBL_DIRECTORY = constantsMap.get("DUMP_ALM_SEQ_TBL_DIRECTORY");
		DUMP_ALM_SEQ_TBL_TIME = constantsMap.get("DUMP_ALM_SEQ_TBL_TIME");
		DUMP_ALM_SEQ_TBL_CYCLE = Integer.parseInt(constantsMap.get("DUMP_ALM_SEQ_TBL_CYCLE"));
		DUMP_ALM_SEQ_TBL_NAME = constantsMap.get("DUMP_ALM_SEQ_TBL_NAME");
		
		DUMP_ALM_LOG_TBL = StringUtils.equals(constantsMap.get("DUMP_ALM_LOG_TBL"),"true");
		DUMP_ALM_LOG_TBL_DIRECTORY = constantsMap.get("DUMP_ALM_LOG_TBL_DIRECTORY");
		DUMP_ALM_LOG_TBL_TIME = constantsMap.get("DUMP_ALM_LOG_TBL_TIME");
		DUMP_ALM_LOG_TBL_CYCLE = Integer.parseInt(constantsMap.get("DUMP_ALM_LOG_TBL_CYCLE"));
		DUMP_ALM_LOG_TBL_NAME = constantsMap.get("DUMP_ALM_LOG_TBL_NAME");
		
		DUMP_SYS_LOG_TBL = StringUtils.equals(constantsMap.get("DUMP_SYS_LOG_TBL"),"true");
		DUMP_SYS_LOG_TBL_DIRECTORY = constantsMap.get("DUMP_SYS_LOG_TBL_DIRECTORY");
		DUMP_SYS_LOG_TBL_TIME = constantsMap.get("DUMP_SYS_LOG_TBL_TIME");
		DUMP_SYS_LOG_TBL_CYCLE = Integer.parseInt(constantsMap.get("DUMP_SYS_LOG_TBL_CYCLE"));
		DUMP_SYS_LOG_TBL_NAME = constantsMap.get("DUMP_SYS_LOG_TBL_NAME");
		//filebackup
		DUMP_FILEBACKUP_TBL = StringUtils.equals(constantsMap.get("DUMP_FILEBACKUP_TBL"),"true");
		DUMP_FILEBACKUP_TBL_DIRECTORY = constantsMap.get("DUMP_FILEBACKUP_TBL_DIRECTORY");
		DUMP_FILEBACKUP_TBL_TIME = constantsMap.get("DUMP_FILEBACKUP_TBL_TIME");
		DUMP_FILEBACKUP_TBL_CYCLE = Integer.parseInt(constantsMap.get("DUMP_FILEBACKUP_TBL_CYCLE"));
		DUMP_FILEBACKUP_TBL__NAME = constantsMap.get("DUMP_FILEBACKUP_TBL_NAME");
		
		LANGUAGE_SELECT = Integer.parseInt(constantsMap.get("LANGUAGE_SELECT"));
		ALM_LOGIN_MODEL = Boolean.parseBoolean(constantsMap.get("ALM_LOGIN_MODEL"));
		ALM_ACTIVE_ALM_QUERY_MODEL = Boolean.parseBoolean(constantsMap.get("ALM_ACTIVE_ALM_QUERY_MODEL"));
		FILE_PAGED_MODEL = Boolean.parseBoolean(constantsMap.get("FILE_PAGED_MODEL"));
	}
	

	private static <T> T creIfNotExist(Map<String, T> fTPUSERS2,String key,Class<T> clazz) {
		T resultObj = fTPUSERS2.get(key);
		if(null==resultObj){
			try {
				resultObj = clazz.newInstance();
			} catch (InstantiationException |IllegalAccessException e) {
				e.printStackTrace();
			} 
			fTPUSERS2.put(key, resultObj);
		}
		return resultObj;
	}
	private static void setFtpUserProperty(FTPUserObj userObj, String name,String constantValue) {
		String[]triple = name.split("\\.");
		if("account".equalsIgnoreCase(triple[1]))
			userObj.setAccount(constantValue);
		else if("pwd".equalsIgnoreCase(triple[1]))
			userObj.setPwd(constantValue);
		else if("dir".equalsIgnoreCase(triple[1]))
			userObj.setRootDir(constantValue);
		else if("permission".equalsIgnoreCase(triple[1]))
			userObj.setPermission(Boolean.parseBoolean(constantValue));
		else
			throw new RuntimeException("bad ftp user format!");
	}
	
	private static void setTaskUserProperty(ResTaskObj resObj, String name,String constantValue) {
		if(name.endsWith("TBL")){
			resObj.setStart(Boolean.parseBoolean(constantValue));
		}else if(name.endsWith("DIRECTORY")){
			resObj.setDir(constantValue);
		}else if(name.endsWith("TIME")){
			resObj.setTime(constantValue);
		}else if(name.endsWith("CYCLE")){
			resObj.setCycle(Integer.parseInt(constantValue));
		}else if(name.endsWith("NAME")){
			resObj.setName(constantValue);
		}
	}
	
	public static void save(){
		removeProperty(constantsProp);
		for(FTPUserObj userObj:FTPUSERS.values()){
			constantsProp.put("FTP.ACCOUNT."+userObj.getAccount(), userObj.getAccount());
			constantsProp.put("FTP.PWD."+userObj.getAccount(), userObj.getPwd());
			constantsProp.put("FTP.DIR."+userObj.getAccount(), userObj.getRootDir());
			constantsProp.put("FTP.PERMISSION."+userObj.getAccount(), String.valueOf(userObj.isPermission()));
		}
		for(String key:RESTASK.keySet()){
			ResTaskObj value = RESTASK.get(key);
			constantsProp.put("DUMP_"+key+"_TBL", String.valueOf(value.isStart()));
			constantsProp.put("DUMP_"+key+"_TBL_DIRECTORY", value.getDir());
			constantsProp.put("DUMP_"+key+"_TBL_TIME", value.getTime());
			constantsProp.put("DUMP_"+key+"_TBL_CYCLE", String.valueOf(value.getCycle()));
			constantsProp.put("DUMP_"+key+"_TBL_NAME", value.getName());
		}
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(Constants.rootDir +"envconf/omc-ni-system.properties");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			constantsProp.store(bos, "");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fos!=null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void removeProperty(Properties constantsProp2) {
		for(String key : constantsProp2.stringPropertyNames()){
			if(StringUtils.containsIgnoreCase(key, "FTP.") || StringUtils.startsWithIgnoreCase(key, "DUMP_"))
				constantsProp2.remove(key);
		}
	}
	
	public static void main(String[] args) {
		Constants.init();
		System.out.println(constantsProp.getProperty("LANGUAGE_SELECT"));
		System.out.println(System.currentTimeMillis());
	}
	
	//语言设置
	public static void setLanguage(int i){
		LANGUAGE_SELECT = i;
		constantsProp.setProperty("LANGUAGE_SELECT", String.valueOf(i));
	}
	
	public static void setInstructionPort(String string) {
		INSTRUCTION_PORT = Integer.parseInt(string);
		constantsProp.setProperty("INSTRUCTION_PORT", string);
	}
	public static void setRemoteIp(String text) {
		RMI_IP_ADDR = text;
		constantsProp.setProperty("RMI_IP_ADDR", text);
	}
	public static void setRemotePort(String text) {
		RMI_PORT = text;
		constantsProp.setProperty("RMI_PORT", text);
	}
	public static void setAlarmPort(String string) {
		ALARM_PORT = Integer.parseInt(string);
		constantsProp.setProperty("ALARM_PORT", string);
	}
	public static void setFtpPort(String string) {
		FTP_SERVER_PORT = Integer.parseInt(string);
		constantsProp.setProperty("FTP_SERVER_PORT", string);
	}
	
	

	
}
