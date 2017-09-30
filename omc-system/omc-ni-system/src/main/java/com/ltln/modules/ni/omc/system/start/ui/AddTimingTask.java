package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.lang3.StringUtils;

public class AddTimingTask extends JDialog {
	ButtonGroup group;
	String time;
	JSpinner spinnerH;
	JSpinner spinnerM;
	JSpinner spinnerS;

	JPanel panelNull;
	JPanel panelCenter;
	JPanel panelBottom;
	JPanel panelAddTT;
	
	DefaultTableModel model;
	
	public void addTable(){
		time=String.format("%02d", spinnerH.getValue())+":"+String.format("%02d", spinnerM.getValue())+":"+String.format("%02d", spinnerS.getValue());
		boolean start = false;
		if(StringUtils.endsWithIgnoreCase(group.getSelection().getActionCommand(), I18NLanguage.OPEN))
			start = true;
		model.addRow(new Object[]{model.getRowCount()+1,time,String.valueOf(start)});
	}
	
	public AddTimingTask(DefaultTableModel resourceAcquisition){
		this.model=resourceAcquisition;
		panelAddTimT();
		this.add(panelAddTT);
		this.setTitle(I18NLanguage.ADD_TIMING_TASK);
		this.setModal(true);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(550, 300);
		this.setResizable(false);
		this.setLocation(300, 200);
	}
	
	
	public void panelAddTimT(){
		panelAddTT=new JPanel(new BorderLayout());
		panelTop();
		panelAddTT.add(panelNull,BorderLayout.NORTH);
		panelCenter();
		panelAddTT.add(panelCenter,BorderLayout.CENTER);
		panelBottom();
		panelAddTT.add(panelBottom,BorderLayout.SOUTH);
	}
	public void panelTop(){
		panelNull=new JPanel();
	}
	public void panelCenter(){
		panelCenter=new JPanel(new GridBagLayout());
		TitledBorder titleTaskAllocation= new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)), I18NLanguage.TASK_ALLOCATION);
		titleTaskAllocation.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelCenter.setBorder(titleTaskAllocation);
		JLabel lblTime=new JLabel(I18NLanguage.TIMING_TASK_TIME);
		lblTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		
		//设置时分秒
		JLabel lblHour=new JLabel(I18NLanguage.HOUR);
		SpinnerNumberModel lblHModel = new SpinnerNumberModel(0,0,23, 1);
		spinnerH = new JSpinner(lblHModel);
		
		JLabel lblMinute=new JLabel(I18NLanguage.MINUTE);
		SpinnerNumberModel lblMModel = new SpinnerNumberModel(0,0,59, 1);
		spinnerM = new JSpinner(lblMModel);
		
		JLabel lblSecond=new JLabel(I18NLanguage.SECOND);
		SpinnerNumberModel lblSModel = new SpinnerNumberModel(0,0,59, 1);
		spinnerS = new JSpinner(lblSModel);
		//设置字体大小
		lblHour.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		lblMinute.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		lblSecond.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblState=new JLabel(I18NLanguage.TIMING_TASK_STATE);
		lblState.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		//设置单选按钮
		 group=new ButtonGroup();
		JRadioButton btnOpen=new JRadioButton(I18NLanguage.OPEN,true);
		btnOpen.setActionCommand(I18NLanguage.OPEN);
		
		JRadioButton btnClose=new JRadioButton(I18NLanguage.CLOSE);
		btnClose.setActionCommand(I18NLanguage.CLOSE);
		
		group.add(btnOpen);
		group.add(btnClose);
		
		btnOpen.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnClose.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		panelCenter.add(lblTime, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//时
		panelCenter.add(lblHour, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(spinnerH, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//分
		panelCenter.add(lblMinute, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(spinnerM, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		//秒
		panelCenter.add(lblSecond, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(spinnerS, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		panelCenter.add(lblState, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(btnOpen, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(btnClose, new GridBagConstraints(3, 1, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}
	public void panelBottom(){
		panelBottom=new JPanel(new BorderLayout());

		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK=new JButton(I18NLanguage.ABOUT_OK);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				boolean exist = false;
				String chooseValue = String.format("%02d",
						spinnerH.getValue())
						+ ":"
						+ String.format("%02d", spinnerM.getValue())
						+ ":" + String.format("%02d", spinnerS.getValue());
				for (int i = 0; i <= (model.getRowCount() - 1); i++) {
					if (chooseValue.equals(model.getValueAt(i,1))) {
						exist = true;
					}
				}
				if (exist) {
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(
							null,
							I18NLanguage.THIS_TIMING_TASK_HAS_BEEN_IN_EXISTENCE,
							"error", JOptionPane.ERROR_MESSAGE);
				} else {
					addTable();
					dispose();
				}

			}});
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JButton btnCancel=new JButton(I18NLanguage.CANCEL);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnCancel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelC.add(btnOK,BorderLayout.WEST);
		panelC.add(btnCancel,BorderLayout.EAST);
		panelBottom.add(panelC, BorderLayout.CENTER);
		panelBottom.add(panelN, BorderLayout.NORTH);
		panelBottom.add(panelS, BorderLayout.SOUTH);
		panelBottom.add(panelW, BorderLayout.WEST);
		panelBottom.add(panelE, BorderLayout.EAST);
	}
	}
