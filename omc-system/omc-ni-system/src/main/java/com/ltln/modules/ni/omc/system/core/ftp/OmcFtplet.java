/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.ltln.modules.ni.omc.system.core.ftp;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ftpserver.ftplet.DefaultFtplet;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.ftplet.FtpletResult;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.listener.ConnectionModel;
import com.ltln.modules.ni.omc.system.core.listener.EConnectionType;
import com.ltln.modules.ni.omc.system.core.listener.IConnListenerContainer;
import com.ltln.modules.ni.omc.system.core.listener.IConnectionListener;
import com.ltln.modules.ni.omc.system.core.log.Logger;
@Component
public class OmcFtplet extends DefaultFtplet implements IConnListenerContainer {

	IConnectionListener listener;
	
	
	@Override
	public FtpletResult onConnect(final FtpSession session) throws FtpException,
			IOException {
		Logger.info(String.format("User %s connected to FtpServer", session.getClientAddress().toString()));
		if(this.listener != null){
			SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					String user = session.getUser()==null?StringUtils.EMPTY:session.getUser().getName();
					listener.ConnectionActive(new ConnectionModel(session.getClientAddress().toString(),EConnectionType.FTP,user));
				}
			});
		}
		return super.onConnect(session);
	}

	@Override
	public FtpletResult onDisconnect(final FtpSession session) throws FtpException,
			IOException {
		Logger.info(String.format("User %s onDisconnect to FtpServer", session.getClientAddress().toString()));
		if(this.listener != null){
			SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					String user = session.getUser()==null?StringUtils.EMPTY:session.getUser().getName();
					listener.ConnectionInactive(new ConnectionModel(session.getClientAddress().toString(),EConnectionType.FTP,user));
				}
			});
		}
		return super.onDisconnect(session);
	}

	@Override
	public void registerListener(IConnectionListener list){
		this.listener = list;
	}
	
	@Override
	public void unRegisterListener(IConnectionListener list) {
		this.listener = null;
	}

}
