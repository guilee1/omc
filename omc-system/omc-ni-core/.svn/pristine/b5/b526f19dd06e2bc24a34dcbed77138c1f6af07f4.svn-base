package com.ltln.modules.ni.omc.core;

import java.util.List;

import com.ltln.modules.ni.omc.core.annotation.Facade;
import com.ltln.modules.ni.omc.core.vo.AlarmVo;

@Facade(serviceName="IAlmInf",serviceBean="almInfImpl")
public interface IAlmInf {

	List<AlarmVo> queryCurrentActiveAlarm(String beginTime,String endTime);
}
