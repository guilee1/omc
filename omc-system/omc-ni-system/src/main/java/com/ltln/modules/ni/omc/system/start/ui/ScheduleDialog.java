package com.ltln.modules.ni.omc.system.start.ui;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.scheduler.PerfTask;
import com.ltln.modules.ni.omc.system.core.scheduler.ResourceTask;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;
import com.ltln.modules.ni.omc.system.util.ResTaskObj;

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
public class ScheduleDialog extends javax.swing.JDialog {
	private JTabbedPane jTabbedPane1;
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JButton btnAdd;
	private JButton btnCancel;
	private JButton btnOK;
	private JButton btnDel;
	private JButton btnRun;
	private JTable jTable2;
	private JTable jTable1;
	DefaultTableModel model;
	DefaultTableModel model2;
        private JPanel panel;
        private JLabel labelStar;
        private JLabel labelStop;
        private JTextField textStart;
        private JTextField textStop;
        private JPanel panelMain;

	/**
	* Auto-generated main method to display this JDialog
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				ScheduleDialog inst = new ScheduleDialog(frame);
				inst.setVisible(true);
			}
		});
	}
	
	public ScheduleDialog(JFrame frame) {
		super(frame);
		initGUI();
		this.setTitle(I18NLanguage.RESOURCE_ACQUISITION_CONFIGURATION);
		initTable();
	}
	
	private void initTable() {
		for(ResTaskObj ftpUser : Constants.RESTASK.values()){
			if(StringUtils.endsWithIgnoreCase(ftpUser.getName(), "resource"))
			model.addRow(new Object[]{model.getRowCount()+1,ftpUser.getTime(),ftpUser.isStart()});
		}
		for(ResTaskObj ftpUser : Constants.RESTASK.values()){
			if(StringUtils.endsWithIgnoreCase(ftpUser.getName(), "perf"))
			model2.addRow(new Object[]{model2.getRowCount()+1,ftpUser.getTime(),ftpUser.isStart()});
		}
	}

	private void initGUI() {
		try {
			{
				getContentPane().setLayout(null);
				{
                                    
					jTabbedPane1 = new JTabbedPane();
					getContentPane().add(jTabbedPane1);
					jTabbedPane1.setBounds(7, 6, 482, 383);
					{
						jScrollPane1 = new JScrollPane();
						jTabbedPane1.addTab("资源采集", null, jScrollPane1, null);
						{
							String[] header={I18NLanguage.NUMBER,I18NLanguage.TIME,I18NLanguage.STATE};
							model = new DefaultTableModel(header, 0);
							jTable1 = new JTable();
							jScrollPane1.setViewportView(jTable1);
							jTable1.setModel(model);
							jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							jTable1.setShowGrid(false);
							jTable1.getColumnModel().getColumn(0).setPreferredWidth(150);
							jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
							jTable1.getColumnModel().getColumn(2).setPreferredWidth(130);
							jTable1.getTableHeader().setReorderingAllowed(false);
						}
					}
					{
						jScrollPane2 = new JScrollPane();
                                                panelMain = new JPanel( new BorderLayout());
						jTabbedPane1.addTab("性能统计", null, panelMain, null);
						{
                                                    //添加开始时间和结束时间                     
                                                    panel = new JPanel();
                                                    panel.setVisible(true);
                                                    
                                                    labelStar = new JLabel(I18NLanguage.STARTTIME);
                                                    labelStar.setPreferredSize(new Dimension(60, 20));
                                                    textStart = new JTextField(OmcDateFormater.formatBasicDate(getZeroDate()));
                                                    textStart.setPreferredSize(new Dimension(130, 20));
                                                    labelStop = new JLabel(I18NLanguage.STOPTIME);
                                                    labelStop.setPreferredSize(new Dimension(60, 20));
                                                    textStop = new JTextField(OmcDateFormater.formatBasicDate(new Date()));
                                                    textStop.setPreferredSize(new Dimension(130, 20));
                                                    panel.setLayout(new FlowLayout());
                                                    panel.add(labelStar);
                                                    panel.add(textStart);
                                                    panel.add(labelStop);
                                                    panel.add(textStop);
                                                    
                                    
							String[] header={I18NLanguage.NUMBER,I18NLanguage.TIME,I18NLanguage.STATE};
							model2 = new DefaultTableModel(header, 0);
							jTable2 = new JTable();
							jScrollPane2.setViewportView(jTable2);
							jTable2.setModel(model2);
							jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
							jTable2.setShowGrid(false);
							jTable2.getColumnModel().getColumn(0).setPreferredWidth(150);
							jTable2.getColumnModel().getColumn(1).setPreferredWidth(150);
							jTable2.getColumnModel().getColumn(2).setPreferredWidth(130);
							jTable2.getTableHeader().setReorderingAllowed(false);
                                                        panelMain.add(panel, BorderLayout.NORTH);
                                                        panelMain.add(jScrollPane2, BorderLayout.CENTER);
						}
					}
				}
				{
					btnAdd = new JButton(I18NLanguage.ADD_TIMING_TASK);
					btnAdd.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
					getContentPane().add(btnAdd);
					btnAdd.setBounds(495, 31, 69, 24);
					btnAdd.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							DlgCreatetimeSelect();
							
						}
					});
				}
				{
					btnRun = new JButton(I18NLanguage.EXE_TIMING_TASK);
					btnRun.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
					getContentPane().add(btnRun);
					btnRun.setBounds(496, 75, 69, 24);
					btnRun.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if(MonitorFrame.updateFlag){
								JOptionPane.showMessageDialog(null,
										I18NLanguage.STORMTEST_INFO_POPUPWINDOW, "Failed",
										JOptionPane.INFORMATION_MESSAGE);
								return;
							}else{
								//根据tabPanel选择
								if(0==jTabbedPane1.getSelectedIndex()){
									ResourceTask task = SelfBeanFactoryAware.getBean("resourceTask");
									task.executeJob();
								}else{
									PerfTask task = SelfBeanFactoryAware.getBean("perfTask");
									String beginTime = textStart.getText().trim();
									String endTime = textStop.getText().trim();
									try {
										OmcDateFormater.parseBasicDate(beginTime);
										OmcDateFormater.parseBasicDate(endTime);
									} catch (ParseException e1) {
										JOptionPane.showMessageDialog(null,"valid format is:yyyy-MM-dd HH:mm:ss");
										return;
									}
									
									task.executeJob(beginTime,endTime);
								}
								JOptionPane.showMessageDialog(null,I18NLanguage.CONNECTION_SUCCESS_POPUPWINDOW);
							}
						}
					});
				}
				{
					btnDel = new JButton(I18NLanguage.DELETE_TASK);
					btnDel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
					getContentPane().add(btnDel);
					btnDel.setBounds(496, 119, 69, 24);
					btnDel.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(MonitorFrame.updateFlag){
								
								JTable tableObj;
								DefaultTableModel m;
								if(0==jTabbedPane1.getSelectedIndex()){
									tableObj = jTable1;
									m = model;
								}else{
									tableObj = jTable2;
									m = model2;
								}
								if (tableObj.getSelectedRow() == -1) {
									JOptionPane.showMessageDialog(null,
											I18NLanguage.FTPCONFIG_DELPOPWINDOW, "error",
											JOptionPane.ERROR_MESSAGE);
								} else {
										m.removeRow(tableObj.getSelectedRow());
										if(tableObj.getRowCount()>=1){
										for(int i=1;i<=tableObj.getRowCount();i++){
											m.setValueAt(i, i-1, 0);
										}}
									}
								
							}else {
						        JOptionPane.showMessageDialog(null, I18NLanguage.FTPCONFIG_DELETE_POPUPWINDOW, "Failed",
										JOptionPane.INFORMATION_MESSAGE);

					              }   	
							}
						});
				}
				{
					btnOK = new JButton(I18NLanguage.ABOUT_OK);
					btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
					getContentPane().add(btnOK);
					btnOK.setBounds(7, 394, 59, 24);
					btnOK.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Iterator<Map.Entry<String, ResTaskObj>> it = Constants.RESTASK.entrySet().iterator();
					        while(it.hasNext()){
					        	Map.Entry<String, ResTaskObj> entry = it.next();
					        	if(entry.getKey().startsWith("RES_") || entry.getKey().startsWith("PERF_"))
					        		it.remove();
					        }
					        
							constractResTaskObj(model,"resource","RES_");
							constractResTaskObj(model2,"perf","PERF_");
							Constants.save();
							JOptionPane.showMessageDialog(null, I18NLanguage.CONNECTION_SUCCESS_POPUPWINDOW, "success",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}

						private void constractResTaskObj(DefaultTableModel model,String name,String preffix) {
							for(int i=0;i<model.getRowCount();++i){
								int no = (int)model.getValueAt(i, 0);
								String time = (String)model.getValueAt(i, 1);
								boolean start = Boolean.parseBoolean(model.getValueAt(i, 2).toString());
								ResTaskObj obj = new ResTaskObj();
								obj.setCycle(0);
								obj.setName(name);
								obj.setStart(start);
								obj.setTime(time);
								Constants.RESTASK.put(preffix+no, obj);
							}
						}
					});
				}
				{
					btnCancel = new JButton(I18NLanguage.CANCEL);
					btnCancel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
					getContentPane().add(btnCancel);
					btnCancel.setBounds(489, 389, 76, 24);
					btnCancel.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dispose();
							
						}
					});
				}
			}
			this.setSize(587, 456);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DlgCreatetimeSelect() {
		DefaultTableModel model;
		if(0==jTabbedPane1.getSelectedIndex()){
			model = this.getModel();
		}else{
			model = this.getModel2();
		}
		AddTimingTask addTimT = new AddTimingTask(model);
		addTimT.setVisible(true);
	}

	public DefaultTableModel getModel() {
		return model;
	}
	
	public DefaultTableModel getModel2() {
		return model2;
	}
        private Date getZeroDate() {
			Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date());
	        calendar.set(Calendar.HOUR_OF_DAY, 0);
	        calendar.set(Calendar.MINUTE, 0);
	        calendar.set(Calendar.SECOND, 0);
	        Date zero = calendar.getTime();
			return zero;
		}
}
