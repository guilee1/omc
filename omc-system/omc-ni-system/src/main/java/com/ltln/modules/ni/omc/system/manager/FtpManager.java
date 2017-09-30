package com.ltln.modules.ni.omc.system.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.TransferRatePermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.ftp.OmcFtplet;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.FTPUserObj;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class FtpManager implements ISystem {

	DefaultFtpServer server;
	
	@Override
	public boolean startUp() {
		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory factory = new ListenerFactory();
		factory.setPort(Constants.FTP_SERVER_PORT);
		factory.setIdleTimeout(Constants.FTP_SERVER_IDL_SEC);
		serverFactory.addListener("default", factory.createListener());
		server = (DefaultFtpServer)serverFactory.createServer();         
		try {
			server.getFtplets().put("omcFtplet", (OmcFtplet)SelfBeanFactoryAware.getBean("omcFtplet"));
			server.start();
			Logger.info("ftp server has started!");
		} catch (Exception e) {
			Logger.error("ftp server start error!", e);
			return false;
		}
		try{
			for(FTPUserObj user : Constants.FTPUSERS.values()){
				BaseUser userObj = new BaseUser();
				userObj.setName(user.getAccount());
				userObj.setPassword(user.getPwd());
				userObj.setHomeDirectory(Constants.FTP_SERVER_ROOT_PATH);
				TransferRatePermission transPermission = new TransferRatePermission(0, 0);
				ConcurrentLoginPermission loginPermission = new ConcurrentLoginPermission(0, 0);
				List<Authority> permissionList = new ArrayList<>();
				permissionList.add(loginPermission);
				permissionList.add(transPermission);
				if(user.isPermission()){
					WritePermission permission = new WritePermission();
					permissionList.add(permission);
				}
				userObj.setAuthorities(permissionList);
				server.getUserManager().save(userObj);
			}
		}catch (Exception e) {
			Logger.error("set ftp root dir error!",e);
			return false;
		}
		return true;
	}

	@Override
	public boolean shutDown() {
		if(server!=null)
			server.stop();
		return true;
	}

	@Override
	public String getName() {
		return "FTP Service";
	}

	public static void main(String[] args) throws Exception{
        System.out.println(Toolkit.string2MD5("admin"));
	}
}
