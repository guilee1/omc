/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.core.vo;

import org.apache.commons.lang3.StringUtils;

public class PerformanceVo extends BaseVO {

	private static final long serialVersionUID = -8426756634961011990L;

	public static final String NA = "N/A";
	
	private String rmUid;
	private String monitorCycle;
	private String monitorType;
	private String monitorTime;
	private boolean workLsp;
	private String[] perfKey;
	private String[] perfValue;
	
	
	public boolean isWorkLsp() {
		return workLsp;
	}

	public void setWorkLsp(boolean workLsp) {
		this.workLsp = workLsp;
	}


	public String getMonitorCycle() {
		return monitorCycle;
	}

	public void setMonitorCycle(String monitorCycle) {
		this.monitorCycle = monitorCycle;
	}

	public String getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(String monitorType) {
		this.monitorType = monitorType;
	}

	public String getRmUid() {
		return rmUid;
	}

	public void setRmUid(String rmUid) {
		this.rmUid = rmUid;
	}

	public String getMonitorTime() {
		return monitorTime;
	}

	public void setMonitorTime(String monitorTime) {
		this.monitorTime = monitorTime;
	}

	public String[] getPerfKey() {
		return perfKey;
	}

	public void setPerfKey(String[] perfKey) {
		this.perfKey = perfKey;
	}

	public String[] getPerfValue() {
		return perfValue;
	}

	public void setPerfValue(String[] perfValue) {
		this.perfValue = perfValue;
	}



	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof PerformanceVo))
			return false;
		PerformanceVo other = (PerformanceVo)obj;
		if(StringUtils.equals(this.rmUid, other.getRmUid()))
			return true;
		return false;
	}


	@Override
	public String getResType() {
		return monitorType;
	}

	@Override
	public String getFileRmUID() {
		return this.monitorCycle;
	}
}
