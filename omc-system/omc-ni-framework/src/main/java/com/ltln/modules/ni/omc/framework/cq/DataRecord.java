/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.framework.cq;

import javax.xml.bind.annotation.*;

/**
 *
 * @author Administrator
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dataRecord")
public class DataRecord {

    @XmlElement(name = "standard_flag")
    private String standard_flag = "";
    @XmlElement(name = "active_status")
    private String active_status = "";
    @XmlElement(name = "alarm_fp")
    private String alarm_fp = "";
    @XmlElement(name = "alarm_c_fp")
    private String alarm_c_fp = "";
    @XmlElement(name = "province_name")
    private String province_name = "";
    @XmlElement(name = "city_name")
    private String city_name = "";
    @XmlElement(name = "alarm_source")
    private String alarm_source = "";
    @XmlElement(name = "vendor_name")
    private String vendor_name = "";
    @XmlElement(name = "sys_vendor_id")
    private String sys_vendor_id = "";
    @XmlElement(name = "eqp_version")
    private String eqp_version = "";
    @XmlElement(name = "eqp_label")
    private String eqp_label = "";
    @XmlElement(name = "eqp_alias")
    private String eqp_alias = "";
    @XmlElement(name = "eqp_ip")
    private String eqp_ip = "";
    @XmlElement(name = "eqp_object_class")
    private String eqp_object_class = "";
    @XmlElement(name = "eqp_severity")
    private String eqp_severity = "";
    @XmlElement(name = "maintain_man")
    private String maintain_man = "";
    @XmlElement(name = "resource_status")
    private String resource_status = "";
    @XmlElement(name = "eqp_operation_status")
    private String eqp_operation_status = "";
    @XmlElement(name = "alarm_resource_status")
    private String alarm_resource_status = "";
    @XmlElement(name = "locate_info")
    private String locate_info = "";
    @XmlElement(name = "alarm_locate_object")
    private String alarm_locate_object = "";
    @XmlElement(name = "ne_alias")
    private String ne_alias = "";
    @XmlElement(name = "object_class")
    private String object_class = "";
    @XmlElement(name = "alarm_object_severity")
    private String alarm_object_severity = "";
    @XmlElement(name = "ne_status")
    private String ne_status = "";
    @XmlElement(name = "ne_admin_status")
    private String ne_admin_status = "";
    @XmlElement(name = "alarm_ne_status")
    private String alarm_ne_status = "";
    @XmlElement(name = "professional_type")
    private String professional_type = "";
    @XmlElement(name = "network_type")
    private String network_type = "";
    @XmlElement(name = "org_type")
    private String org_type = "";
    @XmlElement(name = "logic_alarm_type")
    private String logic_alarm_type = "";
    @XmlElement(name = "logic_sub_alarm_type")
    private String logic_sub_alarm_type = "";
    @XmlElement(name = "vendor_type")
    private String vendor_type = "";
    @XmlElement(name = "send_jt_flag")
    private String send_jt_flag = "";
    @XmlElement(name = "alarm_title_text")
    private String alarm_title_text = "";
    @XmlElement(name = "standard_alarm_name")
    private String standard_alarm_name = "";
    @XmlElement(name = "vendor_severity")
    private String vendor_severity = "";
    @XmlElement(name = "org_severity")
    private String org_severity = "";
    @XmlElement(name = "probable_cause")
    private String probable_cause = "";
    @XmlElement(name = "standard_alarm_id")
    private String standard_alarm_id = "";
    @XmlElement(name = "omc_alarm_id")
    private String omc_alarm_id = "";
    @XmlElement(name = "probable_cause_txt")
    private String probable_cause_txt = "";
    @XmlElement(name = "alarm_text")
    private String alarm_text = "";
    @XmlElement(name = "alarm_act_count")
    private String alarm_act_count = "";
    @XmlElement(name = "related_information")
    private String related_information = "";
    @XmlElement(name = "effect_ne")
    private String effect_ne = "";
    @XmlElement(name = "effect_service")
    private String effect_service = "";
    @XmlElement(name = "alarm_memo")
    private String alarm_memo = "";
    @XmlElement(name = "alarm_value")
    private String alarm_value = "";
    @XmlElement(name = "preprocess_manner")
    private String preprocess_manner = "";
    @XmlElement(name = "province_process_flag")
    private String province_process_flag = "";
    @XmlElement(name = "department_process_flag")
    private String department_process_flag = "";
    @XmlElement(name = "channel_type")
    private String channel_type = "";
    @XmlElement(name = "event_time")
    private String event_time = "";
    @XmlElement(name = "business_area")
    private String business_area = "";
    @XmlElement(name = "extra_string1")
    private String extra_string1 = "";
    @XmlElement(name = "extra_string2")
    private String extra_string2 = "";
    @XmlElement(name = "extra_string3")
    private String extra_string3 = "";
    @XmlElement(name = "extra_id1")
    private String extra_id1 = "";
    @XmlElement(name = "extra_id2")
    private String extra_id2 = "";
    @XmlElement(name = "extra_id3")
    private String extra_id3 = "";
    @XmlElement(name = "traphName")
    private String traphName = "";
    @XmlElement(name = "isPriorityMonitorsTraph")
    private String isPriorityMonitorsTraph = "";
    @XmlElement(name = "alarmObjectCuid")
    private String alarmObjectCuid = "";
    @XmlElement(name = "layerRate")
    private String layerRate = "";
    @XmlElement(name = "vendorAlarmId")
    private String vendorAlarmId = "";
    @XmlElement(name = "vendorVersion")
    private String vendorVersion = "";
    @XmlElement(name = "fdn")
    private String fdn = "";
    @XmlElement(name = "related_ne_fdn")
    private String related_ne_fdn = "";

    public String getStandard_flag() {
        return standard_flag;
    }

    public void setStandard_flag(String standard_flag) {
        this.standard_flag = standard_flag;
    }

    public String getActive_status() {
        return active_status;
    }

    public void setActive_status(String active_status) {
        this.active_status = active_status;
    }

    public String getAlarm_fp() {
        return alarm_fp;
    }

    public void setAlarm_fp(String alarm_fp) {
        this.alarm_fp = alarm_fp;
    }

    public String getAlarm_c_fp() {
        return alarm_c_fp;
    }

    public void setAlarm_c_fp(String alarm_c_fp) {
        this.alarm_c_fp = alarm_c_fp;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getAlarm_source() {
        return alarm_source;
    }

    public void setAlarm_source(String alarm_source) {
        this.alarm_source = alarm_source;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getSys_vendor_id() {
        return sys_vendor_id;
    }

    public void setSys_vendor_id(String sys_vendor_id) {
        this.sys_vendor_id = sys_vendor_id;
    }

    public String getEqp_version() {
        return eqp_version;
    }

    public void setEqp_version(String eqp_version) {
        this.eqp_version = eqp_version;
    }

    public String getEqp_label() {
        return eqp_label;
    }

    public void setEqp_label(String eqp_label) {
        this.eqp_label = eqp_label;
    }

    public String getEqp_alias() {
        return eqp_alias;
    }

    public void setEqp_alias(String eqp_alias) {
        this.eqp_alias = eqp_alias;
    }

    public String getEqp_ip() {
        return eqp_ip;
    }

    public void setEqp_ip(String eqp_ip) {
        this.eqp_ip = eqp_ip;
    }

    public String getEqp_object_class() {
        return eqp_object_class;
    }

    public void setEqp_object_class(String eqp_object_class) {
        this.eqp_object_class = eqp_object_class;
    }

    public String getEqp_severity() {
        return eqp_severity;
    }

    public void setEqp_severity(String eqp_severity) {
        this.eqp_severity = eqp_severity;
    }

    public String getMaintain_man() {
        return maintain_man;
    }

    public void setMaintain_man(String maintain_man) {
        this.maintain_man = maintain_man;
    }

    public String getResource_status() {
        return resource_status;
    }

    public void setResource_status(String resource_status) {
        this.resource_status = resource_status;
    }

    public String getEqp_operation_status() {
        return eqp_operation_status;
    }

    public void setEqp_operation_status(String eqp_operation_status) {
        this.eqp_operation_status = eqp_operation_status;
    }

    public String getAlarm_resource_status() {
        return alarm_resource_status;
    }

    public void setAlarm_resource_status(String alarm_resource_status) {
        this.alarm_resource_status = alarm_resource_status;
    }

    public String getLocate_info() {
        return locate_info;
    }

    public void setLocate_info(String locate_info) {
        this.locate_info = locate_info;
    }

    public String getAlarm_locate_object() {
        return alarm_locate_object;
    }

    public void setAlarm_locate_object(String alarm_locate_object) {
        this.alarm_locate_object = alarm_locate_object;
    }

    public String getNe_alias() {
        return ne_alias;
    }

    public void setNe_alias(String ne_alias) {
        this.ne_alias = ne_alias;
    }

    public String getObject_class() {
        return object_class;
    }

    public void setObject_class(String object_class) {
        this.object_class = object_class;
    }

    public String getAlarm_object_severity() {
        return alarm_object_severity;
    }

    public void setAlarm_object_severity(String alarm_object_severity) {
        this.alarm_object_severity = alarm_object_severity;
    }

    public String getNe_status() {
        return ne_status;
    }

    public void setNe_status(String ne_status) {
        this.ne_status = ne_status;
    }

    public String getNe_admin_status() {
        return ne_admin_status;
    }

    public void setNe_admin_status(String ne_admin_status) {
        this.ne_admin_status = ne_admin_status;
    }

    public String getAlarm_ne_status() {
        return alarm_ne_status;
    }

    public void setAlarm_ne_status(String alarm_ne_status) {
        this.alarm_ne_status = alarm_ne_status;
    }

    public String getProfessional_type() {
        return professional_type;
    }

    public void setProfessional_type(String professional_type) {
        this.professional_type = professional_type;
    }

    public String getNetwork_type() {
        return network_type;
    }

    public void setNetwork_type(String network_type) {
        this.network_type = network_type;
    }

    public String getOrg_type() {
        return org_type;
    }

    public void setOrg_type(String org_type) {
        this.org_type = org_type;
    }

    public String getLogic_alarm_type() {
        return logic_alarm_type;
    }

    public void setLogic_alarm_type(String logic_alarm_type) {
        this.logic_alarm_type = logic_alarm_type;
    }

    public String getLogic_sub_alarm_type() {
        return logic_sub_alarm_type;
    }

    public void setLogic_sub_alarm_type(String logic_sub_alarm_type) {
        this.logic_sub_alarm_type = logic_sub_alarm_type;
    }

    public String getVendor_type() {
        return vendor_type;
    }

    public void setVendor_type(String vendor_type) {
        this.vendor_type = vendor_type;
    }

    public String getSend_jt_flag() {
        return send_jt_flag;
    }

    public void setSend_jt_flag(String send_jt_flag) {
        this.send_jt_flag = send_jt_flag;
    }

    public String getAlarm_title_text() {
        return alarm_title_text;
    }

    public void setAlarm_title_text(String alarm_title_text) {
        this.alarm_title_text = alarm_title_text;
    }

    public String getStandard_alarm_name() {
        return standard_alarm_name;
    }

    public void setStandard_alarm_name(String standard_alarm_name) {
        this.standard_alarm_name = standard_alarm_name;
    }

    public String getVendor_severity() {
        return vendor_severity;
    }

    public void setVendor_severity(String vendor_severity) {
        this.vendor_severity = vendor_severity;
    }

    public String getOrg_severity() {
        return org_severity;
    }

    public void setOrg_severity(String org_severity) {
        this.org_severity = org_severity;
    }

    public String getProbable_cause() {
        return probable_cause;
    }

    public void setProbable_cause(String probable_cause) {
        this.probable_cause = probable_cause;
    }

    public String getStandard_alarm_id() {
        return standard_alarm_id;
    }

    public void setStandard_alarm_id(String standard_alarm_id) {
        this.standard_alarm_id = standard_alarm_id;
    }

    public String getOmc_alarm_id() {
        return omc_alarm_id;
    }

    public void setOmc_alarm_id(String omc_alarm_id) {
        this.omc_alarm_id = omc_alarm_id;
    }

    public String getProbable_cause_txt() {
        return probable_cause_txt;
    }

    public void setProbable_cause_txt(String probable_cause_txt) {
        this.probable_cause_txt = probable_cause_txt;
    }

    public String getAlarm_text() {
        return alarm_text;
    }

    public void setAlarm_text(String alarm_text) {
        this.alarm_text = alarm_text;
    }

    public String getAlarm_act_count() {
        return alarm_act_count;
    }

    public void setAlarm_act_count(String alarm_act_count) {
        this.alarm_act_count = alarm_act_count;
    }

    public String getRelated_information() {
        return related_information;
    }

    public void setRelated_information(String related_information) {
        this.related_information = related_information;
    }

    public String getEffect_ne() {
        return effect_ne;
    }

    public void setEffect_ne(String effect_ne) {
        this.effect_ne = effect_ne;
    }

    public String getEffect_service() {
        return effect_service;
    }

    public void setEffect_service(String effect_service) {
        this.effect_service = effect_service;
    }

    public String getAlarm_memo() {
        return alarm_memo;
    }

    public void setAlarm_memo(String alarm_memo) {
        this.alarm_memo = alarm_memo;
    }

    public String getAlarm_value() {
        return alarm_value;
    }

    public void setAlarm_value(String alarm_value) {
        this.alarm_value = alarm_value;
    }

    public String getPreprocess_manner() {
        return preprocess_manner;
    }

    public void setPreprocess_manner(String preprocess_manner) {
        this.preprocess_manner = preprocess_manner;
    }

    public String getProvince_process_flag() {
        return province_process_flag;
    }

    public void setProvince_process_flag(String province_process_flag) {
        this.province_process_flag = province_process_flag;
    }

    public String getDepartment_process_flag() {
        return department_process_flag;
    }

    public void setDepartment_process_flag(String department_process_flag) {
        this.department_process_flag = department_process_flag;
    }

    public String getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(String channel_type) {
        this.channel_type = channel_type;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getBusiness_area() {
        return business_area;
    }

    public void setBusiness_area(String business_area) {
        this.business_area = business_area;
    }

    public String getExtra_string1() {
        return extra_string1;
    }

    public void setExtra_string1(String extra_string1) {
        this.extra_string1 = extra_string1;
    }

    public String getExtra_string2() {
        return extra_string2;
    }

    public void setExtra_string2(String extra_string2) {
        this.extra_string2 = extra_string2;
    }

    public String getExtra_string3() {
        return extra_string3;
    }

    public void setExtra_string3(String extra_string3) {
        this.extra_string3 = extra_string3;
    }

    public String getExtra_id1() {
        return extra_id1;
    }

    public void setExtra_id1(String extra_id1) {
        this.extra_id1 = extra_id1;
    }

    public String getExtra_id2() {
        return extra_id2;
    }

    public void setExtra_id2(String extra_id2) {
        this.extra_id2 = extra_id2;
    }

    public String getExtra_id3() {
        return extra_id3;
    }

    public void setExtra_id3(String extra_id3) {
        this.extra_id3 = extra_id3;
    }

    public String getTraphName() {
        return traphName;
    }

    public void setTraphName(String traphName) {
        this.traphName = traphName;
    }

    public String getIsPriorityMonitorsTraph() {
        return isPriorityMonitorsTraph;
    }

    public void setIsPriorityMonitorsTraph(String isPriorityMonitorsTraph) {
        this.isPriorityMonitorsTraph = isPriorityMonitorsTraph;
    }

    public String getAlarmObjectCuid() {
        return alarmObjectCuid;
    }

    public void setAlarmObjectCuid(String alarmObjectCuid) {
        this.alarmObjectCuid = alarmObjectCuid;
    }

    public String getLayerRate() {
        return layerRate;
    }

    public void setLayerRate(String layerRate) {
        this.layerRate = layerRate;
    }

    public String getVendorAlarmId() {
        return vendorAlarmId;
    }

    public void setVendorAlarmId(String vendorAlarmId) {
        this.vendorAlarmId = vendorAlarmId;
    }

    public String getVendorVersion() {
        return vendorVersion;
    }

    public void setVendorVersion(String vendorVersion) {
        this.vendorVersion = vendorVersion;
    }

    public String getFdn() {
        return fdn;
    }

    public void setFdn(String fdn) {
        this.fdn = fdn;
    }

    public String getRelated_ne_fdn() {
        return related_ne_fdn;
    }

    public void setRelated_ne_fdn(String related_ne_fdn) {
        this.related_ne_fdn = related_ne_fdn;
    }
    
}
