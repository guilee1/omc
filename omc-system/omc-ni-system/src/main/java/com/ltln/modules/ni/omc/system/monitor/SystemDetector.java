package com.ltln.modules.ni.omc.system.monitor;

public class SystemDetector implements IDetector {

	@Override
	public boolean RmiDetect() {
		return new RmiDetector().detect();
	}

	@Override
	public boolean JmsDetect() {
		return new JmsDetector().detect();
	}

	@Override
	public boolean DbDetect() {
		return new DbDetector().detect();
	}

	@Override
	public boolean AlmServerDetect() {
		return new AlmServerDetector().detect();
	}

	@Override
	public boolean FtpDetect() {
		return new FtpDetector().detect();
	}

	@Override
	public boolean InstructionServerDetect() {
		return new InstructionServerDetector().detect();
	}

}
