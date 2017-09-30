package com.ltln.modules.ni.omc.system.core.scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IPerfInf;
import com.ltln.modules.ni.omc.core.vo.PerformanceVo;
import com.ltln.modules.ni.omc.system.core.file.IFileWriter;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;

@Component
public class PerfTask  {

    @Autowired
    ILocator locator;

    @Resource(name = "perfFileWriter")
    IFileWriter<PerformanceVo> ifw;
	
    public void executeJob(String beginTime,String endTime) {
    	IPerfInf resInf = locator.getInstances(IPerfInf.class);
    	try{
	    	List<List<PerformanceVo>> perfData = resInf.queryHistoryPerf(beginTime, endTime);
	    	for(List<PerformanceVo> item : perfData){
	    		ifw.makeFile(item);
	    	}
        } catch (Exception e) {
            Logger.error("ResourceTask error:", e);
        }
    }

   
}
