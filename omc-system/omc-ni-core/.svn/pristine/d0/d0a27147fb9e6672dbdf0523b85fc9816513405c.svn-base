package com.ltln.modules.ni.omc.core.vo;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.core.Asserter;

public class CmEth extends BaseVO {

	private static final long serialVersionUID = -4580966056351873978L;

	String rmUID;
	
	/**
	 * 名称：本地名称 最大长度:255 是否必填：否 取值范围及说明：OMC上显示的以太网业务名称
	 * 数据示例：2053-武汉钟家村/3900-9005-WHMD(DC)-1-PTN6-ETH-PW APS保护-1
	 * */
	String nativeName = "";

	/**
	 * 名称：业务类型 最大长度: 是否必填：是 取值范围及说明：枚举值包括： E-LINE、E-LAN、E-TREE 数据示例：E-LINE
	 * */
	String serviceType;
	/**
	 * 名称：方向 最大长度: 是否必填：是 取值范围及说明：枚举值包括： CD_UNI：单向； CD_BI：双向
	 * 数据示例：D_BIDIRECTIONAL
	 * */
	String direction;
	/**
	 * 名称：用户名称 最大长度:64 是否必填：否 取值范围及说明：OMC上显示的以太网业务名称 数据示例：
	 * */
	String owner = "";
	/**
	 * 名称：客户业务类型 最大长度:64 是否必填：否 取值范围及说明：OMC上设置的客户业务类型。
	 * 如果是集客业务，需要细化明确是跨省集客、跨地市集客、本地集客 数据示例：
	 * */
	String owneSserviceType = "";
	/**
	 * 名称：激活标识 最大长度: 是否必填：是 取值范围及说明：枚举值包括： ACTIVE：激活； PARTIAL（部分激活）；
	 * PENDING（去激活）
	 * 
	 * 数据示例：ACTIVE
	 * */
	String activeState;
	/**
	 * 名称：业务CIR 最大长度:20 是否必填：否 取值范围及说明：有则必填，单位：kbit/s）
	 * 
	 * 数据示例：100
	 * */
	String CIR = "";
	/**
	 * 名称：业务PIR 最大长度:20 是否必填：否 取值范围及说明：有则必填，单位：kbit/s）
	 * 
	 * 数据示例：1000
	 * */
	String PIR = "";


	public void setRmUID(String rmUID) {
		if (StringUtils.isEmpty(rmUID))
			Asserter.fail(this.getClass(), "rmUID");
		this.rmUID = rmUID;
	}

	@Override
	public String getFileRmUID() {
		return this.rmUID;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		if (StringUtils.isEmpty(nativeName)) {
			nativeName = "";
		}
		// if( StringUtils.isEmpty(nativeName) || nativeName.length()>255 )
		if (nativeName.length() > 255)
			Asserter.fail(this.getClass(), "nativeName");
		this.nativeName = nativeName;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		if (StringUtils.isEmpty(serviceType))
			Asserter.fail(this.getClass(), "serviceType");
		this.serviceType = serviceType;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (StringUtils.isEmpty(direction))
			Asserter.fail(this.getClass(), "direction");
		this.direction = direction;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		if (StringUtils.isEmpty(owner)) {
			owner = "";
		}
		// if( StringUtils.isEmpty(owner) || owner.length()>64 )
		if (owner.length() > 64)
			Asserter.fail(this.getClass(), "owner");
		this.owner = owner;
	}

	public String getOwneSserviceType() {
		return owneSserviceType;
	}

	public void setOwneSserviceType(String owneSserviceType) {
		if (StringUtils.isEmpty(owneSserviceType)) {
			owneSserviceType = "";
		}
		// if( StringUtils.isEmpty(owneSserviceType) ||
		// owneSserviceType.length()>64 )
		if (owneSserviceType.length() > 64)
			Asserter.fail(this.getClass(), "owneSserviceType");
		this.owneSserviceType = owneSserviceType;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		if (StringUtils.isEmpty(activeState))
			Asserter.fail(this.getClass(), "activeState");
		this.activeState = activeState;
	}

	public String getCIR() {
		return CIR;
	}

	public void setCIR(String cIR) {
		if (StringUtils.isEmpty(cIR)) {
			cIR = "";
		}
		// if( StringUtils.isEmpty(cIR) || cIR.length()>20 )
		if (cIR.length() > 20)
			Asserter.fail(this.getClass(), "cIR");
		CIR = cIR;
	}

	public String getPIR() {
		return PIR;
	}

	public void setPIR(String pIR) {
		if (StringUtils.isEmpty(pIR)) {
			pIR = "";
		}
		// if( StringUtils.isEmpty(pIR) || pIR.length()>20 )
		if (pIR.length() > 20)
			Asserter.fail(this.getClass(), "pIR");
		PIR = pIR;
	}

	@Override
	public String getResType() {
		return "ETH";
	}

}
