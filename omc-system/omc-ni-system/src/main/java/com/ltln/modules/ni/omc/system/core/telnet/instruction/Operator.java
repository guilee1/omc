package com.ltln.modules.ni.omc.system.core.telnet.instruction;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.system.core.telnet.client.ITelnetClient;
import com.ltln.modules.ni.omc.system.util.Constants;


public class Operator {

	String uid;
	String userName = StringUtils.EMPTY;
	String password;
	String ipAddr;
	boolean more;
	int timeOut = Constants.TELNET_IDLE_TIME_SECONDS;
	boolean isLogin;
	String loginTime;
	ITelnetClient telClient;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIpAddr() {
		return ipAddr;
	}
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}
	public boolean isMore() {
		return more;
	}
	public void setMore(boolean more) {
		this.more = more;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public ITelnetClient getTelClient() {
		return telClient;
	}
	public void setTelClient(ITelnetClient telClient) {
		this.telClient = telClient;
	}
	
}
