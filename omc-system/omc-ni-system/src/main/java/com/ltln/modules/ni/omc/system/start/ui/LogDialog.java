package com.ltln.modules.ni.omc.system.start.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;
import com.ltln.modules.ni.omc.system.core.alarm.log.AlarmLog;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.dao.SysOperLog;
import java.awt.BorderLayout;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class LogDialog extends javax.swing.JDialog {
	private JTabbedPane jTabbedPane1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane3;
	private JTable tblOper;
	private JTable tblAlmLog;
	private JTable tblSender;
	private JButton btnRef;
	private JButton btnOperNext;
	private JButton btnOperPrev;
	private JLabel lblOperTxt;
	private JPanel jPanel1;
	
	int operLog_current_page;
	int operLog_total_page;

	int almLog_current_page;
	int almLog_total_page;
	
	int senderLog_current_page;
	int senderLog_total_page;
	
	IOmcDao dao = SelfBeanFactoryAware.getDao();
	
	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				LogDialog inst = new LogDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public LogDialog(JFrame frame) {
		super(frame);
		initGUI();
		refresh_OperLog();
	}
	
	private void initGUI() {
		try {
			{
				BorderLayout thisLayout = new BorderLayout();
				getContentPane().setLayout(thisLayout);
				this.setTitle(I18NLanguage.MONITOR_ABOUT);
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1, BorderLayout.SOUTH);
					jPanel1.setBounds(8, 423, 703, 28);
					{
						btnRef = new JButton();
						jPanel1.add(btnRef);
						btnRef.setText(I18NLanguage.REFRESH);
						btnRef.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								refresh();
							}
						});
					}
					{
						lblOperTxt = new JLabel();
						jPanel1.add(lblOperTxt);
						lblOperTxt.setText(I18NLanguage.TOTAL);
					}
					{
						btnOperPrev = new JButton();
						jPanel1.add(btnOperPrev);
						btnOperPrev.setText(I18NLanguage.PREV);
						btnOperPrev.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								prev();
							}
						});
					}
					{
						btnOperNext = new JButton();
						jPanel1.add(btnOperNext);
						btnOperNext.setText(I18NLanguage.NEXT);
						btnOperNext.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								next();
							}
						});
					}
				}
				{
					jTabbedPane1 = new JTabbedPane();
					getContentPane().add(jTabbedPane1, BorderLayout.CENTER);
					{
						tblOper = new JTable();
						jScrollPane1 = new JScrollPane(tblOper);
						jTabbedPane1.addTab(I18NLanguage.DUMP_TABBEDPANELDUMP_ADDOPERATION, null, jScrollPane1, null);
						{
							TableModel tblOperModel = 
									new DefaultTableModel(
											new String[][] {},
											new String[] { "logId", "nmsIp","userName","connectTime","disConnectTime" });
							tblOper.setModel(tblOperModel);
						}
					}
					{
						tblAlmLog = new JTable();
						jScrollPane2 = new JScrollPane(tblAlmLog);
						jTabbedPane1.addTab(I18NLanguage.DUMP_TABBEDPANELDUMP_ADDALARM, null, jScrollPane2, null);
						{
							TableModel tblOperModel = 
									new DefaultTableModel(
											new String[][] {},
											new String[] { "id", "alarmSeq","alarmTitle","alarmStatus","alarmType","origSeverity","eventTime",
													"eventTimeMills","alarmId","specificProblemID","specificProblem","neUID","neName","neType",
													"objectUID","objectName","objectType","locationInfo","holderType","alarmCheck","layer" });
							tblAlmLog.setModel(tblOperModel);
						}
					}
					{
						tblSender = new JTable();
						jScrollPane3 = new JScrollPane(tblSender);
						jTabbedPane1.addTab(I18NLanguage.SEND_LOG, null, jScrollPane3, null);
						{
							TableModel tblOperModel = 
									new DefaultTableModel(
											new String[][] {},
											new String[] { "logId", "nmsIp","userName","alarmSequenceId","pushTime"});
							tblSender.setModel(tblOperModel);
						}
					}
				}
			}
			this.setSize(886, 590);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setTotalLabel(int total,int current) {
		this.lblOperTxt.setText(I18NLanguage.TOTAL+total+" "+I18NLanguage.CURRENT+current);
	}
	
	
	int pageSize = 200;
	
	int getTotalPageSize(String tblName){
		return dao.getTotalSize(tblName);
	}

	void refresh(){
		switch (this.jTabbedPane1.getSelectedIndex()) {
		case 0:
			refresh_OperLog();
			break;
		case 1:
			refresh_Almlog();
			break;
		case 2:
			refresh_Senderlog();
			break;
		default:
			break;
		}
	}
	
	void next(){
		switch (this.jTabbedPane1.getSelectedIndex()) {
		case 0:
			next_OperLog();
			break;
		case 1:
			next_Almlog();
			break;
		case 2:
			next_Senderlog();
			break;
		default:
			break;
		}
	}
	
	void prev(){
		switch (this.jTabbedPane1.getSelectedIndex()) {
		case 0:
			prev_OperLog();
			break;
		case 1:
			prev_Almlog();
			break;
		case 2:
			prev_Senderlog();
			break;
		default:
			break;
		}
	}
	
	
	//--------------sysOperlog
	private void buildTable_Operlog(int page) {
		List<SysOperLog> sysOperLogs = getCurrentOperLog(page);
		DefaultTableModel model = (DefaultTableModel)tblOper.getModel();
		model.getDataVector().clear();
		for(SysOperLog item : sysOperLogs){
			String connTime = item.getConnectTime()!=null?item.getConnectTime().toLocaleString():"";
			String disTime = item.getDisConnectTime()!=null?item.getDisConnectTime().toLocaleString():"";
			model.addRow(new Object[]{item.getLogId(),item.getNmsIp(),item.getUserName(),connTime,disTime});
		}
		model.fireTableDataChanged();
	}

	private void refresh_OperLog(){
		buildTable_Operlog(1);
		int totalRow = getTotalPageSize("sysOperlog");
		operLog_total_page = totalRow/pageSize+(totalRow%pageSize==0?0:1);
		operLog_current_page = 1;
		setTotalLabel(operLog_total_page,operLog_current_page);
	}
	
	private void next_OperLog(){
		if(operLog_current_page==operLog_total_page){
			JOptionPane.showMessageDialog(this,"end of the total!");
			return;
		}
		setTotalLabel(operLog_total_page,++operLog_current_page);
		buildTable_Operlog(operLog_current_page);
	}
	
	private void prev_OperLog(){
		if(operLog_current_page==1){
			JOptionPane.showMessageDialog(this,"begin of the total!");
			return;
		}
		setTotalLabel(operLog_total_page,--operLog_current_page);
		buildTable_Operlog(operLog_current_page);
	}
	
	
	List<SysOperLog> getCurrentOperLog(int pageNo){
		int from = (pageNo-1) * pageSize;
		return dao.querySysOperLog(from, pageSize);
	}
	
	
	//------------------almLog
	private void buildTable_Almlog(int page) {
		List<AlarmVo> sysOperLogs = getCurrentAlmLog(page);
		DefaultTableModel model = (DefaultTableModel)tblAlmLog.getModel();
		model.getDataVector().clear();
		for(AlarmVo item : sysOperLogs){
			model.addRow(new Object[]{item.getId(),item.getAlarmSeq(),item.getAlarmTitle(),
					item.getAlarmStatus(),item.getAlarmType(),item.getOrigSeverity(),
					item.getEventTime(),item.getEventTimeMills(),item.getAlarmId(),
					item.getSpecificProblemID(),item.getSpecificProblem(),item.getNeUID(),
					item.getNeName(),item.getNeType(),item.getObjectUID(),item.getObjectName(),
					item.getObjectType(),item.getLocationInfo(),item.getHolderType(),
					item.getAlarmCheck(),item.getLayer()
					});
		}
		model.fireTableDataChanged();
	}

	private void refresh_Almlog(){
		buildTable_Almlog(1);
		int totalRow = getTotalPageSize("alarmserial");
		almLog_total_page = totalRow/pageSize+(totalRow%pageSize==0?0:1);
		almLog_current_page = 1;
		setTotalLabel(almLog_total_page,almLog_current_page);
	}
	
	private void next_Almlog(){
		if(almLog_current_page==almLog_total_page){
			JOptionPane.showMessageDialog(this,"end of the total!");
			return;
		}
		setTotalLabel(almLog_total_page,++almLog_current_page);
		buildTable_Almlog(almLog_current_page);
	}
	
	private void prev_Almlog(){
		if(almLog_current_page==1){
			JOptionPane.showMessageDialog(this,"begin of the total!");
			return;
		}
		setTotalLabel(almLog_total_page,--almLog_current_page);
		buildTable_Almlog(almLog_current_page);
	}
	List<AlarmVo> getCurrentAlmLog(int pageNo){
		int from = (pageNo-1) * pageSize;
		return dao.queryAlmLog(from, pageSize);
	}
	
	//---------------------Sender
	List<AlarmLog> getCurrentSender(int pageNo){
		int from = (pageNo-1) * pageSize;
		return dao.querySenderLog(from, pageSize);
	}
	private void buildTable_SenderLog(int page) {
		List<AlarmLog> sysOperLogs = getCurrentSender(page);
		DefaultTableModel model = (DefaultTableModel)tblSender.getModel();
		model.getDataVector().clear();
		for(AlarmLog item : sysOperLogs){
			model.addRow(new Object[]{item.getLogId(),item.getNmsIp(),item.getUserName(),item.getAlarmSequenceId(),
					item.getPushTime().toLocaleString()});
		}
		model.fireTableDataChanged();
	}

	private void refresh_Senderlog(){
		buildTable_SenderLog(1);
		int totalRow = getTotalPageSize("alarmlog");
		senderLog_total_page = totalRow/pageSize+(totalRow%pageSize==0?0:1);
		senderLog_current_page = 1;
		setTotalLabel(senderLog_total_page,senderLog_current_page);
	}
	
	private void next_Senderlog(){
		if(senderLog_current_page==senderLog_total_page){
			JOptionPane.showMessageDialog(this,"end of the total!");
			return;
		}
		setTotalLabel(senderLog_total_page,++senderLog_current_page);
		buildTable_SenderLog(senderLog_current_page);
	}
	
	private void prev_Senderlog(){
		if(senderLog_current_page==1){
			JOptionPane.showMessageDialog(this,"begin of the total!");
			return;
		}
		setTotalLabel(senderLog_total_page,--senderLog_current_page);
		buildTable_SenderLog(senderLog_current_page);
	}
}
