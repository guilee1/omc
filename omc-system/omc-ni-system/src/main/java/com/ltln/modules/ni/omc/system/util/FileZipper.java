package com.ltln.modules.ni.omc.system.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileZipper {

	private static final String SUFFIX_ZIP_TMP = ".zip.tmp";

	public static List<String> zipCompress(List<String> filePath)throws Exception {
		List<String> tmpFilePath = new ArrayList<>();
		for(String file : filePath)
			tmpFilePath.add(zipFile(file));
		return tmpFilePath;//Toolkit.removeSuffix(tmpFilePath);
	}


	public static String zipFile(String sourceFile)throws Exception {
		File file = new File(sourceFile);
        File zipTmpFile = new File(sourceFile + SUFFIX_ZIP_TMP);
        int len;
        byte[] buf = new byte[4*1024];
        BufferedInputStream bis = null;
        ZipOutputStream zipOut = null;
        try{
	        bis = new BufferedInputStream(new FileInputStream(file));
	        zipOut = new ZipOutputStream(new FileOutputStream(zipTmpFile));
	        zipOut.putNextEntry(new ZipEntry(file.getName()));
	        while ( ( len = bis.read( buf ) ) != -1 ) {
	        	zipOut.write(buf,0,len);
			}
	        zipOut.flush();
        }finally{
        	if(bis!=null)
        		bis.close();
        	if(zipOut!=null)
        		zipOut.close();
        }
        file.delete();//here to delete txt file
        return sourceFile + SUFFIX_ZIP_TMP;
	}

}
