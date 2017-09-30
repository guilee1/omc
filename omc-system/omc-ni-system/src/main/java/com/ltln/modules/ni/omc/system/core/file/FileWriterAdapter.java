package com.ltln.modules.ni.omc.system.core.file;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.FilePagedUtil;
import com.ltln.modules.ni.omc.system.util.FileZipper;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public abstract class FileWriterAdapter<T extends BaseVO> implements IFileWriter<T> {

	@Override
	public void makeFile(List<T> alarmList) {
		FileWriterReturnValue<T> fwrValue = this.makeFile(alarmList, 1);
		try {
			Thread.sleep(Constants.FILE_TMP_KEEP_SEC*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		fwrValue.filePath = Toolkit.removeSuffix(fwrValue.filePath);
	}
	
	@Override
	public  FileWriterReturnValue<T> makeFile(List<T> alarmList,int fileNo){
		FileWriterReturnValue<T> result = new FileWriterReturnValue<>();
		if(alarmList==null || alarmList.isEmpty()){
			Logger.info("empty object list in file maker?");
			return result; 
		}
		try{
			result = writeListToFile(alarmList,fileNo);
			List<String> zipFilePath = FileZipper.zipCompress(result.getFilePath());
			result.setFilePath(zipFilePath);
		}catch (Exception e) {
			Logger.error("make file exception~", e);
		}
		return result;
	}
	
	protected abstract String getFileHeader(BaseVO baseVo);
	
	protected abstract String getPerfCycle(BaseVO baseVo);
	
	protected abstract String getFileSavePath();
	
	protected abstract String getFileSuffix();
	
	protected abstract void writeFile(File tempFile,String encoding, List<T> alarmList)throws Exception;
	
	protected abstract String getDir();
	
	protected  FileWriterReturnValue<T> writeListToFile(final List<T> alarmList,int fileNo) throws Exception {
		FileWriterReturnValue<T> fwrValue = new FileWriterReturnValue<>();
		LinkedList<T> fastAlarmList = new LinkedList<>(alarmList);
		List<String> filePathResult = new ArrayList<String>();
		T instance = alarmList.get(0);
		boolean isAlarmVo = false;
		if(instance instanceof AlarmVo )
			isAlarmVo = true;
		String fileHead = this.getFileHeader(instance) + adjustFileHeader(instance)+"-";
		int startNumber = fileNo;
		List<List<T>> pagedAlarmList = FilePagedUtil.pageAlarmList(fastAlarmList,Constants.MAX_FILE_SIZE,Constants.MAX_SINGLE_SIZE);
		String dateDir = getDir()+"/";
		String fullDir = this.getFileSavePath() + dateDir;
		boolean paged = pagedAlarmList.size()!=1;
		boolean pagedModel = Constants.FILE_PAGED_MODEL;
		for( int index=0; index < pagedAlarmList.size(); ++index ){
			List<T> almList = pagedAlarmList.get(index);
			if(isAlarmVo && (index==pagedAlarmList.size()-1 && index!=0)){
				fwrValue.setLastReturnValue(almList);
				continue;
			}
			String fullFilePath = fullDir + fileHead + getPerfCycle(alarmList.get(0));
			if(pagedModel || paged)
				fullFilePath += OmcDateFormater.formatDecimal(startNumber++);
			fullFilePath += this.getFileSuffix();
			File tempFile = new File(fullFilePath);
			FileUtils.forceMkdirParent(tempFile);
			this.writeFile(tempFile, Constants.ENCODING, almList);
			filePathResult.add(fullFilePath);
		}
		Thread.sleep(Constants.FILE_TMP_KEEP_SEC*1000);
		List<String> zipFilePath = Toolkit.removeSuffix(filePathResult);
		fwrValue.setFilePath(zipFilePath);
		fwrValue.setFileNo(startNumber);
		return fwrValue;
	}

	/**
	 *  if making alarm file, use real time mill for the file header.
	 *  otherwise,adjust the file header
	 * @param instance
	 * @return
	 */
	private  String adjustFileHeader(T instance) {
		String formate = OmcDateFormater.formatFileDate(System.currentTimeMillis());
		if(instance instanceof AlarmVo){
			AlarmVo vo = (AlarmVo)instance;
			if(Constants.ALM_FILE_HEADER_WITH_NAME)
				return formate+"-"+vo.getIpAddress();//ipaddress is login userName
			else
				return formate;
		}
		else{
			return formate.substring(0, formate.length()-4).concat("0000");
		}
	}

}
