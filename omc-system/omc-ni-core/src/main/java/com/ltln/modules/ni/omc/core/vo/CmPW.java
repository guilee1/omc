package com.ltln.modules.ni.omc.core.vo;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.core.Asserter;

public class CmPW extends BaseVO {

	private static final long serialVersionUID = -1677130243180190482L;

	String rmUID;
	
	/**
	 * 名称：方向 最大长度: 是否必填：是 取值范围及说明：枚举值包括： CD_UNI：单向； CD_BI：双向
	 * 数据示例1：D_BIDIRECTIONAL 数据示例2：D_BIDIRECTIONAL
	 * */
	String direction;
	/**
	 * 名称：源端rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String aEndTprmUID;
	/**
	 * 名称：源端网元rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String aEndNermUID;
	/**
	 * 名称：宿端rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String zEndTprmUID;
	/**
	 * 名称：宿端网元rmUID 最大长度: 是否必填：是 取值范围及说明： 数据示例：
	 * */
	String zEndNermUID;
	/**
	 * 名称：本地名称 最大长度:255 是否必填：否 取值范围及说明：OMC上显示的伪线名称 数据示例1：pwtrail1 数据示例2：pwtrail1
	 * */
	String nativeName = "";
	/**
	 * 名称：源端端口rmUID 最大长度: 是否必填：否 取值范围及说明：如果伪线源端点归属于端口，则必填 数据示例：
	 * */
	String aEndPortrmUID = "";
	/**
	 * 名称：宿端端口rmUID 最大长度: 是否必填：否 取值范围及说明：如果伪线宿端点归属于端口，则必填 数据示例：
	 * */
	String zEndPortrmUID = "";
	/**
	 * 名称：激活标识 最大长度: 是否必填：否 取值范围及说明：枚举值包括： ACTIVE：激活； PARTIAL（部分激活）；
	 * PENDING（去激活） 数据示例1：ACTIVE 数据示例2：ACTIVE
	 * */
	String activeState = "";
	

        String aEndIngressCIR = "";
        String aEndIngressPIR = "";
        private String aEndEgressCIR = "";
        private String aEndEgressPIR = "";
        private String zEndIngressCIR = "";
        private String zEndIngressPIR = "";  
        private String zEndEgressCIR = "";
        private String zEndEgressPIR = "";
        

	public void setRmUID(String rmUID) {
		if (StringUtils.isEmpty(rmUID))
			Asserter.fail(this.getClass(), "rmUID");
		this.rmUID = rmUID;
	}

	@Override
	public String getFileRmUID() {
		return this.rmUID;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (StringUtils.isEmpty(direction))
			Asserter.fail(this.getClass(), "direction");
		this.direction = direction;
	}

	public String getaEndTprmUID() {
		return aEndTprmUID;
	}

	public void setaEndTprmUID(String aEndTprmUID) {
		if (StringUtils.isEmpty(aEndTprmUID))
			Asserter.fail(this.getClass(), "aEndTprmUID");
		this.aEndTprmUID = aEndTprmUID;
	}

	public String getaEndNermUID() {
		return aEndNermUID;
	}

	public void setaEndNermUID(String aEndNermUID) {
		if (StringUtils.isEmpty(aEndNermUID))
			Asserter.fail(this.getClass(), "aEndNermUID");
		this.aEndNermUID = aEndNermUID;
	}

	public String getzEndTprmUID() {
		return zEndTprmUID;
	}

	public void setzEndTprmUID(String zEndTprmUID) {
		if (StringUtils.isEmpty(zEndTprmUID))
			Asserter.fail(this.getClass(), "zEndTprmUID");
		this.zEndTprmUID = zEndTprmUID;
	}

	public String getzEndNermUID() {
		return zEndNermUID;
	}

	public void setzEndNermUID(String zEndNermUID) {
		if (StringUtils.isEmpty(zEndNermUID))
			Asserter.fail(this.getClass(), "zEndNermUID");
		this.zEndNermUID = zEndNermUID;
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

	public String getaEndPortrmUID() {
		return aEndPortrmUID;
	}

	public void setaEndPortrmUID(String aEndPortrmUID) {
		if (StringUtils.isEmpty(aEndPortrmUID)) {
			aEndPortrmUID = "";
		}
		// if( StringUtils.isEmpty(aEndPortrmUID) )
		// Asserter.fail(this.getClass(),"aEndPortrmUID");
		this.aEndPortrmUID = aEndPortrmUID;
	}

	public String getzEndPortrmUID() {
		return zEndPortrmUID;
	}

	public void setzEndPortrmUID(String zEndPortrmUID) {
		if (StringUtils.isEmpty(zEndPortrmUID)) {
			zEndPortrmUID = "";
		}
		// if( StringUtils.isEmpty(zEndPortrmUID) )
		// Asserter.fail(this.getClass(),"zEndPortrmUID");
		this.zEndPortrmUID = zEndPortrmUID;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		if (StringUtils.isEmpty(activeState)) {
			activeState = "";
		}
		// if( StringUtils.isEmpty(activeState) )
		// Asserter.fail(this.getClass(),"activeState");
		this.activeState = activeState;
	}

	
        public String getaEndIngressCIR(){
            return aEndIngressCIR;
        }
        public void setaEndIngressCIR(String aendIngressCIR){
            if(StringUtils.isEmpty(aendIngressCIR)){
                aendIngressCIR = "";
            }
            if(aendIngressCIR.length() > 20){
                Asserter.fail(this.getClass(), "aendIngressCIR"); 
                aEndIngressCIR = aendIngressCIR;
            }
        }
        
        public String getaEndIngressPIR(){
            return aEndIngressPIR;
        }
        public void setaEndIngressPIR(String aendIngressPIR){
            if(StringUtils.isEmpty(aendIngressPIR)){
                aEndIngressPIR = "";
            }
            if(aendIngressPIR.length() > 20){
                Asserter.fail(this.getClass(), "aendIngressPIR"); 
                aEndIngressPIR = aendIngressPIR;
            }
        }

        public String getaEndEgressCIR(){
            return aEndEgressCIR;
        }
        
	@Override
	public String getResType() {
		return "PSW";
	}

    /**
     * @param aEndEgressCIR the aEndEgressCIR to set
     */
    public void setaEndEgressCIR(String aEndEgressCIR) {
        this.aEndEgressCIR = aEndEgressCIR;
    }

    /**
     * @return the aEndEgressPIR
     */
    public String getaEndEgressPIR() {
        return aEndEgressPIR;
    }

    /**
     * @param aEndEgressPIR the aEndEgressPIR to set
     */
    public void setaEndEgressPIR(String aEndEgressPIR) {
        this.aEndEgressPIR = aEndEgressPIR;
    }

    /**
     * @return the zEndIngressCIR
     */
    public String getzEndIngressCIR() {
        return zEndIngressCIR;
    }

    /**
     * @param zEndIngressCIR the zEndIngressCIR to set
     */
    public void setzEndIngressCIR(String zEndIngressCIR) {
        this.zEndIngressCIR = zEndIngressCIR;
    }

    /**
     * @return the zEndIngressPIR
     */
    public String getzEndIngressPIR() {
        return zEndIngressPIR;
    }

    /**
     * @param zEndIngressPIR the zEndIngressPIR to set
     */
    public void setzEndIngressPIR(String zEndIngressPIR) {
        this.zEndIngressPIR = zEndIngressPIR;
    }

    /**
     * @return the zEndEgressCIR
     */
    public String getzEndEgressCIR() {
        return zEndEgressCIR;
    }

    /**
     * @param zEndEgressCIR the zEndEgressCIR to set
     */
    public void setzEndEgressCIR(String zEndEgressCIR) {
        this.zEndEgressCIR = zEndEgressCIR;
    }

    /**
     * @return the zEndEgressPIR
     */
    public String getzEndEgressPIR() {
        return zEndEgressPIR;
    }

    /**
     * @param zEndEgressPIR the zEndEgressPIR to set
     */
    public void setzEndEgressPIR(String zEndEgressPIR) {
        this.zEndEgressPIR = zEndEgressPIR;
    }

}
