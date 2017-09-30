package com.ltln.modules.ni.omc.system.util;

import java.util.TimeZone;

import com.ltln.modules.ni.omc.core.vo.PerformanceVo;

public class PerfFileParser {

	public static String getHeader(PerformanceVo vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("TimeStamp=")
				.append(OmcDateFormater.formatBasicDate(System.currentTimeMillis()))
				.append("|TimeZone=").append(TimeZone.getDefault().getID()).append("|Period=")
				.append(vo.getMonitorCycle()).append("|VendorName=").append(Constants.VENDOR_NAME)
				.append("|ElementType=").append(Constants.ELEMENT_TYPE).append("|PmVersion=").append(Constants.PM_VERSION)
				.append("|ObjectType=" + vo.getResType());
		sb.append("\r\n");
		return sb.toString();
	}

	public static String getColumn(PerformanceVo vo) {
		StringBuilder sb = new StringBuilder();
		sb.append("startTime|");
		for (String fieldName : vo.getPerfKey()) {
			sb.append(fieldName).append("|");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("\r\n");
		return sb.toString();
	}
	
	public static String getData(PerformanceVo vo) {
		StringBuilder sb = new StringBuilder();
		sb.append(vo.getMonitorTime()).append("|");
		for (String value : vo.getPerfValue()) {
			if (PerformanceVo.NA.equals(value)) {
				sb.append("--|");
			} else {
				sb.append(value).append("|");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
