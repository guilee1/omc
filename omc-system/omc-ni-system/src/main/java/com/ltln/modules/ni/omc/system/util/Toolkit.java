package com.ltln.modules.ni.omc.system.util;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;

import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;

public final class Toolkit {

	@SuppressWarnings("deprecation")
	public static long computeDelayMill(String boomTime, int cycle) throws ParseException{
		Date _boomTime= OmcDateFormater.parseDelayDate(boomTime);
		Calendar   now = Calendar.getInstance();
		long delay = 0;
		switch(cycle){
		case 0:
			Calendar   boom = Calendar.getInstance();
			boom.set(Calendar.HOUR_OF_DAY, _boomTime.getHours());
			boom.set(Calendar.MINUTE, _boomTime.getMinutes());
			boom.set(Calendar.SECOND, _boomTime.getSeconds());
			if(boom.before(now))
				boom.add(Calendar.DATE, 1);
			delay = boom.getTimeInMillis()-now.getTimeInMillis();
			break;
		case 1:
			Calendar   monday = Calendar.getInstance();
			monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			monday.set(Calendar.HOUR_OF_DAY, _boomTime.getHours());
			monday.set(Calendar.MINUTE, _boomTime.getMinutes());
			monday.set(Calendar.SECOND, _boomTime.getSeconds());
			if(monday.before(now))
				monday.add(Calendar.DATE, 7);
			delay = monday.getTimeInMillis()-now.getTimeInMillis();
			break;
		default:
			Calendar   firstDayOfMonth = Calendar.getInstance();
			firstDayOfMonth.set(Calendar.DAY_OF_MONTH, 1);
			firstDayOfMonth.set(Calendar.HOUR_OF_DAY, _boomTime.getHours());
			firstDayOfMonth.set(Calendar.MINUTE, _boomTime.getMinutes());
			firstDayOfMonth.set(Calendar.SECOND, _boomTime.getSeconds());
			if(firstDayOfMonth.before(now))
				firstDayOfMonth.add(Calendar.MONTH, 1);
			delay = firstDayOfMonth.getTimeInMillis()-now.getTimeInMillis();
			break;
		}
		return delay;
	}
	
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	public static boolean chkIp(String ip) {
		String longreg = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
		Pattern longpat = Pattern.compile(longreg);
		Matcher longmat = longpat.matcher(ip);
		return longmat.find();
	}
	
	public static int doubleBytesToInt(byte[] src) {
		int value;	
		value = (int) ((src[1] & 0xFF) | ((src[0] & 0xFF)<<8));
		return value;
	}
	
	public static byte[] intToDoubleBytes( int value ) { 
		byte[] src = new byte[2];
		src[0] =  (byte) ((value>>8));  
		src[1] =  (byte) (value);				
		return src; 
	}
	
	public static int bytesToInt(byte src) {
		int value;	
		value = (int)(src & 0xFF);
		return value;
	}
	
	public static byte intToBytes( int value ) { 
		return (byte) (value & 0xFF); 
	}
	
	public static boolean chkNmsMsgStartSign(byte[] bytes){
		if(bytes.length!=2)
			return false;
		return (short)(doubleBytesToInt(bytes)) == NmsMsg.StartSign;
	}
	
	public static String removeEqualAnd(String searchStr,String fix){
		return searchStr.toLowerCase().replace(fix, "").replace("=", "").trim();
	}
	public static void main(String[] args) {
//		byte[] bytes = intTo4Bytes(1024);
//		
//		ByteBuffer  buf = ByteBuffer.allocate(4); 
//		buf.putInt(1024);
////		buf.flip();
//		bytes = buf.array();
//		System.out.println();
	}

	 public static byte[] intTo4Bytes(int value){
         byte[] src = new byte[4];
         src[0] = (byte)((value >> 24) & 0xFF);
         src[1] = (byte)((value >> 16) & 0xFF);
         src[2] = (byte)((value >> 8) & 0xFF);
         src[3] = (byte)(value & 0xFF);
         return src;
     }
	 
	public static List<String> removeSuffix(List<String> tmpFilePath) {
		List<String> result = new ArrayList<>();
		for (String tmpFile : tmpFilePath)
			result.add(removeSuffix(tmpFile));
		return result;
	}

	public static String removeSuffix(String filePath) {
		String targetName = FilenameUtils.removeExtension(filePath);
		new File(filePath).renameTo(new File(targetName));
		return targetName;
	}
	
	public static URL getImgPath(String imgName){
		return ClassLoader.getSystemResource("img/"+imgName);
	}
	
	public static boolean connectSelf(int port) {
		Socket client = null;
		try {
			client = new Socket("localhost", port);
		} catch (Exception e) {
			return false;
		} finally{
			try {
				if(client!=null)
					client.close();
			} catch (IOException e) {
				
			}
		}
		return true;
	}
}
