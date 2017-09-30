package com.ltln.modules.ni.omc.core.vo;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.core.Asserter;

public class CmSubNetNe extends BaseVO {

	private static final long serialVersionUID = -1982083579595848082L;
	
	/**
	 * 名称：网元rmUID
	 * 最大长度:
	 * 是否必填：是
	 * 取值范围及说明：
	 * 数据示例：
	 * */
	String nermUID;
	/**
	 * 名称：所属子网rmUID
	 * 最大长度:
	 * 是否必填：是
	 * 取值范围及说明：
	 * 数据示例：
	 * */
	String subnetrmUID;
	/**
	 * 名称：相对X坐标
	 * 最大长度:
	 * 是否必填：是
	 * 取值范围及说明：网元在子网拓扑图中的X坐标
	 * 数据示例1：99
	 * 数据示例2：99
	 * */
	String xPos;
	/**
	 * 名称：相对Y坐标
	 * 最大长度:
	 * 是否必填：是
	 * 取值范围及说明：网元在子网拓扑图中的Y坐标
	 * 数据示例1：233
	 * 数据示例2：233
	 * */
	String yPos;
        
                  @Override
                  public String getFileRmUID() {
                       //excel中无，但样例中与nermUID一致
                      return this.nermUID;
                  }
        
	public String getNermUID() {
		return nermUID;
	}
	public void setNermUID(String nermUID) {
		if( StringUtils.isEmpty(nermUID)  )
			Asserter.fail(this.getClass(),"nermUID");
		this.nermUID = nermUID;
	}
	public String getSubnetrmUID() {
		return subnetrmUID;
	}
	public void setSubnetrmUID(String subnetrmUID) {
		if( StringUtils.isEmpty(subnetrmUID)  )
			Asserter.fail(this.getClass(),"subnetrmUID");
		this.subnetrmUID = subnetrmUID;
	}
	public String getxPos() {
		return xPos;
	}
	public void setxPos(String xPos) {
		if( StringUtils.isEmpty(xPos)  )
			Asserter.fail(this.getClass(),"xPos");
		this.xPos = xPos;
	}
	public String getyPos() {
		return yPos;
	}
	public void setyPos(String yPos) {
		if( StringUtils.isEmpty(yPos)  )
			Asserter.fail(this.getClass(),"yPos");
		this.yPos = yPos;
	}
	@Override
	public String getResType() {
		return "SNN";
	}

}
