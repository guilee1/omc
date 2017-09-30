package com.ltln.modules.ni.omc.framework.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IPerfInf;
import com.ltln.modules.ni.omc.core.vo.PerformanceVo;
import com.ltln.modules.ni.omc.framework.locator.ILocator;

@Component("perfInfImpl")
public class PerfInfImpl implements IPerfInf {

	@Autowired
	ILocator locator;
	

	@Override
	public List<List<PerformanceVo>> queryHistoryPerf(String beginTime, String endTime) {
		List<List<PerformanceVo>> result = new ArrayList<>();
		List<IPerfInf> perfInfs = locator.getInstances(IPerfInf.class);
		for(IPerfInf perfInf : perfInfs){
			List<List<PerformanceVo>> oneList = perfInf.queryHistoryPerf(beginTime, endTime);
			if(oneList!=null&&!oneList.isEmpty())
				result.addAll(oneList);
		}
		return result;
	}

}
