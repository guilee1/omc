package com.ltln.modules.ni.omc.system.manager;

import java.io.IOException;

import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.MySQLUtil;

public class DbManager implements ISystem {

	public static final String MYSQLPATH = Constants.usersDir + "MySQL";
	
	@Override
	public boolean startUp() {
		try {
			MySQLUtil.startMysql(MYSQLPATH);
		} catch (IOException e) {
			Logger.error("mysql server can not start!",e);
			return false;
		}
		return true;
	}

	@Override
	public boolean shutDown() {
		try {
			MySQLUtil.stopMysqld(MYSQLPATH);
		} catch (Exception e) {
			Logger.error("mysql server can not stop!",e);
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		return "Mysql Service";
	}

	public static void main(String[] args) {
		new DbManager().shutDown();
	}
}
