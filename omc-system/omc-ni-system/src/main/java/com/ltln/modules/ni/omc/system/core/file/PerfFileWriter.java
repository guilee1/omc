package com.ltln.modules.ni.omc.system.core.file;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.core.vo.PerformanceVo;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;
import com.ltln.modules.ni.omc.system.util.PerfFileParser;

@Component
public final class PerfFileWriter<T extends BaseVO> extends FileWriterAdapter<T> {

	@Override
	protected String getFileHeader(BaseVO baseVo) {
		return "PM-PTN-"+baseVo.getResType() + "-" +Constants.HOST_NO+"-"+Constants.VERSION+"-";
	}

	@Override
	protected String getFileSavePath() {
		return Constants.FTP_SERVER_PATH_PM;
	}

	@Override
	protected void writeFile(File tempFile, String encoding, List<T> alarmList) throws Exception{
		if(alarmList.isEmpty())return;
		PerformanceVo vo = (PerformanceVo)alarmList.get(0);
		FileUtils.write(tempFile, PerfFileParser.getHeader(vo) + PerfFileParser.getColumn(vo), encoding);
		StringBuilder perfData = new StringBuilder();
		for(T item : alarmList){
			perfData.append(PerfFileParser.getData((PerformanceVo)item));
			perfData.append("\r\n");
		}
		FileUtils.writeStringToFile(tempFile, perfData.toString(), encoding,true);
	}

	@Override
	protected String getFileSuffix() {
		return ".csv.tmp";
	}

	@Override
	protected String getPerfCycle(BaseVO baseVo) {
		return baseVo.getFileRmUID()+"-";
	}

	@Override
	protected String getDir() {
		return OmcDateFormater.formatPerfDirDate(new Date());
	}
}
