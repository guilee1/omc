package com.ltln.modules.ni.omc.system.core.telnet.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.telnet.TelnetClient;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.core.scheduler.IScheduler;
import com.ltln.modules.ni.omc.system.util.Constants;

public class TinyTelnetClient implements ITelnetClient,Runnable {

	TelnetClient tc = new TelnetClient();
	PrintStream out;
	ITelnetReader reader;
	IScheduler scheduler = SelfBeanFactoryAware.getBean("defaultScheduler");
	
	@Override
	public void connect(String ipAddre) throws Exception{
		tc.connect(ipAddre,Constants.INSTRUCTION_DEFAULT_DEVICE_PORT);
		OutputStream outstr = tc.getOutputStream();
		out = new PrintStream(outstr);
		SelfBeanFactoryAware.getTelnetReaderThreadPool().execute(this);
	}

	@Override
	public boolean sendCommand(String command) {
		if (out != null && tc.isConnected()) {
			out.println(command);
			out.flush();
			return true;
		}
		return false;
	}

	@Override
	public void close(boolean forceClosed) {
		if(forceClosed){
			try {
				if(tc!=null&&tc.isConnected())
					tc.disconnect();
			} catch (IOException e) {
				Logger.error("Exception while closing telnet:",e);
			}
		}else
			this.sendCommand("quit");
	}

	@Override
	public void registerCallBack(ITelnetReader reader) {
		if(null!=reader)
			this.reader = reader;
	}

	@Override
	public void run() {
		InputStream instr = tc.getInputStream();
		final StringBuffer sbResponse = new StringBuffer();
        try{
            byte[] buff = new byte[1024];
            int ret_read = 0;
            do{
                ret_read = instr.read(buff);
                if(ret_read > 0){
                	if(sbResponse.length()==0){
                		scheduler.schedule(new Runnable() {
							@Override
							public void run() {
								if(reader!=null){
									String contents = sbResponse.toString();
									Logger.info(contents);
									sbResponse.setLength(0);
									reader.readComplete(contents);
								}
							}
						}, Constants.TELNET_WAIT_TIME_MS, TimeUnit.MILLISECONDS);
                	}
                	sbResponse.append(new String(buff, 0, ret_read));
                }
            }
            while (ret_read >= 0);
        }
        catch (IOException e){
            Logger.error("Exception while reading socket:",e);
        }
        this.close(true);
	}

	@Override
	public boolean isConnected() {
		return tc.isConnected();
	}

}
