/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.framework.aware.alarm;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.netmgt.server.faultd.Alarm;

/**
 *
 * @author Administrator
 */
public interface IAlarmConvert {
    public AlarmVo converToAlarmInfoModel(Alarm alarm);
}
