package com.ltln.modules.ni.omc.system.core.file;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.core.vo.DataFile;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;
import com.ltln.modules.ni.omc.system.util.ResFileUtil;

@Component
public final class ResourceFileWriter<T extends BaseVO> extends FileWriterAdapter<T> {

	@Override
	protected String getFileHeader(BaseVO baseVo) {
		return "CM-PTN-"+baseVo.getResType()+"-"+Constants.HOST_NO+"-"+Constants.VERSION+"-";
	}

	@Override
	protected String getFileSavePath() {
		return Constants.FTP_SERVER_PATH_CM;
	}

	@Override
	protected void writeFile(File tempFile, String encoding, List<T> alarmList) throws Exception{
		DataFile dataFile = ResFileUtil.convertToDataFile(alarmList);
		ResFileUtil.marshal(dataFile,tempFile,encoding);
	}

	@Override
	protected String getFileSuffix() {
		return ".xml.tmp";
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
