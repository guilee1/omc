/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.framework.cq;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.framework.start.NIUtil;


/**
 *
 * @author Administrator
 */
public class CqAlarmUtil {
    public static DataRecord convertAlarm(AlarmVo alarmInfo){
       
      DataRecord  dataRecord = new DataRecord();
      dataRecord.setStandard_flag("1");//是否标准化,取值1,2；1:未标准化；2：已标准化；
      dataRecord.setActive_status(String.valueOf(alarmInfo.getAlarmStatus()));//告警状态,0：网元自动清除1：活动告警
      dataRecord.setAlarm_fp(alarmInfo.getAlarmId());//告警dn/采集告警流水号
      dataRecord.setAlarm_c_fp(alarmInfo.getAlarmId());//告警dn/采集告警流水号
      dataRecord.setProvince_name(NIUtil.getNiProvince());//省
      dataRecord.setCity_name(NIUtil.getNiCity());//县
      dataRecord.setAlarm_source(NIUtil.getNIAlarmSource());//告警来源
      dataRecord.setVendor_name(NIUtil.getNIVendor_name());
      dataRecord.setEqp_label("TRANS_ELEMENT-"+alarmInfo.getNeUID()+"@"+ alarmInfo.getNeName());
      dataRecord.setEqp_ip(alarmInfo.getIpAddress());//网元IP
      dataRecord.setEqp_object_class("PTN");
      dataRecord.setResource_status(NIUtil.getNIResource_status());
      dataRecord.setAlarm_locate_object(alarmInfo.getObjectName());//告警定位对象名称
      dataRecord.setObject_class(getObjectClass(alarmInfo.getObjectType()));//告警对象类型
      dataRecord.setNe_status(alarmInfo.getNeType());//设备状态
      dataRecord.setNetwork_type("本地汇聚");//网络类型
      dataRecord.setOrg_type(getOrgType(alarmInfo.getAlarmType()));//告警类别0：未知1：设备告警2：性能告警
      dataRecord.setAlarm_title_text(alarmInfo.getAlarmTitle());//告警名称
      dataRecord.setVendor_severity(getVendorSeverity(alarmInfo.getOrigSeverity()));//厂家告警级别
      dataRecord.setOrg_severity(String.valueOf(alarmInfo.getOrigSeverity()));//告警级别
      dataRecord.setProbable_cause(alarmInfo.getSpecificProblem());//告警可能原因
      dataRecord.setOmc_alarm_id(alarmInfo.getSpecificProblemID());//厂家告警号
      dataRecord.setEvent_time(alarmInfo.getEventTime());//告警发生时间
      dataRecord.setExtra_string1(NIUtil.getEmsName());//EMS名称
      dataRecord.setAlarmObjectCuid(alarmInfo.getObjectUID());//告警对象ID
      dataRecord.setLayerRate(String.valueOf(alarmInfo.getLayer()));//层速率
      dataRecord.setVendorVersion(NIUtil.getEmsNBIVersion());//厂家版本号
      dataRecord.setFdn(alarmInfo.getAddInfo());//告警对象fdn
      dataRecord.setRelated_ne_fdn(alarmInfo.getAddInfo());//告警设备fdn
     return dataRecord;   
    }
    private static String getVendorSeverity(int origSeverity){
        switch(origSeverity){
            case 0:
                return "Critical";
            case 1:
                return "Major";
            case 2:
                return "Minor";
            case 3:
                return "Warning";
            case 4:
                return "Info";
            case 5:
                return "unknown";
        }
        return "unknown";
                
    }
    private static String getObjectClass(String objectType){
        switch(objectType){
            case "NEL":
                return "网元";
            case "EQH":
               return "板卡";
            case "CRD":
                    return "板卡";
            case "PRT":
                return "端口";
            case "ESP":
                return "以太网业务接入点";
            case "PSW":
                return "伪线";
            case "TNL":
                return "隧道";
            case "LBS":
                return "标签交换";
        }
        return "";
    }

    private static String getOrgType(String alarmType) {

        return "0";  
    }
}
