package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class TimeSelect extends JDialog {
	JPanel panelMain;
	JPanel panelCenter;
	JPanel bottomPanel;
	DrawDumpMainPanel drawDumpMainPanel;
	JTextField textFieldTime;
	JSpinner spinnerH;
	JSpinner spinnerM;
	JSpinner spinnerS;
	ButtonGroup group;
	JRadioButton day;
	JRadioButton week;
	JRadioButton month;
	
	
	
	
	public TimeSelect(DrawDumpMainPanel drawDumpMainPanel){
		this.setModal(true);
		this.drawDumpMainPanel=drawDumpMainPanel;
		this.textFieldTime=drawDumpMainPanel.getTextFieldTime();
		this.group=drawDumpMainPanel.getGroup();
		this.day=drawDumpMainPanel.getDay();
		this.week=drawDumpMainPanel.getWeek();
		this.month=drawDumpMainPanel.getMonth();
		drawCenter();
		panelMain=new JPanel(new BorderLayout());
		panelMain.setBackground(Color.white);
		JPanel panelNull=new JPanel();
		panelMain.add(panelNull,BorderLayout.NORTH);
		panelMain.add(panelCenter,BorderLayout.CENTER);
		bottomPanel();
		panelMain.add(bottomPanel,BorderLayout.SOUTH);
		this.add(panelMain);
		this.setSize(300, 200);
		this.setResizable(false);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
	}
	
	public void drawCenter(){
		panelCenter=new JPanel(new GridBagLayout());
		panelCenter.setBackground(Color.white);
		JLabel lblH=new JLabel("hour:");
		SpinnerNumberModel lblHModel = new SpinnerNumberModel(Integer.parseInt(String.format("%02d",00)) , 00, 23, 01);
	
		spinnerH = new JSpinner(lblHModel);
		spinnerH.setPreferredSize(new Dimension(100, 20));
		
		
		JLabel lblM=new JLabel("minute:");
		SpinnerNumberModel lblMModel = new SpinnerNumberModel(00, 00, 59, 01);
	
		spinnerM = new JSpinner(lblMModel);
		spinnerM.setPreferredSize(new Dimension(100, 20));
		
		
		JLabel lblS=new JLabel("second:");
		SpinnerNumberModel lblSModel = new SpinnerNumberModel(00, 00, 59, 01);
	
		spinnerS = new JSpinner(lblSModel);
		spinnerS.setPreferredSize(new Dimension(100, 20));
		
		
		panelCenter.add(lblH, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(spinnerH, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(lblM, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(spinnerM, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(lblS, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelCenter.add(spinnerS, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
}
	
	public void bottomPanel(){
		
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.setBackground(Color.white);
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK = new JButton(I18NLanguage.CONNECTION_BTNOK);
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(day.isSelected()){
					
				textFieldTime.setText(String.format("%02d", spinnerH.getValue())+":"+String.format("%02d", spinnerM.getValue())+":"+String.format("%02d", spinnerS.getValue()));
			}else if(week.isSelected()){
				
				textFieldTime.setText(String.format("%02d", spinnerH.getValue())+":"+String.format("%02d", spinnerM.getValue())+":"+String.format("%02d", spinnerS.getValue()));
			}else if(month.isSelected()){
				
				textFieldTime.setText(String.format("%02d", spinnerH.getValue())+":"+String.format("%02d", spinnerM.getValue())+":"+String.format("%02d", spinnerS.getValue()));
			}
				
					
				dispose();
				}
			
			});
				
	
				
			
	
		JButton btnCancel = new JButton(I18NLanguage.CONNECTION_BTNCANCEL);
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
}
