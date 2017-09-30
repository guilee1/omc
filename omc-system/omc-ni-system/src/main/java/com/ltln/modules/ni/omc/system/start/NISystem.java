package com.ltln.modules.ni.omc.system.start;

import it.sauronsoftware.junique.AlreadyLockedException;
import it.sauronsoftware.junique.JUnique;

import javax.swing.JOptionPane;

import com.ltln.modules.ni.omc.system.start.ui.I18NLanguage;
import com.ltln.modules.ni.omc.system.start.ui.MonitorFrame;
import com.ltln.modules.ni.omc.system.util.Constants;

public class NISystem {

	public static void main(String[] args) {
		String appName = "omc-nbi-server";
		try {
		      JUnique.acquireLock(appName);
		    } catch (AlreadyLockedException exp) {
		      JOptionPane.showMessageDialog(null, "OMC-NI-System has running!", "Message", JOptionPane.OK_OPTION);
		      System.exit(0);
		    }
		
		Constants.init();
		I18NLanguage.init();
		MonitorFrame omc = new MonitorFrame();
		omc.drawOMC();
	}

}
