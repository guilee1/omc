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
public enum PhysicalOrLogicalEnum{
    
  ptp("ptp"),ftp("ftp");
  
  String type;
  
  PhysicalOrLogicalEnum(String type){
    this.type=type;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
    
}
