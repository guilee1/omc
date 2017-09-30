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
public enum TPIRecModeEnum {
  RM_REVERTIVE(1,"RM_REVERTIVE"),RM_NON_REVERTIVE(0,"RM_NON_REVERTIVE"),RM_UNKNOWN(-1,"RM_UNKNOWN");
  
  TPIRecModeEnum(int code,String type){
    this.code=code;
    this.type=type;
  }
  
  int code;
  String type;
  
  public int getCode() {
    return code;
  }
  
  public void setCode(int code) {
    this.code = code;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
  
  public static String getTypeFromCode(int code) {
    for (TPIRecModeEnum tpiEnum : TPIRecModeEnum.values()) {
      if (tpiEnum.getCode() == code) {
        return tpiEnum.getType();
      }
    }
    return TPIRecModeEnum.RM_NON_REVERTIVE.getType();
  }  
}
