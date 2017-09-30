package com.ltln.modules.ni.omc.system.core.dao;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.cache.AlarmCache;
import com.ltln.modules.ni.omc.system.util.Constants;

public class OmcDaoTest {

	static final String SPRING_XML_FILE = "file:" + Constants.rootDir
			+ "envconf/applicationContext.xml";
	
	public static void main(String[] args) {
		new FileSystemXmlApplicationContext(SPRING_XML_FILE);
		AlarmCache ca = SelfBeanFactoryAware.getBean("alarmCache");
		for(int i=0;i<10;++i){
			AlarmVo alarmObj = new AlarmVo();
			alarmObj.setAddInfo("addInfo");
			alarmObj.setAlarmCheck("alarmCheck");
			alarmObj.setAlarmId("alarmId");
			alarmObj.setAlarmSeq(0);
			alarmObj.setAlarmStatus(0);
			alarmObj.setAlarmTitle("alarmTitle");
			alarmObj.setAlarmType("alarmType");
			alarmObj.setEventTime("eventTime");
			alarmObj.setEventTimeMills(0);
			alarmObj.setHolderType("holderType");
			alarmObj.setId(0);
			alarmObj.setLayer(0);
			alarmObj.setLocationInfo("locationInfo");
			alarmObj.setLogTime("logTime");
			alarmObj.setNeName("neName");
			alarmObj.setNeType("neType");
			alarmObj.setNeUID("neUID");
			alarmObj.setObjectName("objectName");
			alarmObj.setObjectType("objectType");
			alarmObj.setObjectUID("objectUID");
			alarmObj.setOrigSeverity(0);
			alarmObj.setSpecificProblem("specificProblem");
			alarmObj.setSpecificProblemID("specificProblemID");
			ca.put(alarmObj);
		}
	}
}
