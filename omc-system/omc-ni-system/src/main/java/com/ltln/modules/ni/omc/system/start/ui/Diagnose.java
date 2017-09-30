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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ltln.modules.ni.omc.system.manager.SystemManager;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class Diagnose extends JDialog {
	private static final long serialVersionUID = -987746937530480150L;
	JPanel topPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	JLabel lblImg1;
	JLabel lblImg2;
	JLabel lblImg3;
	JLabel lblImg4;
	JLabel lblImg5;
	JLabel lblImg6;
	String imgname="stop.png";
	SystemManager sysManager;
	
	public Diagnose(SystemManager m) {

		this.setModal(true);
		this.setTitle(I18NLanguage.DIAGNOSE_TITLETOP);
		JPanel mainPanel = new JPanel(new BorderLayout());
		topPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		middlePanel();
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		bottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.sysManager = m;
		this.add(mainPanel);

		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void topPanel() {
		topPanel = new JPanel();
	}

	public void middlePanel() {
		middlePanel = new JPanel(new GridBagLayout());

		TitledBorder titleTop = new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)), I18NLanguage.DIAGNOSE_TITLETOP);
		titleTop.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		middlePanel.setBorder(titleTop);
		JLabel lblDataConnec = new JLabel(I18NLanguage.DIAGNOSE_LBLDATACONNEC);
		lblDataConnec.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JLabel lblOMC_RMT = new JLabel(I18NLanguage.DIAGNOSE_LBLOMC_RMT);
		lblOMC_RMT.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JLabel lblOMC_JMS = new JLabel(I18NLanguage.DIAGNOSE_LBLOMC_JMS);
		lblOMC_JMS.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JLabel lblAlarmPort = new JLabel(I18NLanguage.DIAGNOSE_LBLALARMPORT);
		lblAlarmPort.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JLabel lblInstructionPort = new JLabel(I18NLanguage.DIAGNOSE_LBLINSTRUCTIONPORT);
		lblInstructionPort.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JLabel lblFTP = new JLabel(I18NLanguage.DIAGNOSE_FTP);
		lblFTP.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		
		lblImg1 = new JLabel(new ImageIcon(Toolkit.getImgPath(imgname)));

		lblImg2 = new JLabel(new ImageIcon(Toolkit.getImgPath(imgname)));

		lblImg3 = new JLabel(new ImageIcon(Toolkit.getImgPath(imgname)));

		lblImg4 = new JLabel(new ImageIcon(Toolkit.getImgPath(imgname)));
		
		lblImg5 = new JLabel(new ImageIcon(Toolkit.getImgPath(imgname)));

		lblImg6 = new JLabel(new ImageIcon(Toolkit.getImgPath(imgname)));

		middlePanel.add(lblDataConnec, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblImg1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblOMC_RMT, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblImg2, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblOMC_JMS, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblImg3, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblAlarmPort, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblImg4, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblInstructionPort, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblImg5, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblFTP, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblImg6, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}

	public void check(){
		if(this.sysManager.DbDetect()){
			imgname="start.png";
			lblImg1.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}else{
			imgname="stop.png";
			lblImg1.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}
		if(this.sysManager.RmiDetect()){
			imgname="start.png";
			lblImg2.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}else{
			imgname="stop.png";
			lblImg2.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}
		
		if(this.sysManager.JmsDetect()){
			imgname="start.png";
			lblImg3.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}else{
			imgname="stop.png";
			lblImg3.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}
		
		if(this.sysManager.AlmServerDetect()){
			imgname="start.png";
			lblImg4.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}else{
			imgname="stop.png";
			lblImg4.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}
		
		if(this.sysManager.InstructionServerDetect()){
			imgname="start.png";
			lblImg5.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}else{
			imgname="stop.png";
			lblImg5.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}
		
		if(this.sysManager.FtpDetect()){
			imgname="start.png";
			lblImg6.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}else{
			imgname="stop.png";
			lblImg6.setIcon(new ImageIcon(Toolkit.getImgPath(imgname)));
		}
		
	}
	
	public void bottomPanel() {
		bottomPanel = new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK = new JButton(I18NLanguage.DIAGNOSE_BTNOK);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnOK.setPreferredSize(new Dimension(90, 30));
		JButton btnCancel = new JButton(I18NLanguage.DIAGNOSE_BTNCANCEL);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		btnCancel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnCancel.setPreferredSize(new Dimension(90, 30));
		panelC.add(btnOK, BorderLayout.WEST);

		panelC.add(btnCancel, BorderLayout.EAST);
		bottomPanel.add(panelC, BorderLayout.CENTER);
		bottomPanel.add(panelN, BorderLayout.NORTH);
		bottomPanel.add(panelS, BorderLayout.SOUTH);
		bottomPanel.add(panelW, BorderLayout.WEST);
		bottomPanel.add(panelE, BorderLayout.EAST);
	}

}
