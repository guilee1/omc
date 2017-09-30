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
public enum TunnelTypeEnum {
  Common(0,"Common"),OneByOne(1,"OneByOne"),CIRCLE(2,"CIRCLE");
  
  int code;
  String type;
  
  TunnelTypeEnum(int code,String type){
    this.code=code;
    this.type=type;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }
  
  public static TunnelTypeEnum getTypeFromCode(int code) {
    for (TunnelTypeEnum signalType : TunnelTypeEnum.values()) {
      if (signalType.getCode() == code) {
        return signalType;
      }
    }
    return TunnelTypeEnum.Common;
  }  
  
}
