package com.ltln.modules.ni.omc.core.vo;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.core.Asserter;

public class CmOMC extends BaseVO {
	
	private static final long serialVersionUID = -7921855943512036039L;
	
	/**
	 * 名称：本地名称
	 * 最大长度:255
	 * 是否必填是
	 * 取值范围及说明 OMC名称
	 * 数据示例:MAS-U2000-1-P
	 */
	String nativeName;
	
	/**
	 * 名称：IP地址
	 * 最大长度:40
	 * 是否必填：是
	 * 取值范围及说明：安装OMC的服务器IP地址
	 * 数据示例：10.78.33.22
	 * */
	String commuAddress;
	
	/**
	 * 名称：软件版本
	 * 最大长度:255
	 * 是否必填：是
	 * 取值范围及说明：网管版本号
	 * 数据示例：V1R2C01
	 * */
	String omcVersion;
	
	/**
	 * 名称：北向接口版本
	 * 最大长度:255
	 * 是否必填：是
	 * 取值范围及说明：
	 * 数据示例：2
	 * */
	String interfaceVersion;
	
	/**
	 * 名称：管理设备类型
	 * 最大长度:50
	 * 是否必填：是
	 * 取值范围及说明：OMC上实际管理的设备类型
                        设备类型包括：SDH、WDM、OTN、PTN、PON。
                        管理多种设备类型时，用逗号分隔，形如：SDH,PTN
	 * 数据示例：PTN
	 * */
	String equipmemtDomain;
	
	/**
	 * 名称：设备供应商
	 * 最大长度:20
	 * 是否必填：是
	 * 取值范围及说明：见《OMC系统北向接口数据规范-公共数据分册》
	 * 数据示例：HW
	 * */
	String vendor;
	
	/**
	 * 名称：OMC唯一标识
	 * 最大长度:
	 * 是否必填：是
	 * 取值范围及说明：OMC唯一标识
	 * 数据示例：1302HWCS1
	 * */
	String rmUID;

                  @Override
                  public String getFileRmUID() {
                      return this.rmUID;
                  }
                  
	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		if( StringUtils.isEmpty(nativeName) || nativeName.length()>255 )
			Asserter.fail(this.getClass(),"nativeName");
		this.nativeName = nativeName;
	}

	public String getCommuAddress() {
		return commuAddress;
	}

	public void setCommuAddress(String commuAddress) {
		if( StringUtils.isEmpty(commuAddress) || commuAddress.length()>40 )
			Asserter.fail(this.getClass(),"commuAddress");
		this.commuAddress = commuAddress;
	}

	public String getOmcVersion() {
		return omcVersion;
	}

	public void setOmcVersion(String omcVersion) {
		if( StringUtils.isEmpty(omcVersion) || omcVersion.length()>255 )
			Asserter.fail(this.getClass(),"omcVersion");
		this.omcVersion = omcVersion;
	}

	public String getInterfaceVersion() {
		return interfaceVersion;
	}

	public void setInterfaceVersion(String interfaceVersion) {
		if( StringUtils.isEmpty(interfaceVersion) || interfaceVersion.length()>255 )
			Asserter.fail(this.getClass(),"interfaceVersion");
		this.interfaceVersion = interfaceVersion;
	}

	public String getEquipmemtDomain() {
		return equipmemtDomain;
	}

	public void setEquipmemtDomain(String equipmemtDomain) {
		if( StringUtils.isEmpty(equipmemtDomain) || equipmemtDomain.length()>50 )
			Asserter.fail(this.getClass(),"equipmemtDomain");
		this.equipmemtDomain = equipmemtDomain;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		if( StringUtils.isEmpty(vendor) || vendor.length()>20 )
			Asserter.fail(this.getClass(),"vendor");
		this.vendor = vendor;
	}

	public String getRmUID() {
		return rmUID;
	}

	public void setRmUID(String rmUID) {
		if( StringUtils.isEmpty(rmUID) )
			Asserter.fail(this.getClass(),"rmUID");
		this.rmUID = rmUID;
	}

	@Override
	public String getResType() {
		return "OMC";
	}

}
