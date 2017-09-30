package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ltln.modules.ni.omc.system.util.Constants;

public class ConnectionConfig extends JDialog {
	private static final long serialVersionUID = 8171212302615046898L;
	JPanel topPanel;
	JPanel middlePanel;
	JPanel bottomPanel;
	JSpinner Aspinner;
	JSpinner Ispinner;
	TextField textfieldIP;
	TextField textfieldPort;
	String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	// String
	// portCheck=(/^([0-9]|[1-9]\d|[1-9]\d{2}|[1-9]\d{3}|[1-5]\d{4}|6[0-4]\d{3}|65[0-4]\d{2}|655[0-2]\d|6553[0-5])$/));
	String str = "[0-9]+";

	SpinnerModel AspinnerModel;
	SpinnerModel IspinnerModel;
	InputStream in;

	public ConnectionConfig() {
		this.setModal(true);
		this.setTitle(I18NLanguage.CONNECTION_TITLE);
		JPanel mainPanel = new JPanel(new BorderLayout());
		topPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		middlePanel();
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		bottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(350, 250);
		this.setResizable(false);
		this.setLocation(300, 200);

	}

	public void topPanel() {

		topPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new GridBagLayout());
		JPanel nullPanel = new JPanel();
		TitledBorder titleTop = new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)), I18NLanguage.CONNECTION_OMC_TITLETOP);
		titleTop.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panel.setBorder(titleTop);
		JLabel lblIP = new JLabel(I18NLanguage.CONNECTION_LBLIP);
		lblIP.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldIP = new TextField(20);


		textfieldIP.setText(Constants.RMI_IP_ADDR);

		JLabel lblPort = new JLabel(I18NLanguage.CONNECTION_LBLPORT);
		lblPort.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldPort = new TextField(20);
		textfieldPort.setText(Constants.RMI_PORT);
		panel.add(lblIP, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(textfieldIP, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		panel.add(lblPort, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panel.add(textfieldPort, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		topPanel.add(panel, BorderLayout.CENTER);
		topPanel.add(nullPanel, BorderLayout.NORTH);
	}

	public void middlePanel() {

		middlePanel = new JPanel(new GridBagLayout());
		TitledBorder titleTop = new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)), I18NLanguage.CONNECTION_PORT_TITLETOP);
		titleTop.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		middlePanel.setBorder(titleTop);
		JLabel lblAlarmPort = new JLabel(I18NLanguage.CONNECTION_LBLALARMPORT);
		lblAlarmPort.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		if (Constants.ALARM_PORT == 0) {
			AspinnerModel = new SpinnerNumberModel(31236, 31232, 31241, 1);
		} else {
			AspinnerModel = new SpinnerNumberModel(Constants.ALARM_PORT, 31232, 31241, 1);
		}
		Aspinner = new JSpinner(AspinnerModel);

		JLabel lblNull = new JLabel("                          ");
		JLabel lblInstructionPort = new JLabel(I18NLanguage.CONNECTION_LBLINSTRUCTIONPORT);
		lblInstructionPort.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		if (Constants.INSTRUCTION_PORT == 0) {
			IspinnerModel = new SpinnerNumberModel(31237, 31232, 31241, 1);
		} else {
			IspinnerModel = new SpinnerNumberModel(Constants.INSTRUCTION_PORT, 31232, 31241,
					1);
		}
		Ispinner = new JSpinner(IspinnerModel);
		middlePanel.add(lblAlarmPort, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

		middlePanel.add(Aspinner, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblNull, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(lblInstructionPort, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		middlePanel.add(Ispinner, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}

	public void bottomPanel() {
		bottomPanel = new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK = new JButton(I18NLanguage.CONNECTION_BTNOK);
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnOK.setBounds(2, 3, 200, 8);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 if(MonitorFrame.updateFlag){
				
				if (Aspinner.getValue().equals(Ispinner.getValue())) {
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(middlePanel, I18NLanguage.CONNECTION_PORT_POPUPWINDOW, "error",
							JOptionPane.ERROR_MESSAGE);
						return;
				}
				
				if (!textfieldIP.getText().matches(rexp) && !textfieldIP.getText().equals("localhost")) {
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(middlePanel, I18NLanguage.CONNECTION_IP_LEGAL, "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(textfieldPort.getText().isEmpty()){
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(middlePanel, I18NLanguage.CONNECTION_PORT_ENTER, "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(textfieldPort.getText().equals(new FTPConfig().getAspinnerModel().getValue().toString())){
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(middlePanel, I18NLanguage.CONNECTION_PORT_SAME, "error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(textfieldPort.getText().matches(str)){
//					if(Double.parseDouble(textfieldPort.getText()) > (Math.pow(2, 16)-1)){
//						JOptionPane joptionp = new JOptionPane();
//						joptionp.showMessageDialog(middlePanel, "Port must be a 0-65535 integer, please enter again", "error", JOptionPane.ERROR_MESSAGE);
//						return;
//					}
				    if(Integer.parseInt(textfieldPort.getText()) < 0 || Integer.parseInt(textfieldPort.getText()) > (Math.pow(2, 16)-1)){
					   JOptionPane joptionp = new JOptionPane();
					   joptionp.showMessageDialog(middlePanel, I18NLanguage.CONNECTION_PORT_RANGE, "error", JOptionPane.ERROR_MESSAGE);
					
				    }else {
				    	if(textfieldIP.getText().equals("localhost")){
						InetAddress ia = null;
						try {
							ia = ia.getLocalHost();
							String localip = ia.getHostAddress();
							Constants.setRemoteIp(localip);
						} catch (UnknownHostException e1) {
							e1.printStackTrace();
						}
					}else{
						String localip = textfieldIP.getText();
						Constants.setRemoteIp(localip);
						
					}
					
					Constants.setRemotePort(textfieldPort.getText());
					Constants.setAlarmPort(Aspinner.getValue().toString());
					Constants.setInstructionPort(Ispinner.getValue().toString());
					Constants.save();
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(null, I18NLanguage.CONNECTION_SUCCESS_POPUPWINDOW, "success",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				}else{
					JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(middlePanel, I18NLanguage.CONNECTION_PORT_RANGE, "error", JOptionPane.ERROR_MESSAGE);
				}
			 }else {
			        JOptionPane joptionp = new JOptionPane();
					joptionp.showMessageDialog(null, I18NLanguage.CONNECTION_MODIFY_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);

					dispose();
		}
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