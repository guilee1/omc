package com.ltln.modules.ni.omc.system.manager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;

import com.ltln.modules.ni.omc.system.monitor.IDetector;
import com.ltln.modules.ni.omc.system.monitor.SystemDetector;

public  class SystemManager implements ISystem ,IDetector{

	IDetector proxy;
	List<ISystem> managerList = new ArrayList<>();
	List<String> result = new ArrayList<>();
	static final String START_SUCC = " start successfully";
	static final String START_FAIL = " start fail";
	static final String STOP_SUCC = " stop successfully";
	static final String STOP_FAIL = " stop fail";
	
	public SystemManager(JTable tableConnection) {
		managerList.add(new DbManager());
		managerList.add(new ContainerManager());
		managerList.add(new JmsManager());
		managerList.add(new ConnListenerManager(tableConnection));
		managerList.add(new TaskManager());
		managerList.add(new SchedulerManager());
		managerList.add(new FtpManager());
		proxy = DetectorInvocationHandler.createProxiedIntf(IDetector.class, new SystemDetector());
	}
	
	@Override
	public boolean startUp() {
		boolean isSucc = true;
		result.clear();
		for(ISystem manager : managerList){
			if(manager.startUp())
				this.result.add(manager.getName() + START_SUCC);
			else{
				this.result.add(manager.getName() + START_FAIL);
				isSucc = false;
			}
		}
		return isSucc;
	}

	@Override
	public boolean shutDown() {
		result.clear();
		boolean isSucc = true;
		for(int i=managerList.size()-1;i>=0;--i){
			ISystem manager = managerList.get(i);
			if(manager.shutDown())
				this.result.add(manager.getName() + STOP_SUCC);
			else{
				this.result.add(manager.getName() + STOP_FAIL);
				isSucc = false;
			}
		}
		return isSucc;
	}


	public List<String> getResult(){
		return this.result;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean RmiDetect() {
		return proxy.RmiDetect();
	}

	@Override
	public boolean JmsDetect() {
		return proxy.JmsDetect();
	}

	@Override
	public boolean DbDetect() {
		return proxy.DbDetect();
	}

	@Override
	public boolean AlmServerDetect() {
		return proxy.AlmServerDetect();
	}

	@Override
	public boolean FtpDetect() {
		return proxy.FtpDetect();
	}

	@Override
	public boolean InstructionServerDetect() {
		return proxy.InstructionServerDetect();
	}
}
