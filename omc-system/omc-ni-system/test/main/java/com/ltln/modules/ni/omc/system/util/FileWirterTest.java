package com.ltln.modules.ni.omc.system.util;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.file.AlarmFileWriter;
import com.ltln.modules.ni.omc.system.core.file.IFileWriter;

public class FileWirterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Constants.init();
		List<AlarmVo>alarmList = makeOneAlarm();
//		long sizeOf = FileWriter.sizeOf(alarmList);
//		System.out.println(sizeOf);
//		FileZipper.zipCompress(FileWriter.writeAlarmListToFile(alarmList));
//		FileZipper.zipFile("F:\\log4net.xml");
//		FileZipper.zipFile("F:\\omc-system\\omc-system\\omc-ni-system\\ftproot\\HB\\CS\\SS\\OMC_NI\\FM\\FM-OMC-1A-V1.0.0-20170328141556-001.txt");
	
//		Toolkit.removeSuffix("F:\\omc-system\\omc-system\\omc-ni-system\\ftproot\\HB\\CS\\SS\\OMC_NI\\FM\\FM-OMC-1A-V1.0.0-20170328141556-001.txt");
		IFileWriter<AlarmVo> ifw = new AlarmFileWriter<>();
		ifw.makeFile(alarmList);
	}

	private static List<AlarmVo> makeOneAlarm() {
		List<AlarmVo> result = new ArrayList<>();
		for(int i=0;i<1000000;++i)
			result.add(buildOne(i));
		return result;
	}

//	@Test
	public void testCutOff(){
		List<AlarmVo>alarmList = makeOneAlarm();
		List<AlarmVo> re = FilePagedUtil.cutOff(alarmList, 5*1024*1024, 600);
		Assert.assertEquals(11, re.size());
	}
	
//	@Test
	public void testPageAlarmList(){
		List<AlarmVo>alarmList = makeOneAlarm();
		List<List<AlarmVo>> re = FilePagedUtil.pageAlarmList(alarmList, 5*1024*1024, 600);
		Assert.assertEquals(2, re.size());
	}
	
	static AlarmVo buildOne(int seq){
		AlarmVo alarmObj = new AlarmVo();
		alarmObj.setAddInfo("附件信息附件信息附件信息附件信息附件信息附件信息附件信息附件信息附件信息附件信息附件信息附件信息");
		alarmObj.setAlarmCheck("告警check");
		alarmObj.setAlarmId("告警ID");
		alarmObj.setAlarmSeq(seq);
		alarmObj.setAlarmStatus(0);
		alarmObj.setAlarmTitle("告警标题");
		alarmObj.setAlarmType("告警类型");
		alarmObj.setEventTime("时间试试");
		alarmObj.setEventTimeMills(0);
		alarmObj.setHolderType("holderType");
		alarmObj.setId(0);
		alarmObj.setLayer(0);
		alarmObj.setLocationInfo("位置信息");
		alarmObj.setLogTime("logTime");
		alarmObj.setNeName("网元名称信息时说明？");
		alarmObj.setNeType("网元类型");
		alarmObj.setNeUID("neUID");
		alarmObj.setObjectName("对象定位点为止信息");
		alarmObj.setObjectType("objectType");
		alarmObj.setObjectUID("objectUID");
		alarmObj.setOrigSeverity(0);
		alarmObj.setSpecificProblem("specificProblem");
		alarmObj.setSpecificProblemID("specificProblemID");
		return alarmObj;
	}
}
