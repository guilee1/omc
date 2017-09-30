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
public enum SignalTypeEnum {
  optical(1,"optical"),electrical(0,"electrical"),invalid(-1,"invalid");
  int code;
  String type;
  
  SignalTypeEnum(int code,String type){
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
  
  public static String getTypeFromCode(int code) {
    for (SignalTypeEnum signalType : SignalTypeEnum.values()) {
      if (signalType.getCode() == code) {
        return signalType.getType();
      }
    }
    return SignalTypeEnum.invalid.getType();
  }  
  
}
