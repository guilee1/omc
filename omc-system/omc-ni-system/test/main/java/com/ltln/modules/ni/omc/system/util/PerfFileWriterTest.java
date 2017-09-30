package com.ltln.modules.ni.omc.system.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ltln.modules.ni.omc.core.vo.PerformanceVo;
import com.ltln.modules.ni.omc.system.core.file.IFileWriter;
import com.ltln.modules.ni.omc.system.core.file.PerfFileWriter;

public class PerfFileWriterTest {

	IFileWriter<PerformanceVo> perfWriter = new PerfFileWriter<>();
	
	public static void main(String[] args) {
		PerfFileWriterTest t = new PerfFileWriterTest();
		t.before();
		t.testWrite();
	}
	@Before
	public void before(){
		Constants.init();
	}
	
	@Test
	public void testWrite(){
		List<List<PerformanceVo>> allPerf = new ArrayList<>();
		for(int index = 0;index<2;index++){
			List<PerformanceVo> perfVos = new ArrayList<>();
			for(int i=0;i<10000;i++){
				PerformanceVo vo = new PerformanceVo();
//				vo.setDn("ZTE-CMZJ-TZ,SubNetwork=ltetdd18,ManagedElement=ENODEBME747785,EnbFunction=747785,EutranCellTdd=1");
				vo.setMonitorCycle(String.valueOf(index*5));
				vo.setMonitorType("TUNNEL");
				vo.setRmUid("TUNNEL"+i);
//				vo.setUserLabel("UserLabel");
				String[] key = new String[]{"Delay","Delaychg","Droppercent","Fcserr","Inbyte","Inpacket","Outbyte","Outpacket"};
				String[] value = new String[]{String.valueOf(i),String.valueOf(i),"80%","90%","N/A","9821","8293120","9282"};
				vo.setPerfKey(key);
				vo.setPerfValue(value);
				vo.setMonitorTime(OmcDateFormater.formatFileDate(new Date()));
				perfVos.add(vo);
			}
			allPerf.add(perfVos);
		}
		
		for(List<PerformanceVo> perfVos : allPerf)
			perfWriter.makeFile(perfVos);
	}
}
