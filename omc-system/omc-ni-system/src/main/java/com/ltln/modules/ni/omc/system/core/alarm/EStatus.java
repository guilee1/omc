package com.ltln.modules.ni.omc.system.core.alarm;

public enum EStatus {

	RT_ALM_SEND(0),
	SUSPEND(1),
	SYNC_ALM_SEND(2);
	
	int value;
	
	private EStatus(int v) {
		this.value = v;
	}
	
	public int getValue(){
		return this.value;
	}
}
