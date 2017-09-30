package com.ltln.modules.ni.omc.system.core.scheduler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IResInf;
import com.ltln.modules.ni.omc.core.vo.CmCard;
import com.ltln.modules.ni.omc.core.vo.CmEProtectGroup;
import com.ltln.modules.ni.omc.core.vo.CmEProtectGroupUnit;
import com.ltln.modules.ni.omc.core.vo.CmEth;
import com.ltln.modules.ni.omc.core.vo.CmEthSPInfo;
import com.ltln.modules.ni.omc.core.vo.CmEthSerciePWInfo;
import com.ltln.modules.ni.omc.core.vo.CmHolder;
import com.ltln.modules.ni.omc.core.vo.CmLabelSwitch;
import com.ltln.modules.ni.omc.core.vo.CmNE;
import com.ltln.modules.ni.omc.core.vo.CmOMC;
import com.ltln.modules.ni.omc.core.vo.CmPW;
import com.ltln.modules.ni.omc.core.vo.CmPWPW;
import com.ltln.modules.ni.omc.core.vo.CmPWTunnel;
import com.ltln.modules.ni.omc.core.vo.CmPort;
import com.ltln.modules.ni.omc.core.vo.CmPortBinding;
import com.ltln.modules.ni.omc.core.vo.CmProtectGroup;
import com.ltln.modules.ni.omc.core.vo.CmProtectGroupUnit;
import com.ltln.modules.ni.omc.core.vo.CmSubNet;
import com.ltln.modules.ni.omc.core.vo.CmSubNetNe;
import com.ltln.modules.ni.omc.core.vo.CmTDM;
import com.ltln.modules.ni.omc.core.vo.CmTopoLink;
import com.ltln.modules.ni.omc.core.vo.CmTunnel;
import com.ltln.modules.ni.omc.core.vo.CmTunnelPGBackupInfo;
import com.ltln.modules.ni.omc.core.vo.CmTunnelPGInfo;
import com.ltln.modules.ni.omc.system.core.file.IFileWriter;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.ReflectionUtil;
import com.ltln.modules.ni.omc.system.util.ResFileUtil;

@Component
public class ResourceTask implements Runnable {

    @Autowired
    ILocator locator;

    @SuppressWarnings("rawtypes")
	@Resource(name = "resourceFileWriter")
    IFileWriter ifw;

    @SuppressWarnings("unchecked")
	public void executeJob() {
        IResInf resInf = locator.getInstances(IResInf.class);
        try {
            CmOMC omcObj = resInf.getCmOMC();
            List<CmOMC> cmOmcList = new ArrayList<>();
            cmOmcList.add(omcObj);
            //OMC数据
            ifw.makeFile(cmOmcList);
            
            //网元
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmNE(),CmNE.class));
            //容器
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmHolder(),CmHolder.class));
            //板卡
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmCard(),CmCard.class));
            System.gc();
            //端口
            List<CmPort> _ethPorts = resInf.getEthPort();
            List<CmPort> _pdhPorts = resInf.getPdhPort();
            List<CmPort> _subPorts = resInf.getSubPort();
            List<CmPort> _otherPorts = resInf.getOtherPort();
            _ethPorts.addAll(_pdhPorts);
            _ethPorts.addAll(_subPorts);
            _ethPorts.addAll(_otherPorts);
            ifw.makeFile(addSpecialEleIfEmpty(_ethPorts,CmPort.class));
            System.gc();
            //端口绑定（链路聚合LAG）
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmPortBinding(),CmPortBinding.class));
            //隧道
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmTunnel(),CmTunnel.class));
            //隧道保护组-主备用隧道
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmTunnelPGBackupInfo(),CmTunnelPGBackupInfo.class));
            //隧道保护组-基本信息
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmTunnelPGInfo(),CmTunnelPGInfo.class));
            //标签交换
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmLabelSwitch(),CmLabelSwitch.class));
            //伪线
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmPW(),CmPW.class));
            //伪线间承载关系
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmPWPW(),CmPWPW.class));
            //伪线隧道承载关系
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmPWTunnel(),CmPWTunnel.class));
            System.gc();
            //以太网业务
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmEth(),CmEth.class));
            //以太网业务承载伪线
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmEthSerciePWInfo(),CmEthSerciePWInfo.class));
          //以太网业务接入点
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmEthSPInfo(),CmEthSPInfo.class));
            //TDM业务
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmTDM(),CmTDM.class));
            //拓扑连接
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmTopoLink(),CmTopoLink.class));
            //子网（包含子网与子网关系）
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmSubNet(),CmSubNet.class));
            //子网与网元关系
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmSubNetNe(),CmSubNetNe.class));
            //板卡保护组
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmEProtectGroup(),CmEProtectGroup.class));
            //板卡保护组单元
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmEProtectGroupUnit(),CmEProtectGroupUnit.class));
            //端口保护
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmProtectGroup(),CmProtectGroup.class));
            //端口保护组成员
            ifw.makeFile(addSpecialEleIfEmpty(resInf.getCmProtectGroupUnit(),CmProtectGroupUnit.class));
            System.gc();
        } catch (Exception e) {
            Logger.error("ResourceTask error:", e);
        }
    }

   public <T> List<T> addSpecialEleIfEmpty(List<T> objList, Class<T> class1)throws Exception {
		if(objList.isEmpty()){
			T special = class1.newInstance();
			ReflectionUtil.setFieldVal(special, "rmUID", ResFileUtil.SPECIALUID);
			objList.add(special);
		}
		return objList;
	}

	@Override
    public void run() {
        this.executeJob();
    }
	
}
