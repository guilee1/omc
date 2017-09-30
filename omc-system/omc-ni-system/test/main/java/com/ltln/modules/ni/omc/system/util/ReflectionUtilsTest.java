package com.ltln.modules.ni.omc.system.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.core.vo.CmCard;
import com.ltln.modules.ni.omc.core.vo.CmEProtectGroup;
import com.ltln.modules.ni.omc.core.vo.CmEProtectGroupUnit;
import com.ltln.modules.ni.omc.core.vo.CmEth;
import com.ltln.modules.ni.omc.core.vo.CmEthSPInfo;
import com.ltln.modules.ni.omc.core.vo.CmEthSerciePWInfo;
import com.ltln.modules.ni.omc.core.vo.CmHolder;
import com.ltln.modules.ni.omc.core.vo.CmLabelSwitch;
import com.ltln.modules.ni.omc.core.vo.CmNE;
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
import com.ltln.modules.ni.omc.core.vo.DataFile;
import com.ltln.modules.ni.omc.system.core.file.IFileWriter;
import com.ltln.modules.ni.omc.system.core.file.ResourceFileWriter;
import com.ltln.modules.ni.omc.system.core.scheduler.ResourceTask;

public class ReflectionUtilsTest {

	@Test
	public void testGetFieldNameList() {
		List<String> fieldNames = ReflectionUtil.getFieldNameList(CmEth.class);
		Assert.assertEquals(9, fieldNames.size());
	}

	@Test
	public void testGetFieldValObjectString() {
		CmEth obj = buildOneCmEth();
		String rmUID  = ReflectionUtil.getFieldVal(obj, "rmUID");
		Assert.assertEquals(obj.getFileRmUID(), rmUID);
	}

	private CmEth buildOneCmEth() {
		CmEth obj = new CmEth();
		obj.setActiveState("activeState_value");
		obj.setCIR("cIR_value");
		obj.setDirection("direction_value");
		obj.setNativeName("nativeName_value");
		obj.setOwner("owner_value");
		obj.setOwneSserviceType("owneSserviceType_value");
		obj.setPIR("pIR_value");
		obj.setRmUID("rmUID-11111");
		obj.setServiceType("serviceType_value");
		return obj;
	}

	@Test
	public void testConvertToDataFile() {
		List<CmEth> cmEthList = new ArrayList<>();
		for(int i=0;i<10;++i){
			cmEthList.add(buildOneCmEth());
		}
		DataFile df = ResFileUtil.convertToDataFile(cmEthList);
		Assert.assertEquals(9, df.getObjects().get(0).getFieldName().getN().size());
		Assert.assertEquals(10, df.getObjects().get(0).getFieldValue().getObject().size());
	}
	
	@Test
	public void testIFileWriter_makeFile() {
		List<CmEth> cmEthList = new ArrayList<>();
		for(int i=0;i<10000;++i){
			cmEthList.add(buildOneCmEth());
		}
		Constants.init();
		IFileWriter<CmEth> ifw = new ResourceFileWriter<>();
		ifw.makeFile(cmEthList);
	}

	@Test
	public void testResFileUtil_getObjectToXml() {
		String str = ResFileUtil.getObjectToXml(ResFileUtil.buildFieldObject(buildOneCmEth()));
		System.out.println(str);
	}

	@Test
	public void testGetFieldValFieldObject()throws Exception {
		ResourceTask resTask = new ResourceTask();
		Class<?>[] allResSubClass = new Class<?>[]{CmNE.class,CmHolder.class,CmCard.class,
				CmPort.class,CmPortBinding.class,CmTunnel.class,CmTunnelPGBackupInfo.class,
				CmTunnelPGInfo.class,CmLabelSwitch.class,CmPW.class,CmPWPW.class,CmPWTunnel.class,
				CmEthSerciePWInfo.class,CmEth.class,CmEthSPInfo.class,CmTDM.class,CmTopoLink.class,
				CmSubNet.class,CmSubNetNe.class,CmEProtectGroup.class,CmEProtectGroupUnit.class,
				CmProtectGroup.class,CmProtectGroupUnit.class};
		for(Class<?> clazz : allResSubClass){
			List emptyCmNe = new ArrayList<>();
			resTask.addSpecialEleIfEmpty(emptyCmNe,clazz );
			BaseVO baseObj = (BaseVO)emptyCmNe.get(0);
			System.out.println(baseObj.getFileRmUID());
		}
	}

}
