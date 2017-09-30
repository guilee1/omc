package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.ltln.modules.ni.omc.system.manager.SystemManager;
import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.Toolkit;

public class MonitorFrame {

	private SystemTray tray;
	private TrayIcon trayIcon;
	JTable tableOMC;
	JTable tableConnection = new JTable(new DefaultTableModel(new String[] {
			"No", "IP", "Type" }, 0));
	JScrollPane jscroPanOMC;
	DefaultTableModel tableModelOMC;
	SystemManager system = new SystemManager(tableConnection);
	JFrame frame;
	JLabel lblImg = new JLabel(new ImageIcon(Toolkit.getImgPath("stop.png")));
	JLabel lblNull = new JLabel("                 ");
	JLabel lblMySql = new JLabel();
	String imgname;
	boolean b;

	public static boolean updateFlag = true;

	// public static boolean languageFlag = false;
	public void drawOMC() {
		frame = new JFrame(I18NLanguage.MONITOR_TITLE);

		JPanel panelMain = new JPanel(new BorderLayout());

		JMenuBar menubar = new JMenuBar();

		JMenu menuSystem = new JMenu(I18NLanguage.MONITOR_SYSTEM);
		menuSystem.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JMenu menuHelp = new JMenu(I18NLanguage.MONITOR_HELP);
		menuHelp.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JMenu menuLanguage = new JMenu(I18NLanguage.MONITOR_LANGUAGE);
		menuLanguage.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JMenuItem itemConnectionCfg = new JMenuItem(
				I18NLanguage.MONITOR_CONNECTION_CONFIG);
		itemConnectionCfg.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN,
				12));
		itemConnectionCfg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ConnectionConfig connectCfg = new ConnectionConfig();
				connectCfg.setVisible(true);
			}
		});
		JMenuItem itemFTPCfg = new JMenuItem(I18NLanguage.MONITOR_FTP_CONFIG);
		itemFTPCfg.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemFTPCfg.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FTPConfig ftp = new FTPConfig();
				ftp.setVisible(true);
			}
		});
		JMenuItem itemDump = new JMenuItem(I18NLanguage.MONITOR_DUMP_CONFIG);
		itemDump.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemDump.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Dump dump = new Dump();
				dump.setVisible(true);
			}
		});
		JMenuItem itemDiagnose = new JMenuItem(I18NLanguage.MONITOR_DIAGNOSE);
		itemDiagnose.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemDiagnose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Diagnose diagnose = new Diagnose(system);
				diagnose.setVisible(true);
			}
		});
		// 加入Time Config
		JMenuItem itemTime = new JMenuItem(I18NLanguage.MONITOR_TIME_CONFIG);
		itemTime.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemTime.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TimeConfig timeconfig = new TimeConfig();
				timeconfig.setVisible(true);
			}
		});

		// 加入风暴测试The storm test
		JMenuItem itemStorm = new JMenuItem(I18NLanguage.STORM_TEST);
		itemStorm.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemStorm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StormTest stormTest = new StormTest();
				stormTest.setVisible(true);
			}
		});

		// omc user menu item
		JMenuItem omcUserMenu = new JMenuItem(I18NLanguage.OMC_USER);
		omcUserMenu.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		omcUserMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (MonitorFrame.updateFlag) {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.STORMTEST_INFO_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					OmcUserConfig stormTest = new OmcUserConfig();
					stormTest.setVisible(true);
				}
			}
		});

		// 加入资源采集配置
		JMenuItem itemResourceAcquisition = new JMenuItem(
				I18NLanguage.RESOURCE_ACQUISITION_CONFIGURATION);
		itemResourceAcquisition.setFont(new Font(I18NLanguage.FONT_NAME,
				Font.PLAIN, 12));
		itemResourceAcquisition.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScheduleDialog resourceAcq = new ScheduleDialog(frame);
				resourceAcq.setResizable(false);
				resourceAcq.setModal(true);
				resourceAcq.setLocation(300, 200);
				resourceAcq.setVisible(true);
			}
		});
		// 退出
		JMenuItem itemExit = new JMenuItem(I18NLanguage.MONITOR_EXIT);
		itemExit.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (updateFlag) {
					System.exit(1);
				} else {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.MONITOR_EXIT_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});
		JMenuItem itemAbout = new JMenuItem(I18NLanguage.MONITOR_ABOUT);
		itemAbout.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// About about = new About();
				// about.setVisible(true);
				if (MonitorFrame.updateFlag) {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.STORMTEST_INFO_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					LogDialog logDialog = new LogDialog(null);
					logDialog.setModal(true);
					logDialog.setVisible(true);
				}
			}
		});
		JMenuItem itemChinese = new JMenuItem(I18NLanguage.MONITOR_CHINESE);
		itemChinese.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemChinese.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Constants.setLanguage(0);
				Constants.save();
				I18NLanguage.init();
				frame.repaint();
			}
		});

		JMenuItem itemEnglish = new JMenuItem(I18NLanguage.MONITOR_ENGLISH);
		itemEnglish.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		itemEnglish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Constants.setLanguage(1);
				Constants.save();
				I18NLanguage.init();
				frame.repaint();
			}
		});

		menuSystem.add(itemConnectionCfg);
		menuSystem.add(itemFTPCfg);
		menuSystem.add(itemDump);
		menuSystem.add(itemDiagnose);
		menuSystem.add(itemTime);

		menuSystem.add(itemResourceAcquisition);
		menuSystem.add(itemExit);
		menuHelp.add(itemStorm);
		menuHelp.add(omcUserMenu);
		menuHelp.add(itemAbout);
		menuLanguage.add(itemChinese);
		menuLanguage.add(itemEnglish);
		menubar.add(menuSystem);
		menubar.add(menuHelp);
		menubar.add(menuLanguage);

		JPanel panelBig = new JPanel(new BorderLayout());
		JPanel panelSystemF = new JPanel(new BorderLayout());
		JPanel panelSystem = new JPanel(new GridBagLayout());
		JPanel panelNull = new JPanel();
		TitledBorder titlePanelSystem = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.MONITOR_TITLE_PANEL_SYSTEM);
		titlePanelSystem.setTitleFont(new Font(I18NLanguage.FONT_NAME,
				Font.PLAIN, 12));
		panelSystem.setBorder(titlePanelSystem);

		final JLabel lblStop = new JLabel(I18NLanguage.MONITOR_LBL_STOPSTA);
		lblStop.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		final JButton btnStart = new JButton(I18NLanguage.MONITOR_BTN_STARTSTA);
		btnStart.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (lblStop.getText().equals(I18NLanguage.MONITOR_LBL_STOPSTA)) {
					boolean startSucc = system.startUp();
					lblStop.setText(I18NLanguage.MONITOR_LBL_STOPSTO);
					lblImg.setIcon(new ImageIcon(Toolkit
							.getImgPath(startSucc ? "start.png" : "stop.png")));
					btnStart.setText(I18NLanguage.MONITOR_BTN_STARTSTO);
					tableModelOMC.setRowCount(0);

					for (String strItem : system.getResult()) {
						if (strItem.contains("successfully")) {
							tableModelOMC
									.addRow(new Object[] { "true", strItem });
						} else {
							tableModelOMC.addRow(new Object[] { "false",
									strItem });
						}
					}
					updateFlag = false;
					tableOMC.repaint();
				} else {
					boolean stopSucc = system.shutDown();
					lblStop.setText(I18NLanguage.MONITOR_LBL_STOPSTA);
					lblImg.setIcon(new ImageIcon(Toolkit
							.getImgPath(stopSucc ? "stop.png" : "start.png")));
					btnStart.setText(I18NLanguage.MONITOR_BTN_STARTSTA);

					tableModelOMC.setRowCount(0);
					for (int i = system.getResult().size() - 1; i >= 0; i--) {
						String strItem = system.getResult().get(i);
						if (strItem.contains("successfully")) {
							tableModelOMC.addRow(new Object[] { "false",
									strItem });
						} else {
							tableModelOMC
									.addRow(new Object[] { "true", strItem });
						}
					}
					updateFlag = true;
					tableOMC.repaint();
				}
			}
		});

		panelSystem.add(lblStop, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelSystem.add(lblImg, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelSystem.add(lblNull, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		panelSystem.add(btnStart, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelSystemF.add(panelNull, BorderLayout.NORTH);
		panelSystemF.add(panelSystem, BorderLayout.CENTER);
		panelBig.add(panelSystemF, BorderLayout.NORTH);

		JPanel panelConnection = new JPanel();
		panelConnection.setLayout(new BoxLayout(panelConnection,
				BoxLayout.Y_AXIS));
		JPanel panelOMC = new JPanel(new BorderLayout());
		TitledBorder titlePanelOMC = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.MONITOR_TITLE_PANEL_OMC);
		titlePanelOMC.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN,
				12));
		panelOMC.setBorder(titlePanelOMC);

		String[] header = { I18NLanguage.MONITOR_HEADERONE,
				I18NLanguage.MONITOR_HEADERTWO };
		tableModelOMC = new DefaultTableModel(header, 0);

		tableOMC = new JTable();

		class MyRenderer implements TableCellRenderer {
			public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) {
				JLabel editor = new JLabel();
				if (value.equals("true")) {
					editor.setIcon(new ImageIcon(Toolkit
							.getImgPath("start.png")));
				} else
					editor.setIcon(new ImageIcon(Toolkit.getImgPath("stop.png")));
				return editor;
			}
		}

		// setDefaultRenderer(Object.class,new MyRenderer());
		tableOMC.setEnabled(false);
		tableOMC.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tableOMC.getTableHeader().setResizingAllowed(false); // 不可拉动表格
		tableOMC.setModel(tableModelOMC);
		tableOMC.getColumnModel().getColumn(0).setPreferredWidth(50);
		// 设置行高
		// tableOMC.setRowHeight(100);;
		// 给表格的某一列设置渲染器
		tableOMC.getColumnModel().getColumn(0)
				.setCellRenderer(new MyRenderer());

		tableOMC.getColumnModel().getColumn(0).setResizable(false);
		tableOMC.getColumnModel().getColumn(1).setPreferredWidth(415);
		tableOMC.getColumnModel().getColumn(1).setResizable(false);
		// tableOMC.setShowGrid(false);
		tableOMC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		// table.setPreferredScrollableViewportSize(new Dimension(100,100));
		jscroPanOMC = new JScrollPane();
		jscroPanOMC
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscroPanOMC
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscroPanOMC.getViewport().add(tableOMC);
		panelOMC.add(jscroPanOMC, BorderLayout.CENTER);

		TitledBorder titlePanelConnection = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.MONITOR_TITLE_PANEL_CONNECTION);
		titlePanelConnection.setTitleFont(new Font(I18NLanguage.FONT_NAME,
				Font.PLAIN, 12));
		JPanel panelNMS = new JPanel(new BorderLayout());
		TitledBorder titlePanelNMS = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.MONITOR_TITLE_PANEL_NMS);
		titlePanelNMS.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN,
				12));
		panelNMS.setBorder(titlePanelNMS);
		panelConnection.setBorder(titlePanelConnection);

		JScrollPane jscroPan = new JScrollPane();
		jscroPan.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		// 设置列的宽度
		int columncount = tableConnection.getColumnCount();

		for (int i = 1; i < columncount; i++) {

			tableConnection.getColumnModel().getColumn(i).setPreferredWidth(20);

		}

		tableConnection.setShowGrid(false);

		jscroPan.getViewport().add(tableConnection);
		panelNMS.add(jscroPan);
		panelConnection.add(panelOMC);
		panelConnection.add(panelNMS);

		panelBig.add(panelConnection, BorderLayout.CENTER);
		panelMain.add(panelBig, BorderLayout.CENTER);

		panelMain.add(menubar, BorderLayout.NORTH);
		frame.add(panelMain);

		java.awt.Toolkit tool=frame.getToolkit();
		frame.setIconImage(tool.getImage(Toolkit.getImgPath("logo.png")));

		frame.setSize(500, 550);
		frame.setResizable(false);
		frame.setLocation(500, 200);
		frame.setVisible(true);

		// 设置窗口关闭事件监听
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				if (!updateFlag) {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.MONITOR_EXIT_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);
					// // 将托盘图标添加到系统的托盘实例中
					try {
						tray.add(trayIcon);
						frame.setVisible(false);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				} else {
					System.exit(1);
				}
			}
		});
		frame.addWindowStateListener(new WindowStateListener() {
			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				if (e.getNewState() == JFrame.ICONIFIED) {
					try {
						tray.add(trayIcon);
						frame.setVisible(false);
					} catch (AWTException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		if (SystemTray.isSupported()) {
			tray();
		}
	}

	private void tray() {
		// 获得本操作系统托盘的实例
		tray = SystemTray.getSystemTray();
		// 显示在托盘中的图标
		ImageIcon icon = new ImageIcon(Toolkit.getImgPath("logo.png"));
		trayIcon = new TrayIcon(icon.getImage(), "OMC-NI");
		// 这句很重要，没有会导致图片显示不出来
		trayIcon.setImageAutoSize(true);
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					tray.remove(trayIcon);
					frame.setVisible(true);
					// 还原成原来的窗口，而不是显示在任务栏
					frame.setExtendedState(Frame.NORMAL);
				}
			}
		});
	}

}
