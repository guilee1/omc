package com.ltln.modules.ni.omc.system.manager;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.dao.SysOperLog;
import com.ltln.modules.ni.omc.system.core.listener.ConnectionModel;
import com.ltln.modules.ni.omc.system.core.listener.IConnListenerContainer;
import com.ltln.modules.ni.omc.system.core.listener.IConnectionListener;

public class ConnListenerManager implements IConnectionListener,ISystem {

	JTable tableConnection;
	List<ConnectionModel> tableModel = new LinkedList<>();
	IConnListenerContainer almListContainer;
	IConnListenerContainer instructionListContainer;
	IConnListenerContainer ftpListContainer;
	
	public ConnListenerManager(JTable table){
		this.tableConnection = table;
	}
	
	@Override
	public void ConnectionActive(final ConnectionModel client) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel model = (DefaultTableModel)tableConnection.getModel();
				tableModel.add(client);
				model.addRow(new Object[]{tableModel.size(),client.getIpAddr(),client.getType().name()});
				model.fireTableDataChanged();
			}
		});
		SysOperLog logObj = new SysOperLog(client.getIpAddr(), client.getUser(), client.getTime(), null,"ConnectionActive");
		SelfBeanFactoryAware.getDao().insertSysLog(logObj);
	}

	@Override
	public void ConnectionInactive(final ConnectionModel client) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				DefaultTableModel model = (DefaultTableModel)tableConnection.getModel();
				int index = tableModel.indexOf(client);
				tableModel.remove(index);
				model.getDataVector().clear();
				for(int i=0;i<tableModel.size();++i){
					ConnectionModel item = tableModel.get(i);
					model.addRow(new Object[]{i+1,item.getIpAddr(),item.getType().name()});
				}
				model.fireTableDataChanged();
			}
		});
		SysOperLog logObj = new SysOperLog(client.getIpAddr(), client.getUser(), null,client.getTime(),"ConnectionInactive");
		SelfBeanFactoryAware.getDao().insertSysLog(logObj);
	}

	@Override
	public boolean startUp() {
		// dependent on other services
		almListContainer = SelfBeanFactoryAware.getBean("alarmServerHandler");
		instructionListContainer = SelfBeanFactoryAware.getBean("telnetServerHandler");
		ftpListContainer = SelfBeanFactoryAware.getBean("omcFtplet");
		almListContainer.registerListener(this);
		ftpListContainer.registerListener(this);
		instructionListContainer.registerListener(this);
		return true;
	}

	@Override
	public boolean shutDown() {
		DefaultTableModel model = (DefaultTableModel)tableConnection.getModel();
		model.getDataVector().clear();
		model.fireTableDataChanged();
		return true;
	}

	@Override
	public String getName() {
		return "Connection Listener Service";
	}

	@Override
	public void ConnectionLogin(final ConnectionModel client) {
		SysOperLog logObj = new SysOperLog(client.getIpAddr(), client.getUser(), client.getTime(), null,"ConnectionLogin");
		SelfBeanFactoryAware.getDao().insertSysLog(logObj);
	}

}
