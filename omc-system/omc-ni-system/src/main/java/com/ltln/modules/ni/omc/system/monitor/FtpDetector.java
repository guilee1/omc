package com.ltln.modules.ni.omc.system.monitor;

import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class FtpDetector implements IDetect {

	@Override
	public boolean detect() {
		return Toolkit.connectSelf(Constants.FTP_SERVER_PORT);
	}

}
