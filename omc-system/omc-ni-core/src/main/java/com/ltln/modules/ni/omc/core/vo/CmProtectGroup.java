package com.ltln.modules.ni.omc.core.vo;

public class CmProtectGroup extends BaseVO {

	private static final long serialVersionUID = -6757436597882517057L;
	String rmUID;
	String lagrmUID;
	String NErmUID;
	String nativeName;
	String reversionMode;
	String type;
	
	public String getRmUID() {
		return rmUID;
	}

	public void setRmUID(String rmUID) {
		this.rmUID = rmUID;
	}

	public String getLagrmUID() {
		return lagrmUID;
	}

	public void setLagrmUID(String lagrmUID) {
		this.lagrmUID = lagrmUID;
	}

	public String getNErmUID() {
		return NErmUID;
	}

	public void setNErmUID(String nErmUID) {
		NErmUID = nErmUID;
	}

	public String getNativeName() {
		return nativeName;
	}

	public void setNativeName(String nativeName) {
		this.nativeName = nativeName;
	}

	public String getReversionMode() {
		return reversionMode;
	}

	public void setReversionMode(String reversionMode) {
		this.reversionMode = reversionMode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getResType() {
		return "PTG";
	}

	@Override
	public String getFileRmUID() {
		return this.rmUID;
	}

}
