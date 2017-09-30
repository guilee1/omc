package com.ltln.modules.ni.omc.framework.impl;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IMgrInf;
import com.ltln.modules.ni.omc.framework.log.Logger;
import com.ltln.netmgt.security.AuthUtil;
import com.ltln.netmgt.security.authentication.AuthenticationAPI;
import com.ltln.netmgt.server.util.NmsUtil;

@Component("mgrInfImpl")
public class MgrInfImpl implements IMgrInf {

	@Override
	public void heartBeat() {
		//do nothing here~
	}

	@Override
	public boolean authentication(String userName, String pwd) {
		return authByServer(userName,pwd);
	}

	@Override
	public long getNtpTime() {
		return new Date().getTime();
	}

	private boolean authByServer(String userName, String pwd) {
        try {
            AuthenticationAPI authenticationAPI = (AuthenticationAPI) NmsUtil
                    .getAPI("NmsAuthenticationAPI");
            String challenge = authenticationAPI.getChallenge(userName);
            String key = AuthUtil.getChallengeKey(userName,pwd, challenge);
            return authenticationAPI.verifyCredentials(userName, key);
        } catch (Exception exp) {
            Logger.error("login fail~", exp);
        }
        return false;
    }
}
