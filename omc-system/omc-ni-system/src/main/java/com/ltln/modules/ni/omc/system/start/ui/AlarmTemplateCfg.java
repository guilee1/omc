package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ltln.modules.ni.omc.system.util.Constants;

public class AlarmTemplateCfg extends JDialog{
	
	
	Properties prop;
	JTextField jtfAlarmTitle;
	JTextField jtfAlarmType;
	JComboBox jcb;
	JTextField jtfSpecificProblemID;
	JTextField jtfSpecificProblem;
	JTextField jtfNeUID;
	JTextField jtfNeName;
	JTextField jtfNeType;
	JTextField jtfObjectUID;
	JTextField jtfObjectName;
	JTextField jtfObjectType;
	JTextField jtfLocationInfo;
	JTextField jtfAddInfo;
	JTextField jtfHolderType;
	JTextField jtfAlarmCheck;
	JTextField jtfLayer;
	JTextField jtfIpAddress;
	
	JPanel panelTop;
	JPanel panelC;
	JPanel panelB;
	JPanel panelAlarmTC;
	JPanel obj;
	public AlarmTemplateCfg(Properties prop2){
		this.prop = prop2;
		drawAlarmTC();
		this.add(panelAlarmTC);
		this.setModal(true);
		this.setTitle(I18NLanguage.ALARM_TEMPLATE_CONFIGURE);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(450, 650);
		this.setResizable(false);
		this.setLocation(300, 30);
		
	}
	
	public void drawAlarmTC(){
		panelAlarmTC=new JPanel(new BorderLayout());
		drawTop();
		panelAlarmTC.add(panelTop,BorderLayout.NORTH);
		drawCenter();
		panelAlarmTC.add(panelC,BorderLayout.CENTER);
		drawBottom();
		panelAlarmTC.add(panelB,BorderLayout.SOUTH);
		
	}
	public void drawTop(){
		panelTop=new JPanel();
	}
	public void drawCenter(){
		panelC=new JPanel(new BorderLayout());
		TitledBorder titleC= new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)), I18NLanguage.ALARM_OBJECT);
		titleC.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelC.setBorder(titleC);
		JScrollPane jsp=new JScrollPane();
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelObject();
		jsp.getViewport().add(obj);
		panelC.add(jsp,BorderLayout.CENTER);
	}
	public void drawBottom(){
		panelB=new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		
		JButton btnOK=new JButton(I18NLanguage.ABOUT_OK);
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String layerRate = jtfLayer.getText();
				try{
					Integer.parseInt(layerRate);
				}catch (NumberFormatException err) {
					JOptionPane.showMessageDialog(null, I18NLanguage.LAYERRATE_MUST_INTEGER, "failed",JOptionPane.WARNING_MESSAGE);
					return;
				}
				prop.setProperty("alarmTitle",jtfAlarmTitle.getText());
				prop.setProperty("alarmType",jtfAlarmType.getText());
				prop.setProperty("origSeverity",String.valueOf(jcb.getSelectedIndex()));
				prop.setProperty("specificProblemID",jtfSpecificProblemID.getText());
				prop.setProperty("specificProblem",jtfSpecificProblem.getText());
				prop.setProperty("neUID",jtfNeUID.getText());
				prop.setProperty("neName",jtfNeName.getText());
				prop.setProperty("neType",jtfNeType.getText());
				prop.setProperty("objectUID",jtfObjectUID.getText());
				prop.setProperty("objectName",jtfObjectName.getText());
				prop.setProperty("objectType",jtfObjectType.getText());
				prop.setProperty("locationInfo",jtfLocationInfo.getText());
				prop.setProperty("addInfo",jtfAddInfo.getText());
				prop.setProperty("holderType",jtfHolderType.getText());
				prop.setProperty("alarmCheck",jtfAlarmCheck.getText());
				prop.setProperty("layer",jtfLayer.getText());
				prop.setProperty("ipAddress",jtfIpAddress.getText());
				save();
				JOptionPane.showMessageDialog(null, I18NLanguage.CONNECTION_SUCCESS_POPUPWINDOW, "success",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JButton btnDel=new JButton(I18NLanguage.CANCEL);
		btnDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		btnDel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelC.add(btnOK,BorderLayout.WEST);
		panelC.add(btnDel,BorderLayout.EAST);
		panelB.add(panelC, BorderLayout.CENTER);
		panelB.add(panelN, BorderLayout.NORTH);
		panelB.add(panelS, BorderLayout.SOUTH);
		panelB.add(panelW, BorderLayout.WEST);
		panelB.add(panelE, BorderLayout.EAST);
	}
	public void panelObject(){
		
		obj=new JPanel(new GridBagLayout());
		JLabel lblAlarmTitle=new JLabel("alarmTitle:");
		lblAlarmTitle.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		jtfAlarmTitle=new JTextField(25);
		jtfAlarmTitle.setText(prop.getProperty("alarmTitle"));
		obj.add(lblAlarmTitle, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfAlarmTitle, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		JLabel lblAlarmType=new JLabel("alarmType:");
		lblAlarmType.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		jtfAlarmType=new JTextField(25);
		jtfAlarmType.setText(prop.getProperty("alarmType"));
		obj.add(lblAlarmType, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfAlarmType, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		JLabel lblOrigSevty=new JLabel("origSeverity:");
		lblOrigSevty.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		String[] alarm={"Critical","Major","Minor","Warning"};
		jcb=new JComboBox(alarm);
		jcb.setSelectedIndex(Integer.parseInt(prop.getProperty("origSeverity")));
		jcb.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		obj.add(lblOrigSevty, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jcb, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		JLabel lblSpecificProblemID=new JLabel("specificProblemID:");
		lblSpecificProblemID.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		jtfSpecificProblemID=new JTextField(25);
		jtfSpecificProblemID.setText(prop.getProperty("specificProblemID"));
		obj.add(lblSpecificProblemID, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfSpecificProblemID, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		JLabel lblSpecificProblem=new JLabel("specificProblem:");
		
		jtfSpecificProblem=new JTextField(25);
		jtfSpecificProblem.setText(prop.getProperty("specificProblem"));
		obj.add(lblSpecificProblem, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfSpecificProblem, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblSpecificProblem.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblNeUID=new JLabel("neUID:");
		
		jtfNeUID=new JTextField(25);
		jtfNeUID.setText(prop.getProperty("neUID"));
		obj.add(lblNeUID, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfNeUID, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblNeUID.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblNeName=new JLabel("neName:");
		
		jtfNeName=new JTextField(25);
		jtfNeName.setText(prop.getProperty("neName"));
		obj.add(lblNeName, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfNeName, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblNeName.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblNeType=new JLabel("neType:");
		
		jtfNeType=new JTextField(25);
		jtfNeType.setText(prop.getProperty("neType"));
		obj.add(lblNeType, new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfNeType, new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblNeType.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblObjectUID=new JLabel("objectUID:");
		
		jtfObjectUID=new JTextField(25);
		jtfObjectUID.setText(prop.getProperty("objectUID"));
		obj.add(lblObjectUID, new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfObjectUID, new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblObjectUID.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblObjectName=new JLabel("objectName:");
		
		jtfObjectName=new JTextField(25);
		jtfObjectName.setText(prop.getProperty("objectName"));
		obj.add(lblObjectName, new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfObjectName, new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblObjectName.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblObjectType=new JLabel("objectType:");
		
		jtfObjectType=new JTextField(25);
		jtfObjectType.setText(prop.getProperty("objectType"));
		obj.add(lblObjectType, new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfObjectType, new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblObjectType.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblLocationInfo=new JLabel("locationInfo:");
		
		jtfLocationInfo=new JTextField(25);
		jtfLocationInfo.setText(prop.getProperty("locationInfo"));
		obj.add(lblLocationInfo, new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfLocationInfo, new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblLocationInfo.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblAddInfo=new JLabel("addInfo:");
		
		jtfAddInfo=new JTextField(25);
		jtfAddInfo.setText(prop.getProperty("addInfo"));
		obj.add(lblAddInfo, new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfAddInfo, new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblAddInfo.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblHolderType=new JLabel("holderType:");
		
		jtfHolderType=new JTextField(25);
		jtfHolderType.setText(prop.getProperty("holderType"));
		obj.add(lblHolderType, new GridBagConstraints(0, 14, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfHolderType, new GridBagConstraints(1, 14, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblHolderType.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblAlarmCheck=new JLabel("alarmCheck:");
		
		jtfAlarmCheck=new JTextField(25);
		jtfAlarmCheck.setText(prop.getProperty("alarmCheck"));
		obj.add(lblAlarmCheck, new GridBagConstraints(0, 15, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfAlarmCheck, new GridBagConstraints(1, 15, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblAlarmCheck.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblLayer=new JLabel("layer:");
		
		jtfLayer=new JTextField(25);
		jtfLayer.setText(prop.getProperty("layer"));
		obj.add(lblLayer, new GridBagConstraints(0, 16, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfLayer, new GridBagConstraints(1, 16, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblLayer.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
		JLabel lblIpAddress=new JLabel("ipAddress:");
		
		jtfIpAddress=new JTextField(25);
		jtfIpAddress.setText(prop.getProperty("ipAddress"));
		obj.add(lblIpAddress, new GridBagConstraints(0, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		
		obj.add(jtfIpAddress, new GridBagConstraints(1, 17, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		lblIpAddress.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		
	}

	public void save() {
		FileOutputStream fos = null;
		try{
			fos = new FileOutputStream(Constants.rootDir +"envconf/alarmTemplateCfg.properties");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			prop.store(bos, "");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			try {
				if(fos!=null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
		
