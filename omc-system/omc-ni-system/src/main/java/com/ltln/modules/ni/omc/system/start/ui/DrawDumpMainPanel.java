package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DrawDumpMainPanel {
	JPanel panelDump;

	JTextField textField;

	TimeSelect t;

	JTextField textFieldTime;
	ButtonGroup group;
	ButtonGroup groupSwitch;//开关
	JRadioButton open;
	JRadioButton close;
	JRadioButton day;
	JRadioButton week;
	JRadioButton month;

	public ButtonGroup getGroupSwitch() {
		return groupSwitch;
	}

	public void setGroupSwitch(ButtonGroup groupSwitch) {
		this.groupSwitch = groupSwitch;
	}

	public JRadioButton getOpen() {
		return open;
	}

	public void setOpen(JRadioButton open) {
		this.open = open;
	}

	public JRadioButton getClose() {
		return close;
	}

	public void setClose(JRadioButton close) {
		this.close = close;
	}

	public JPanel getPanelDump() {
		return panelDump;
	}

	public void setPanelDump(JPanel panelDump) {
		this.panelDump = panelDump;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	public TimeSelect getT() {
		return t;
	}

	public void setT(TimeSelect t) {
		this.t = t;
	}

	public JTextField getTextFieldTime() {
		return textFieldTime;
	}

	public void setTextFieldTime(JTextField textFieldTime) {
		this.textFieldTime = textFieldTime;
	}

	public ButtonGroup getGroup() {
		return group;
	}

	public void setGroup(ButtonGroup group) {
		this.group = group;
	}

	public JRadioButton getDay() {
		return day;
	}

	public void setDay(JRadioButton day) {
		this.day = day;
	}

	public JRadioButton getWeek() {
		return week;
	}

	public void setWeek(JRadioButton week) {
		this.week = week;
	}

	public JRadioButton getMonth() {
		return month;
	}

	public void setMonth(JRadioButton month) {
		this.month = month;
	}

	public void DlgCreatetimeSelect() {
		t = new TimeSelect(this);
		t.setVisible(true);
	}

	public void drawDump() {

		panelDump = new JPanel(new GridBagLayout());
		TitledBorder titlePanelDump = new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)), I18NLanguage.DRAWDUMP_TITLE);
		titlePanelDump.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelDump.setBorder(titlePanelDump);
		JLabel lblDumpDirectory = new JLabel(I18NLanguage.DRAWDUMP_LBLDUMPDIRECTORY);

		lblDumpDirectory.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textField = new JTextField(20);

		textField.setEditable(false);
		JButton btn = new JButton("...");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fd = new JFileChooser();
				fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (fd.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fd.getSelectedFile();
					String path = f.getPath();
					textField.setText(path);
				}

			}
		});
		btn.setPreferredSize(new Dimension(40, 20));
		
		//新增开关
		open=new JRadioButton(I18NLanguage.DRAWDUMP_OPEN);
		open.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		close=new JRadioButton(I18NLanguage.DRAWDUMP_CLOSE,true);
		close.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		open.setActionCommand("open");
		close.setActionCommand("close");
		groupSwitch=new ButtonGroup();
		groupSwitch.add(open);
		groupSwitch.add(close);
		
		
		day = new JRadioButton(I18NLanguage.DRAWDUMP_DAY);
		day.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		week = new JRadioButton(I18NLanguage.DRAWDUMP_WEEK);
		week.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		month = new JRadioButton(I18NLanguage.DRAWDUMP_MONTH, true);
		month.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		group = new ButtonGroup();
		day.setActionCommand("everyday");
		week.setActionCommand("everyweek");
		month.setActionCommand("everymonth");
		group.add(day);
		group.add(week);
		group.add(month);

		JLabel lblTime = new JLabel(I18NLanguage.DRAWDUMP_LBLTIME);
		lblTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textFieldTime = new JTextField(I18NLanguage.DRAWDUMP_TEXTFIELD_TIME);

		textFieldTime.setEnabled(false);
		// 设置字体颜色
		// textFieldTime.setForeground(Color.BLACK);
		// 提示信息
		textFieldTime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				DlgCreatetimeSelect();

			}
		});
		panelDump.add(open, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(close, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(lblDumpDirectory, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(textField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(btn, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(day, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(week, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(month, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(lblTime, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelDump.add(textFieldTime, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

	}

}
