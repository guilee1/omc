package com.ltln.modules.ni.omc.system.core.telnet.cmd;

import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.AbsNmsReq;

public class ConfigNmsReq extends AbsNmsReq {

	private boolean show;
	private boolean more;
	private int timeout = -1;
	
	public boolean isMoreCfg(){
		return this.timeout == -1;
	}
	
	public boolean isMore() {
		return more;
	}
	public void setMore(boolean more) {
		this.more = more;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public boolean isShow() {
		return show;
	}

	public void setShow(boolean show) {
		this.show = show;
	}
}
