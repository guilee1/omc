package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.ResTaskObj;

public class Dump extends JDialog {
	JTabbedPane tabbedPaneDump;
	JPanel PanelRunningWateRecord;
	JPanel panelMessageLog;
	JPanel panelOperationLog;
	JPanel panelBackUp;
	JPanel bottomPanel;
	InputStream in;
	// Properties propIN;
	// OutputStream oFile;
	// Properties prop = new Properties();
	DrawDumpMainPanel runningWateRecord = new DrawDumpMainPanel();
	DrawDumpMainPanel MessageLog = new DrawDumpMainPanel();
	DrawDumpMainPanel OperationLog = new DrawDumpMainPanel();
	DrawDumpMainPanel FileBackup = new DrawDumpMainPanel();

	public Dump() {

		this.setModal(true);
		this.setTitle(I18NLanguage.DUMP_TITLE);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel panelNullAlarm = new JPanel();
		topPanel();
		mainPanel.add(tabbedPaneDump, BorderLayout.CENTER);
		mainPanel.add(panelNullAlarm, BorderLayout.NORTH);
		bottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(mainPanel);

		this.setSize(450, 300);
		this.setResizable(false);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void topPanel() {
		// 从配置文件中读取信息

		tabbedPaneDump = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		PanelRunningWateRecord = new JPanel(new BorderLayout());
		JPanel panelNullAlarm = new JPanel();

		runningWateRecord.drawDump();
		// 从properties文件里读取
		runningWateRecord.getTextField().setText(Constants.DUMP_ALM_SEQ_TBL_DIRECTORY);
		runningWateRecord.getTextFieldTime().setText(Constants.DUMP_ALM_SEQ_TBL_TIME);
		if ((Integer) Constants.DUMP_ALM_SEQ_TBL_CYCLE != null) {
			if (Constants.DUMP_ALM_SEQ_TBL_CYCLE == 2) {
				runningWateRecord.getMonth().setSelected(true);
			} else if (Constants.DUMP_ALM_SEQ_TBL_CYCLE == 1) {
				runningWateRecord.getWeek().setSelected(true);
			} else if (Constants.DUMP_ALM_SEQ_TBL_CYCLE == 0) {
				runningWateRecord.getDay().setSelected(true);
			}
		}

		if (String.valueOf(Constants.DUMP_ALM_SEQ_TBL) != null) {
			if (Constants.DUMP_ALM_SEQ_TBL) {
				runningWateRecord.getOpen().setSelected(true);
			} else if (!Constants.DUMP_ALM_SEQ_TBL) {
				runningWateRecord.getClose().setSelected(true);
			}
		}

		PanelRunningWateRecord.add(runningWateRecord.panelDump, BorderLayout.CENTER);

		PanelRunningWateRecord.add(panelNullAlarm, BorderLayout.NORTH);

		tabbedPaneDump.addTab(I18NLanguage.DUMP_TABBEDPANELDUMP_ADDALARM, PanelRunningWateRecord);
		// 建一个messagelog面板
		panelMessageLog = new JPanel(new BorderLayout());
		JPanel panelNullFtp = new JPanel();

		panelMessageLog.add(panelNullFtp, BorderLayout.NORTH);

		MessageLog.drawDump();
		// 从properties文件里读取
		MessageLog.getTextField().setText(Constants.DUMP_ALM_LOG_TBL_DIRECTORY);
		MessageLog.getTextFieldTime().setText(Constants.DUMP_ALM_LOG_TBL_TIME);
		if ((Integer) Constants.DUMP_ALM_LOG_TBL_CYCLE != null) {
			if (Constants.DUMP_ALM_LOG_TBL_CYCLE == 2) {
				MessageLog.getMonth().setSelected(true);
			} else if (Constants.DUMP_ALM_LOG_TBL_CYCLE == 1) {
				MessageLog.getWeek().setSelected(true);
			} else if (Constants.DUMP_ALM_LOG_TBL_CYCLE == 0) {
				MessageLog.getDay().setSelected(true);
			}
		}
		if (String.valueOf(Constants.DUMP_SYS_LOG_TBL) != null) {
			if (Constants.DUMP_ALM_LOG_TBL) {
				MessageLog.getOpen().setSelected(true);
			} else if (!Constants.DUMP_ALM_LOG_TBL) {
				MessageLog.getClose().setSelected(true);
			}
		}
		panelMessageLog.add(MessageLog.panelDump, BorderLayout.CENTER);

		tabbedPaneDump.addTab(I18NLanguage.DUMP_TABBECPANELDUMP_ADDMESSAGE, panelMessageLog);
		// 建一个operationlog面板
		panelOperationLog = new JPanel(new BorderLayout());
		JPanel panelNullInstructions = new JPanel();

		panelOperationLog.add(panelNullInstructions, BorderLayout.NORTH);

		OperationLog.drawDump();
		// 从properties文件里读取

		OperationLog.getTextField().setText(Constants.DUMP_SYS_LOG_TBL_DIRECTORY);
		OperationLog.getTextFieldTime().setText(Constants.DUMP_SYS_LOG_TBL_TIME);
		if ((Integer) Constants.DUMP_ALM_LOG_TBL_CYCLE != null

		) {
			if (Constants.DUMP_SYS_LOG_TBL_CYCLE == 2) {
				OperationLog.getMonth().setSelected(true);
			} else if (Constants.DUMP_SYS_LOG_TBL_CYCLE == 1) {
				OperationLog.getWeek().setSelected(true);
			} else if (Constants.DUMP_SYS_LOG_TBL_CYCLE == 0) {
				OperationLog.getDay().setSelected(true);
			}
		}
		if (String.valueOf(Constants.DUMP_SYS_LOG_TBL) != null) {
			if (Constants.DUMP_SYS_LOG_TBL) {
				OperationLog.getOpen().setSelected(true);
			} else if (!Constants.DUMP_SYS_LOG_TBL) {
				OperationLog.getClose().setSelected(true);
			}
		}
		panelOperationLog.add(OperationLog.panelDump, BorderLayout.CENTER);

		tabbedPaneDump.addTab(I18NLanguage.DUMP_TABBEDPANELDUMP_ADDOPERATION, panelOperationLog);

		// 建一个file backup面板
		panelBackUp = new JPanel(new BorderLayout());
		JPanel panelNullBackUp = new JPanel();

		panelBackUp.add(panelNullBackUp, BorderLayout.NORTH);

		FileBackup.drawDump();
		// 从properties文件里读取

		FileBackup.getTextField().setText(Constants.DUMP_FILEBACKUP_TBL_DIRECTORY);
		FileBackup.getTextFieldTime().setText(Constants.DUMP_FILEBACKUP_TBL_TIME);
		if ((Integer) Constants.DUMP_FILEBACKUP_TBL_CYCLE != null

		) {
			if (Constants.DUMP_FILEBACKUP_TBL_CYCLE == 2) {
				FileBackup.getMonth().setSelected(true);
			} else if (Constants.DUMP_FILEBACKUP_TBL_CYCLE == 1) {
				FileBackup.getWeek().setSelected(true);
			} else if (Constants.DUMP_FILEBACKUP_TBL_CYCLE == 0) {
				FileBackup.getDay().setSelected(true);
			}
		}
		if (String.valueOf(Constants.DUMP_FILEBACKUP_TBL) != null) {
			if (Constants.DUMP_FILEBACKUP_TBL) {
				FileBackup.getOpen().setSelected(true);
			} else if (!Constants.DUMP_FILEBACKUP_TBL) {
				FileBackup.getClose().setSelected(true);
			}
		}
		panelBackUp.add(FileBackup.panelDump, BorderLayout.CENTER);

		tabbedPaneDump.addTab(I18NLanguage.DUMP_TABBEDPANELDUMP_ADDFILEBACKUP, panelBackUp);
		tabbedPaneDump.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

	}

	public void bottomPanel() {

		bottomPanel = new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK = new JButton(I18NLanguage.DUMP_BTNOK);
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		// 根据MonitorFrame中updateFlag判断是否可进行操作
		// if(MonitorFrame.updateFlag){
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MonitorFrame.updateFlag) {

					if (runningWateRecord.getGroup().getSelection().getActionCommand().isEmpty()
							|| runningWateRecord.getTextFieldTime().getText().isEmpty()
							|| runningWateRecord.getGroupSwitch().getSelection().getActionCommand().isEmpty()
							|| MessageLog.getGroup().getSelection().getActionCommand().isEmpty()
							|| MessageLog.getTextFieldTime().getText().isEmpty()
							|| MessageLog.getGroupSwitch().getSelection().getActionCommand().isEmpty()
							|| OperationLog.getGroup().getSelection().getActionCommand().isEmpty()
							|| OperationLog.getGroupSwitch().getSelection().getActionCommand().isEmpty()
							|| OperationLog.getTextFieldTime().getText().isEmpty()
							|| FileBackup.getGroup().getSelection().getActionCommand().isEmpty()
							|| FileBackup.getGroupSwitch().getSelection().getActionCommand().isEmpty()
							|| FileBackup.getTextFieldTime().getText().isEmpty()) {
						JOptionPane.showMessageDialog(PanelRunningWateRecord, I18NLanguage.DUMP_IFPOPUPWINDOW,
								"error", JOptionPane.ERROR_MESSAGE);
					} else
					updateDumpProperty("ALM_SEQ", runningWateRecord.getTextField().getText(), 
							runningWateRecord.getGroup().getSelection().getActionCommand(),
							runningWateRecord.getGroupSwitch().getSelection().getActionCommand(), 
							runningWateRecord.getTextFieldTime().getText());
						
					updateDumpProperty("ALM_LOG", MessageLog.getTextField().getText(), 
							MessageLog.getGroup().getSelection().getActionCommand(),
							MessageLog.getGroupSwitch().getSelection().getActionCommand(), 
							MessageLog.getTextFieldTime().getText());
					
					updateDumpProperty("SYS_LOG", OperationLog.getTextField().getText(), 
							OperationLog.getGroup().getSelection().getActionCommand(),
							OperationLog.getGroupSwitch().getSelection().getActionCommand(), 
							OperationLog.getTextFieldTime().getText());
					
					updateDumpProperty("FILEBACKUP", FileBackup.getTextField().getText(), 
							FileBackup.getGroup().getSelection().getActionCommand(),
							FileBackup.getGroupSwitch().getSelection().getActionCommand(), 
							FileBackup.getTextFieldTime().getText());
					
						Constants.save();
					
					JOptionPane.showMessageDialog(PanelRunningWateRecord, I18NLanguage.DUMP_SUCCESS, "success",
							JOptionPane.INFORMATION_MESSAGE);

					dispose();
				} else {
					JOptionPane.showMessageDialog(PanelRunningWateRecord, I18NLanguage.DUMP_ELSEPOPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);

					dispose();

				}
			}
		}

		);
		
		
		JButton btnCancel = new JButton(I18NLanguage.DUMP_BTNCANCEL);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnCancel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelC.add(btnOK, BorderLayout.WEST);

		panelC.add(btnCancel, BorderLayout.EAST);
		bottomPanel.add(panelC, BorderLayout.CENTER);
		bottomPanel.add(panelN, BorderLayout.NORTH);
		bottomPanel.add(panelS, BorderLayout.SOUTH);
		bottomPanel.add(panelW, BorderLayout.WEST);
		bottomPanel.add(panelE, BorderLayout.EAST);
	}

	private void updateDumpProperty(String key,String dir,String cycle,String status,String time){
		ResTaskObj obj = Constants.RESTASK.get(key);
		if(obj==null)
			return;
		obj.setDir(dir);
		if(cycle.equals("everyday")){
			obj.setCycle(0);
		}else if(cycle.equals("everyweek")){
			obj.setCycle(1);
		}else if(cycle.equals("everymonth")){
			obj.setCycle(2);
		}
		if(status.equals("open")){
			obj.setStart(true);
		}else {
			obj.setStart(false);
		}
		obj.setTime(time);
	}
}
