package com.ltln.modules.ni.omc.system.core.file;

import java.util.List;

import com.ltln.modules.ni.omc.core.vo.BaseVO;

public interface IFileWriter<T extends BaseVO> {

	FileWriterReturnValue<T> makeFile(List<T> alarmList,int fileNo);
	
	void makeFile(List<T> alarmList);
}
