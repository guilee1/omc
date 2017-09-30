package com.ltln.modules.ni.omc.system.manager;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;

public class ContainerManager implements ISystem {

	static final String SPRING_XML_FILE = "file:" + Constants.rootDir + "envconf/applicationContext.xml";
	public static FileSystemXmlApplicationContext app;
	
	@Override
	public boolean startUp() {
		try{
			app = new FileSystemXmlApplicationContext(SPRING_XML_FILE);
		}catch (Exception e) {
			Logger.error("error occu in Spring startUp", e);
			return false;
		}
		return true;
	}

	@Override
	public boolean shutDown() {
		if(app!=null)
			app.close();
		app = null;
		return true;
	}

	@Override
	public String getName() {
		return "IoC Container";
	}

}
