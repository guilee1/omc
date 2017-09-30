package com.ltln.modules.ni.omc.framework.test;

import java.util.Date;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.framework.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.framework.msg.JmsSender;

public class AlarmStrom implements Runnable{

	
	public void execute(){
		AlarmVo alarmObj = new AlarmVo();
		alarmObj.setAddInfo("addInfo");
		alarmObj.setAlarmCheck("alarmCheck");
		alarmObj.setAlarmId("alarmId");
		alarmObj.setAlarmSeq(0);
		alarmObj.setAlarmStatus(0);
		alarmObj.setAlarmTitle("alarmTitle");
		alarmObj.setAlarmType("alarmType");
//		alarmObj.setEventTime(new Date().toLocaleString());
//		alarmObj.setEventTimeMills(System.currentTimeMillis());
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
		JmsSender sender = SelfBeanFactoryAware.getBean("JmsSender");
		while(true){
			for(int i=0;i<2;++i){
				alarmObj.setEventTime(new Date().toLocaleString());
				alarmObj.setEventTimeMills(System.currentTimeMillis());
				sender.sendAlarmMsg(alarmObj);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		this.execute();
	}

}
