package com.ltln.modules.ni.omc.system.nbi.msg;

import java.io.UnsupportedEncodingException;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public final class NmsMsg {

	public NmsMsg(EMsgType type, int timeStamp2, int length, String body2) {
		this.msgType = type;
		this.timeStamp = timeStamp2;
		this.lenOfBody = length;
		this.body = body2;
	}
	
	public NmsMsg(EMsgType type,String body){
		this.msgType = type;
		this.body = body;
		this.timeStamp = (int)(System.currentTimeMillis()/1000);
	}
	
	public static final short StartSign = (short)0xffff;
	private final EMsgType msgType;
	private final int timeStamp;
	private int lenOfBody;
	private final String body;
	
	
	public String getBody(){
		return this.body;
	}
	public EMsgType getMsgType() {
		return msgType;
	}
	
	public int getTimeStamp(){
		return this.timeStamp;
	}

	public byte[] toBytes() {
		byte[] sign = Toolkit.intToDoubleBytes(StartSign);
        byte msg = Toolkit.intToBytes(msgType.value);
        byte[] time = Toolkit.intTo4Bytes(timeStamp);

        byte[] buffer = null;
		try {
			buffer = this.body.getBytes(Constants.ENCODING);
		} catch (UnsupportedEncodingException e) {
			Logger.error("error occu in toBytes()", e);
		}
		byte[] len = Toolkit.intToDoubleBytes(buffer.length);
		
		byte[] result = new byte[9 + buffer.length];
		System.arraycopy(sign, 0, result, 0, 2);
		result[2] = msg;
		System.arraycopy(time, 0, result, 3, 4);
		System.arraycopy(len, 0, result, 7, 2);
		System.arraycopy(buffer, 0, result, 9, buffer.length);
		return result;
	}
	
}
