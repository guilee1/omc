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
public enum HolderStateEnum {
  EMPTY("EMPTY"),INSTALLED_AND_EXPECTED("INSTALLED_AND_EXPECTED"),EXPECTED_AND_NOT_INSTALLED("EXPECTED_AND_NOT_INSTALLED"),
  INSTALLED_AND_NOT_EXPECTED("INSTALLED_AND_NOT_EXPECTED"),MISMATCH_OF_INSTALLED_AND_EXPECTED("MISMATCH_OF_INSTALLED_AND_EXPECTED"),
  UNAVAILABLE("UNAVAILABLE"),UNKNOWN("UNKNOWN");
  
  String type;
  
  HolderStateEnum(String type){
    this.type=type;
  }
  
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
  
}
