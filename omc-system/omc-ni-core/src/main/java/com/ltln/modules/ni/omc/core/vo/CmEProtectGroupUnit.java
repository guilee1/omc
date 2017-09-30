package com.ltln.modules.ni.omc.core.vo;

public class CmEProtectGroupUnit extends BaseVO {

	private static final long serialVersionUID = -4186752991727842744L;

	String rmUID;
	String grouprmUID;
	String cardrmUID;
	String role;
	
	public String getRmUID() {
		return rmUID;
	}

	public void setRmUID(String rmUID) {
		this.rmUID = rmUID;
	}

	public String getGrouprmUID() {
		return grouprmUID;
	}

	public void setGrouprmUID(String grouprmUID) {
		this.grouprmUID = grouprmUID;
	}

	public String getCardrmUID() {
		return cardrmUID;
	}

	public void setCardrmUID(String cardrmUID) {
		this.cardrmUID = cardrmUID;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String getResType() {
		return "EPU";
	}

	@Override
	public String getFileRmUID() {
		return this.rmUID;
	}

}
