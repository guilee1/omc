package com.ltln.modules.ni.omc.system.monitor;

import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class InstructionServerDetector implements IDetect {

	@Override
	public boolean detect() {
		return Toolkit
				.connectSelf(Constants.INSTRUCTION_SSL ? Constants.INSTRUCTION_SSL_PORT
						: Constants.INSTRUCTION_PORT);
	}

}
