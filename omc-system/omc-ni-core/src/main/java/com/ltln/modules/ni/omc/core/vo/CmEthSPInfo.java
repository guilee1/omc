package com.ltln.modules.ni.omc.core.vo;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.core.Asserter;

public class CmEthSPInfo extends BaseVO {

	private static final long serialVersionUID = -8970359533192789051L;

	String rmUID;

	/**
	 * 名称：以太网业务rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String servicermUID;
	/**
	 * 名称：接入点所在网元rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String nermUID;
	/**
	 * 名称：接入点所在端口rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String portrmUID;
	/**
	 * 名称：接入点CVLAN ID 最大长度:50 是否必填：否 取值范围及说明：CVLAN ID范围,有则必填。
	 * 当vlan连续时，格式：起始vlanid-终止vlanid； 当vlan不连续时，不同vlanid间用逗号分隔。
	 * 当同时存在连续和不连续场景时，两种场景间用逗号分隔。形如：1-10或1,2,8或1,2,5-10 数据示例：2575
	 * */
	String CVID = "";
	/**
	 * 名称：接入点SVLAN ID 最大长度:50 是否必填：否 取值范围及说明：SVLAN ID范围,有则必填。
	 * 当vlan连续时，格式：起始vlanid-终止vlanid； 当vlan不连续时，不同vlanid间用逗号分隔。
	 * 当同时存在连续场景和不连续场景时，两种场景间用逗号分隔。形如：1-10或1,2,8或1,2,5-10 数据示例：
	 * */
	String SVID = "";
	

        private String ingressCIR = "";
        private String ingressPIR = "";
        private String egressCIR = "";
        private String egressPIR = "";

	@Override
	public String getFileRmUID() {
		return this.rmUID;
	}

	public String getServicermUID() {
		return servicermUID;
	}

	public void setServicermUID(String servicermUID) {
		if (StringUtils.isEmpty(servicermUID))
			Asserter.fail(this.getClass(), "servicermUID");
		this.servicermUID = servicermUID;
	}

	public String getNermUID() {
		return nermUID;
	}

	public void setNermUID(String nermUID) {
		if (StringUtils.isEmpty(nermUID))
			Asserter.fail(this.getClass(), "nermUID");
		this.nermUID = nermUID;
	}

	public String getPortrmUID() {
		return portrmUID;
	}

	public void setPortrmUID(String portrmUID) {
		if (StringUtils.isEmpty(portrmUID))
			Asserter.fail(this.getClass(), "portrmUID");
		this.portrmUID = portrmUID;
	}

	public String getCVID() {
		return CVID;
	}

	public void setCVID(String cVID) {
		if (StringUtils.isEmpty(cVID)) {
			cVID = "";
		}
		// if( StringUtils.isEmpty(cVID) || cVID.length()>50 )
		if (cVID.length() > 50)
			Asserter.fail(this.getClass(), "cVID");
		CVID = cVID;
	}

	public String getSVID() {
		return SVID;
	}

	public void setSVID(String sVID) {
		if (StringUtils.isEmpty(sVID)) {
			sVID = "";
		}
		// if( StringUtils.isEmpty(sVID) || sVID.length()>50 )
		if (sVID.length() > 50)
			Asserter.fail(this.getClass(), "sVID");
		SVID = sVID;
	}


	public String getRmUID() {
		return rmUID;
	}

	public void setRmUID(String rmUID) {
		this.rmUID = rmUID;
	}

	@Override
	public String getResType() {
		return "ESP";
	}

    /**
     * @return the ingressCIR
     */
    public String getIngressCIR() {
        return ingressCIR;
    }

    /**
     * @param ingressCIR the ingressCIR to set
     */
    public void setIngressCIR(String ingressCIR) {
        this.ingressCIR = ingressCIR;
    }

    /**
     * @return the ingressPIR
     */
    public String getIngressPIR() {
        return ingressPIR;
    }

    /**
     * @param ingressPIR the ingressPIR to set
     */
    public void setIngressPIR(String ingressPIR) {
        this.ingressPIR = ingressPIR;
    }

    /**
     * @return the egressCIR
     */
    public String getEgressCIR() {
        return egressCIR;
    }

    /**
     * @param egressCIR the egressCIR to set
     */
    public void setEgressCIR(String egressCIR) {
        this.egressCIR = egressCIR;
    }

    /**
     * @return the egressPIR
     */
    public String getEgressPIR() {
        return egressPIR;
    }

    /**
     * @param egressPIR the egressPIR to set
     */
    public void setEgressPIR(String egressPIR) {
        this.egressPIR = egressPIR;
    }

}
