package com.ltln.modules.msan.ni.mtosi.client;

import com.ltln.modules.msan.ni.mtosi.log.Logger;

public class JmsAyncReceiver {

	public void receive(final Object obj){
		String txtMsg = (String)obj;
		Logger.info(txtMsg);
	}
}
