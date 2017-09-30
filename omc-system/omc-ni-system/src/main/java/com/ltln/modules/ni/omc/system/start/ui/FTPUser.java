package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ltln.modules.ni.omc.system.util.Constants;
import com.ltln.modules.ni.omc.system.util.FTPUserObj;

public class FTPUser extends JDialog {
	JPanel panelMid;
	JPanel bottomPanel;
	JTextField textfieldRoot;
	JTextField textfieldAccount;
	static JTextField textfieldPassW;
	ButtonGroup permissionsGroup;
	JRadioButton read;
	JRadioButton write;
    
	String account;
	String rootDirectory;
	String passWord;
	static List<String> listPW = new ArrayList<String>();

	private FTPConfig ftpConfig;
	private DefaultTableModel tableModel;

	private static final long serialVersionUID = 9219256132434911691L;


	public FTPUser(FTPConfig ftpConfig) {
		this.setModal(true);
		this.setTitle(I18NLanguage.FTPUSER_TITLE);
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
		
		JPanel panelRoot = new JPanel(new GridBagLayout());

		JLabel lblAccount = new JLabel(I18NLanguage.FTPUSER_LBLACCOUNT);
		lblAccount.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldAccount = new JTextField(10);

		JLabel lblPassW = new JLabel(I18NLanguage.FTPUSER_LBLPASSW);
		lblPassW.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldPassW = new JTextField(10);
		// listPW=new ArrayList<String>();
		// listPW.add(textfieldPassW.getText());
		JLabel lblRoot = new JLabel(I18NLanguage.FTPUSER_LBLROOT);
		lblRoot.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		textfieldRoot = new JTextField(10);
		
		textfieldRoot.setEditable(false);
		textfieldRoot.setText(Constants.FTP_SERVER_ROOT_PATH);

		JButton btn = new JButton("...");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fd = new JFileChooser();
				fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (fd.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File f = fd.getSelectedFile();
					String path = f.getPath();
					textfieldRoot.setText(path);
				}

			}

		});
		btn.setPreferredSize(new Dimension(40, 20));
		//新增读写权限按钮
		read = new JRadioButton(I18NLanguage.FTPUSER_READ,true);
		read.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		write = new JRadioButton(I18NLanguage.FTPUSER_WRITE);
		write.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		panelRoot.add(read,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelRoot.add(write,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		read.setActionCommand("read");
		write.setActionCommand("write");
		permissionsGroup = new ButtonGroup();
		permissionsGroup.add(read);
		permissionsGroup.add(write);
				

		panelMid.add(lblAccount, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(textfieldAccount, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(lblPassW, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(textfieldPassW, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(lblRoot, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		panelMid.add(textfieldRoot, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
//				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		panelMid.add(btn, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
//				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
		panelMid.add(panelRoot,new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
//		panelMid.add(write,new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
//				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
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
				if (textfieldAccount.getText().isEmpty() || textfieldPassW.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelMid, I18NLanguage.FTPUSER_ENTER_COMPLETE, "error", JOptionPane.ERROR_MESSAGE);
				} else {
					if (tableModel.getRowCount() == 0) {
						listPW.add(textfieldPassW.getText());
						tableModel.addRow(new Object[] { textfieldAccount.getText(), textfieldRoot.getText() });
						FTPUserObj userObj = new FTPUserObj();
						userObj.setAccount(textfieldAccount.getText());
						userObj.setPwd(textfieldPassW.getText());
						userObj.setRootDir(textfieldRoot.getText());
						//加入读写权限
						if(permissionsGroup.getSelection().getActionCommand().equals("read")){
							userObj.setPermission(false);
						}
						if(permissionsGroup.getSelection().getActionCommand().equals("write")){
							userObj.setPermission(true);
						}
						
						Constants.FTPUSERS.put(userObj.getAccount(), userObj);
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
							listPW.add(textfieldPassW.getText());
							FTPUserObj userObj = new FTPUserObj();
							userObj.setAccount(textfieldAccount.getText());
							userObj.setPwd(textfieldPassW.getText());
							userObj.setRootDir(textfieldRoot.getText());
							//加入读写权限
							if(permissionsGroup.getSelection().getActionCommand().equals("read")){
								userObj.setPermission(false);
							}
							if(permissionsGroup.getSelection().getActionCommand().equals("write")){
								userObj.setPermission(true);
							}
							tableModel.addRow(new Object[] { userObj.getAccount(),userObj.isPermission()?"writable":"readable" });
							Constants.FTPUSERS.put(userObj.getAccount(), userObj);
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
