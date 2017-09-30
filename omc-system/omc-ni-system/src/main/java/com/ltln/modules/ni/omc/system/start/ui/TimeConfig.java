package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ltln.modules.ni.omc.core.IMgrInf;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.util.OmcDateFormater;

public class TimeConfig extends JDialog {
	JPanel firstPanel;
	JPanel secondPanel;
	TextField textOmcTime;
	private Clock clock = new Clock();
	private OmcClock oclock = new OmcClock();

	public static boolean flagClock = true;

	public TimeConfig() {
		this.setModal(true);
		this.setTitle(I18NLanguage.TIMECONFIG_TITLETOP);
		JPanel mainPanel = new JPanel(new BorderLayout());
		firstPanel();
		mainPanel.add(firstPanel, BorderLayout.CENTER);
		secondPanel();
		mainPanel.add(secondPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(350, 250);
		this.setResizable(false);
		this.setLocation(300, 200);
		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				flagClock = false;
//			}
			@Override
			public void windowClosed(WindowEvent e) {
				super.windowClosed(e);
				flagClock = false;
			}
		});
	}

	public void firstPanel() {
		firstPanel = new JPanel(new BorderLayout());
		JPanel panel = new JPanel(new GridBagLayout());
		TimeConfig.flagClock = true;
		clock.start();
		oclock.start();
		TitledBorder titleTop = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.TIMECONFIG_TITLETOP);
		titleTop.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panel.setBorder(titleTop);
		JLabel omcTime = new JLabel(I18NLanguage.TIMECONFIG_OMCTIME);
		omcTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		JLabel systemTime = new JLabel(I18NLanguage.TIMECONFIG_SYSTEMTIME);
		systemTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		panel.add(omcTime, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		panel.add(oclock, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panel.add(systemTime, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panel.add(clock, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		firstPanel.add(panel, BorderLayout.CENTER);
	}

	public void secondPanel() {

		secondPanel = new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnTime = new JButton(I18NLanguage.TIMECONFIG_BTNTIME);
		btnTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnTime.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ILocator locator = SelfBeanFactoryAware
						.getBean("serviceLocator");
				long seconds = locator.getInstances(IMgrInf.class).getNtpTime();
				String[] dateStr = OmcDateFormater.formatBasicDate(seconds).split(" ");
				try {
					Runtime.getRuntime().exec("cmd /c date " + dateStr[0]);
					Runtime.getRuntime().exec("cmd /c time " + dateStr[1]);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		JButton btnCancel = new JButton(I18NLanguage.TIMECONFIG_BTNCANCEL);
		btnCancel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panelC.add(btnTime, BorderLayout.WEST);
		panelC.add(btnCancel, BorderLayout.EAST);

		secondPanel.add(panelC, BorderLayout.CENTER);
		secondPanel.add(panelN, BorderLayout.NORTH);
		secondPanel.add(panelS, BorderLayout.SOUTH);
		secondPanel.add(panelW, BorderLayout.WEST);
		secondPanel.add(panelE, BorderLayout.EAST);

	}
	
	
}

class Clock extends Label implements Runnable {
	public Thread clocker = null;

	public Clock() {
		// 初始化时，把label设置为当前系统时间
		setText(new Date().toString());
	}

	public void start() {
		if (clocker == null) {
			clocker = new Thread(this);
			clocker.start();
		}
	}

	public void run() {
		// 判断clocker是否是当前运行的线程
		while (TimeConfig.flagClock) {
			Date date = new Date();
			setText(OmcDateFormater.formatBasicDate(date));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println("Thread error:" + ie);
			}
		}
	}
}

class OmcClock extends Label implements Runnable {
	public Thread clocker = null;
	ILocator locator = SelfBeanFactoryAware.getBean("serviceLocator");
	public void start() {
		if (clocker == null) {
			clocker = new Thread(this);
			clocker.start();
		}
	}

	public void run() {
		while (TimeConfig.flagClock) {
			try {
				long seconds = locator.getInstances(IMgrInf.class).getNtpTime();
				setText(OmcDateFormater.formatBasicDate(seconds));
				Thread.sleep(1000);
			} catch (Exception ie) {
				setText("--- --- ---");
			}
		}
	}

}