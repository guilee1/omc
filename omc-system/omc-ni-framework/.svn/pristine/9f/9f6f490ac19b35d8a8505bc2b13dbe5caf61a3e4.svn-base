package com.ltln.modules.ni.omc.framework.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IAlmInf;
import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.framework.locator.ILocator;

@Component("almInfImpl")
public class AlmInfImpl implements IAlmInf {

	@Autowired
	ILocator locator;
	
	@Override
	public List<AlarmVo> queryCurrentActiveAlarm(String beginTime,String endTime) {
		List<AlarmVo> result = new ArrayList<>();
		List<IAlmInf> resInfs = locator.getInstances(IAlmInf.class);
		for(IAlmInf resInf : resInfs){
			List<AlarmVo> oneList = resInf.queryCurrentActiveAlarm(beginTime, endTime);
			if(oneList!=null&&!oneList.isEmpty())
				result.addAll(oneList);
		}
		return result;
	}

}
