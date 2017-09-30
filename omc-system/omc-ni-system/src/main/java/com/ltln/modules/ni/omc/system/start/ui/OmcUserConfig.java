package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.dao.OmcUser;

public class OmcUserConfig extends JDialog {

	private static final long serialVersionUID = -3225956498351988084L;
	JPanel middlePanel;
	JPanel bottomPanel;
	JTextField textfieldAccount;
	public DefaultTableModel tableModelOMC;
	JTable tableOMC;
	InputStream in;
	OutputStream oFile;

	SpinnerModel AspinnerModel;
	Vector<Object> data;

	// 启动mysql才可以查询
	IOmcDao dao = SelfBeanFactoryAware.getDao();

	public SpinnerModel getAspinnerModel() {
		return AspinnerModel;
	}

	public void setAspinnerModel(SpinnerModel aspinnerModel) {
		AspinnerModel = aspinnerModel;
	}

	public OmcUserConfig() {
		
		this.setModal(true);
		this.setTitle(I18NLanguage.OMC_USER);
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		middlePanel();
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		bottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(mainPanel);

		this.setSize(400, 300);
		this.setResizable(false);
		this.setLocation(300, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
	}

	public void middlePanel() {

		middlePanel = new JPanel(new BorderLayout());
		JPanel panelmid = new JPanel(new FlowLayout());
		TitledBorder titleTop = new TitledBorder(new EtchedBorder(
				EtchedBorder.LOWERED, Color.white, new Color(142, 142, 142)),
				I18NLanguage.FTPCONFIG_TITLETOP);
		titleTop.setTitleFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		middlePanel.setBorder(titleTop);

		
		JPanel panelCfgMidd = new JPanel(new BorderLayout());
		// 创建画表格的jpanel
		JPanel panelTable = new JPanel(new BorderLayout());

		String[] tableHeader = { I18NLanguage.FTPUSER_LBLACCOUNT,
				I18NLanguage.FTPUSER_LBLPASSW };

		tableModelOMC = new DefaultTableModel(tableHeader, 0);
		tableOMC = new JTable(tableModelOMC);

		tableOMC.getTableHeader().setReorderingAllowed(false); // 不可整列移动
		tableOMC.getColumnModel().getColumn(0).setPreferredWidth(100);

		tableOMC.getColumnModel().getColumn(0).setResizable(false);
		tableOMC.getColumnModel().getColumn(1).setPreferredWidth(200);
		tableOMC.getColumnModel().getColumn(1).setResizable(false);
		tableOMC.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		if (dao != null) {
			List<OmcUser> allUsers = dao.getAllUser();
			for (OmcUser ftpUser : allUsers) {
				tableModelOMC.addRow(new Object[] { ftpUser.getUserName(),
						ftpUser.getPwd() });
			}
		}
		tableOMC.setShowGrid(false);

		JScrollPane jscroPanOMC = new JScrollPane();
		jscroPanOMC
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscroPanOMC
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscroPanOMC.getViewport().add(tableOMC);
		// 创建按钮的jpanel
		JPanel panelBtn = new JPanel(new GridBagLayout());
		JButton btnAdd = new JButton(I18NLanguage.FTPCONFIG_BTNADD);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!MonitorFrame.updateFlag) {
					DlgCreateftpUser();
				} else {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.STORMTEST_INFO_POPUPWINDOW, "Failed",
							JOptionPane.INFORMATION_MESSAGE);

					dispose();
				}
			}
		});

		JButton btnDel = new JButton(I18NLanguage.FTPCONFIG_BTNDELETE);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!MonitorFrame.updateFlag) {
					if (tableOMC.getSelectedRow() == -1) {
						JOptionPane.showMessageDialog(null,
								I18NLanguage.FTPCONFIG_DELPOPWINDOW, "error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int[] selectRows = tableOMC.getSelectedRows();
						for (int i = 0; i < selectRows.length; i++) {
							tableModelOMC.removeRow(selectRows[i]);
						}

					}

				} else {
					JOptionPane.showMessageDialog(null,
							I18NLanguage.STORMTEST_INFO_POPUPWINDOW,
							"Failed", JOptionPane.INFORMATION_MESSAGE);

					dispose();
				}
			}
		});

		btnAdd.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnDel.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		JLabel lblNull1 = new JLabel("");
		JLabel lblNull2 = new JLabel("");
		JLabel lblNull3 = new JLabel("");
		JLabel lblNull4 = new JLabel("");
		JLabel lblNull5 = new JLabel("");
		JLabel lblNull6 = new JLabel("");
		panelBtn.add(btnAdd, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(lblNull1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(lblNull2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(lblNull3, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(lblNull4, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(lblNull5, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(lblNull6, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));
		panelBtn.add(btnDel, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 5, 5, 5), 0, 0));

		panelTable.add(jscroPanOMC, BorderLayout.CENTER);
		JPanel panelTabelN = new JPanel();
		JPanel panelTabelS = new JPanel();
		JPanel panelTabelW = new JPanel();

		panelCfgMidd.add(panelTable, BorderLayout.CENTER);
		panelCfgMidd.add(panelTabelN, BorderLayout.NORTH);
		panelCfgMidd.add(panelTabelS, BorderLayout.SOUTH);
		panelCfgMidd.add(panelTabelW, BorderLayout.WEST);
		panelCfgMidd.add(panelTabelW, BorderLayout.WEST);

		panelCfgMidd.add(panelBtn, BorderLayout.EAST);
		middlePanel.add(panelmid, BorderLayout.NORTH);
		middlePanel.add(panelCfgMidd, BorderLayout.CENTER);

	}

	public void DlgCreateftpUser() {
		OmcUserForm ftpUser = new OmcUserForm(this);
		ftpUser.setVisible(true);
	}

	public DefaultTableModel getTableModel() {
		return tableModelOMC;
	}

	public void bottomPanel() {
		bottomPanel = new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK = new JButton(I18NLanguage.FTPCONFIG_BTNOK);
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<OmcUser> users = new ArrayList<>();
				for(int i=0;i<tableModelOMC.getRowCount();++i){
					String name = tableOMC.getValueAt(i, 0).toString();
					String pwd = tableOMC.getValueAt(i, 1).toString();
					OmcUser user = new OmcUser();
					user.setUserName(name);
					user.setPwd(pwd);
					users.add(user);
				}
				if(dao!=null){
					dao.delAllUsers();
					dao.insertOmcUser(users);
				}
				JOptionPane.showMessageDialog(null,
						I18NLanguage.FTPCONFIG_SUCCESS, "success",
						JOptionPane.INFORMATION_MESSAGE);

				dispose();
			}
		});

		JButton btnCancel = new JButton(I18NLanguage.FTPCONFIG_BTNCANCEL);
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
