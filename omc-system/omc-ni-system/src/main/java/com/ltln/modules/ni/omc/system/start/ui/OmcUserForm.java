package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class OmcUserForm extends JDialog {
	JPanel panelMid;
	JPanel bottomPanel;
	JTextField textfieldAccount;
	static JTextField textfieldPassW;
	String account;
	String passWord;

	private OmcUserConfig ftpConfig;
	private DefaultTableModel tableModel;

	private static final long serialVersionUID = 9219256132434911691L;


	public OmcUserForm(OmcUserConfig ftpConfig) {
		this.setModal(true);
		this.setTitle(I18NLanguage.OMC_USER_ADD_TITLE);
		this.ftpConfig = ftpConfig;
		this.tableModel = ftpConfig.getTableModel();
		JPanel mainPanel = new JPanel(new BorderLayout());
		middlePanel();
		mainPanel.add(panelMid, BorderLayout.CENTER);
		bottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		this.setSize(300, 200);
		this.setResizable(false);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public void middlePanel() {
		panelMid = new JPanel(new GridBagLayout());

		JLabel lblAccount = new JLabel(I18NLanguage.FTPUSER_LBLACCOUNT);
		lblAccount.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldAccount = new JTextField(10);

		JLabel lblPassW = new JLabel(I18NLanguage.FTPUSER_LBLPASSW);
		lblPassW.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldPassW = new JTextField(10);
		

		panelMid.add(lblAccount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(textfieldAccount, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(lblPassW, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(textfieldPassW, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
	}

	public void bottomPanel() {
		bottomPanel = new JPanel(new BorderLayout());
		JPanel panelN = new JPanel();
		JPanel panelS = new JPanel();
		JPanel panelW = new JPanel();
		JPanel panelE = new JPanel();
		JPanel panelC = new JPanel(new BorderLayout());
		JButton btnOK = new JButton(I18NLanguage.FTPUSER_BTNOK);
		btnOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (textfieldAccount.getText().isEmpty() || textfieldPassW.getText().isEmpty() ||textfieldPassW.getText().contains(";")) {
					JOptionPane.showMessageDialog(panelMid, I18NLanguage.FTPUSER_ENTER_COMPLETE, "error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (tableModel.getRowCount() == 0) {
						tableModel.addRow(new Object[] { textfieldAccount.getText(), textfieldPassW.getText() });
						dispose();
					} else {
						//判断所创建账户是否已存在
						boolean b = false;
						for (int i = 0; i <= (tableModel.getRowCount() - 1); i++) {
							if (textfieldAccount.getText().equals(tableModel.getValueAt(i, 0))) {
								b = true;
							}

						}
						if (b) {
							JOptionPane.showMessageDialog(panelMid, I18NLanguage.FTPUSER_EXIST, "error",
									JOptionPane.ERROR_MESSAGE);

						} else {
							tableModel.addRow(new Object[] { textfieldAccount.getText(),textfieldPassW.getText() });
							dispose();

						}
					}

				}

			}
		});
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));

		JButton btnCancel = new JButton(I18NLanguage.FTPUSER_BTNCANCEL);
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
