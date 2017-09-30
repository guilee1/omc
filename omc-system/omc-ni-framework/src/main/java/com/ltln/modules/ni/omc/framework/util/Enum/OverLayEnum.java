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
public enum OverLayEnum {
  OverLay(1,"true","virtual"),NotOverLay(0,"false","real");
  
  int code;
  String type;
  String reality;
  
  OverLayEnum(int code,String type,String reality){
    this.code=code;
    this.type=type;
    this.reality=reality;
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

  public String getReality() {
    return reality;
  }

  public void setReality(String reality) {
    this.reality = reality;
  }
  
  public static String getTypeFromCode(int code) {
    for (OverLayEnum signalType : OverLayEnum.values()) {
      if (signalType.getCode() == code) {
        return signalType.getType();
      }
    }
    return OverLayEnum.NotOverLay.getType();
  }  
  
  public static String getRealityFromCode(int code) {
    for (OverLayEnum signalType : OverLayEnum.values()) {
      if (signalType.getCode() == code) {
        return signalType.getReality();
      }
    }
    return OverLayEnum.NotOverLay.getReality();
  }  
}
