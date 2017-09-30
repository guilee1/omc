package com.ltln.modules.ni.omc.system.start.ui;

import java.awt.BorderLayout;
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

import com.ltln.modules.ni.omc.system.util.Toolkit;

public class About extends JDialog {
	private static final long serialVersionUID = 8330522394152405509L;
	JPanel middlepanel;
	JPanel bottompanel;

	public About() {

		super(new MonitorFrame().frame, "About", true);
		JPanel mainPanel = new JPanel(new BorderLayout());

		middlePanel();
		mainPanel.add(middlepanel, BorderLayout.CENTER);
		bottomPanel();
		mainPanel.add(bottompanel, BorderLayout.SOUTH);
		this.add(mainPanel);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(400, 350);
		this.setResizable(false);
		this.setLocation(300, 200);
	}

	public void middlePanel() {
		middlepanel = new JPanel();
		JLabel label = new JLabel(new ImageIcon(Toolkit.getImgPath("about.png")));
		label.setBounds(600, 500, middlepanel.getWidth(), middlepanel.getHeight());
		middlepanel.add(label);
	}

	public void bottomPanel() {
		bottompanel = new JPanel(new GridBagLayout());
		JButton btnOK = new JButton(I18NLanguage.ABOUT_OK);
		btnOK.setFont(new Font(I18NLanguage.FONT_NAME, Font.PLAIN, 12));
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		Dimension dimension = new Dimension(80, 20);
		btnOK.setPreferredSize(dimension);

		bottompanel.add(btnOK, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));

	}

}
