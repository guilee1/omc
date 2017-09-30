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
@XmlRootElement(name = "dataSet")
public class DataSet {
    @XmlAttribute(name = "xmlns")
    private String xmlns="" ;
    @XmlAttribute(name = "data_type")
    private String  data_type="ALARM_ACT";
    @XmlElement(name = "dataRecord")
    private DataRecord datarecord = new DataRecord();

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public DataRecord getDatarecord() {
        return datarecord;
    }

    public void setDatarecord(DataRecord datarecord) {
        this.datarecord = datarecord;
    }

}
