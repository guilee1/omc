/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.framework.util.Enum;

/**
 *
 * @author sher
 */
public enum CmEnum {
   OMC("OMC"),NEL("NEL"),EQH("EQH"),CRD("CRD"),PRT("PRT"),PRB("PRB"),//网元、容器、槽位、板卡、端口
   TNL("TNL"),LBS("LBS"),TPI("TPI"),TPB("TPB"),MPI("MPI"),MTR("MTR"),MTL("MTL"),//Tunnel、环
   PSW("PSW"),PWP("PWP"),PWT("PWT"),ETH("ETH"),ESP("ESP"),ESI("ESI"),TDM("TDM"),//Pw、以太网、TDM
   BRD("BRD"),L3I("L3I"),L3P("L3P"),L3T("L3T"),//
   TPL("TPL"),SBN("SBN"),SNN("SNN"),//物理连接、子网连线
   ETP("ETP");
   
  String type;
  CmEnum(String type){
  this.type=type;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
}
