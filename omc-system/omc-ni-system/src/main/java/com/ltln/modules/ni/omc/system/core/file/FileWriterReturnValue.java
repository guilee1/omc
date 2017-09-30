package com.ltln.modules.ni.omc.system.core.file;

import java.util.ArrayList;
import java.util.List;

import com.ltln.modules.ni.omc.core.vo.BaseVO;

public class FileWriterReturnValue<T extends BaseVO> {

	List<T> lastReturnValue = new ArrayList<>();
	int fileNo;
	List<String> filePath = new ArrayList<>();
	
	public List<T> getLastReturnValue() {
		return lastReturnValue;
	}
	public void setLastReturnValue(List<T> lastReturnValue) {
		this.lastReturnValue = lastReturnValue;
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public List<String> getFilePath() {
		return filePath;
	}
	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
}
