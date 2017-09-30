package com.ltln.modules.ni.omc.system.core.listener;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class ConnectionModel {

	private final String ipAddr;
	
	private final EConnectionType type;
	
	private final String user;
	
	private final Date time = new Date();

	public ConnectionModel(String ip, EConnectionType type,String u) {
		if(StringUtils.isEmpty(ip) || type == null)
			throw new RuntimeException("empty ipAddress or type input?");
		this.ipAddr = ip;
		this.type = type;
		this.user = u;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public EConnectionType getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object obj) {
		ConnectionModel other = (ConnectionModel)obj;
		if(other==null)return false;
		if(StringUtils.isEmpty(other.getIpAddr()) || other.getType()==null)
			return false;
		if(StringUtils.isEmpty(this.getIpAddr()) || this.getType()==null)
			return false;
		if(other.getIpAddr().equals(this.ipAddr) && other.getType().ordinal()==this.type.ordinal())
			return true;
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ipAddr == null) ? 0 : ipAddr.hashCode());
		result = prime * result + ((type == null) ? 0 : type.ordinal());
		return result;
	}

	public String getUser() {
		return user;
	}

	public Date getTime() {
		return time;
	}
}
