package com.ltln.modules.ni.omc.system.core.file;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;

@Component
public final class AlarmFileWriter<T extends BaseVO> extends FileWriterAdapter<T> {


	@Override
	protected String getFileSavePath() {
		return Constants.FTP_SERVER_PATH_FM;
	}

	@Override
	protected void writeFile(File tempFile, String encoding, List<T> alarmList) throws Exception{
		FileUtils.writeLines(tempFile, Constants.ENCODING, alarmList);
	}

	@Override
	protected String getFileHeader(BaseVO baseVo) {
		return "FM-OMC-"+Constants.HOST_NO+"-"+Constants.VERSION+"-";
	}

	@Override
	protected String getFileSuffix() {
		return ".txt.tmp";
	}

	@Override
	protected String getPerfCycle(BaseVO baseVo) {
		return "";
	}

	@Override
	protected String getDir() {
		return OmcDateFormater.formatDirDate(new Date());
	}

}
