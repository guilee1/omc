package com.ltln.modules.ni.omc.core.vo;

public class CmEProtectGroup extends BaseVO {

	private static final long serialVersionUID = -5884702814157909001L;
	String rmUID;
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
		return "EPG";
	}

	@Override
	public String getFileRmUID() {
		return this.rmUID;
	}

	public String getNErmUID() {
		return NErmUID;
	}

	public void setNErmUID(String nErmUID) {
		NErmUID = nErmUID;
	}

}
