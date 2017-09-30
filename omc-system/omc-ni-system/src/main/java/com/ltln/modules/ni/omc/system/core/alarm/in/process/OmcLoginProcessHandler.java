package com.ltln.modules.ni.omc.system.core.alarm.in.process;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IMgrInf;
import com.ltln.modules.ni.omc.system.core.alarm.AlarmClient;
import com.ltln.modules.ni.omc.system.core.alarm.AlarmServerInitializer;
import com.ltln.modules.ni.omc.system.core.alarm.ICommandHandler;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.AckLoginAlarm;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.ReqLoginAlarm;
import com.ltln.modules.ni.omc.system.core.alarm.cmd.parser.ICmdParser;
import com.ltln.modules.ni.omc.system.core.alarm.in.AlarmServerHandler;
import com.ltln.modules.ni.omc.system.core.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.system.core.cache.IAlmContext;
import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.listener.ConnectionModel;
import com.ltln.modules.ni.omc.system.core.listener.EConnectionType;
import com.ltln.modules.ni.omc.system.core.locator.ILocator;
import com.ltln.modules.ni.omc.system.core.log.Logger;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsg;
import com.ltln.modules.ni.omc.system.nbi.msg.NmsMsgBuilder;
import com.ltln.modules.ni.omc.system.util.Constants;

@Component
public class OmcLoginProcessHandler implements ICommandHandler {

	@Resource(name="loginAlarmParser")
	ICmdParser parser;
	
	@Autowired
	ILocator locator;
	
	@Autowired
	IAlmContext context;
	
	@Autowired
	IOmcDao dao;
	
	@Override
	public void process(final ChannelHandlerContext channelHandlerContex, NmsMsg cmd) {
		int numberOfFail = getNumberOfLoginFail(channelHandlerContex);
		final ReqLoginAlarm request = (ReqLoginAlarm)parser.decode(cmd.getBody());
		AckLoginAlarm response = new AckLoginAlarm();
		if(request.badFormat()){
			response.setResult(AckLoginAlarm.RESULT_FAIL);
			response.setResDesc(request.getErrorMsg());
			setNumberOfLoginFail(channelHandlerContex,++numberOfFail);
			Logger.info(String.format("connection %s login fail count is %s reason is %s",channelHandlerContex.channel().remoteAddress().toString(), numberOfFail,request.getErrorMsg()));
		}else{
			if(!context.isLogin(channelHandlerContex.channel().id())){
				boolean canLogin = false;
				List<AlarmClient> cliens = context.getClients(request);
				if(cliens.size() < 2){
					if(cliens.size()==1){
						if(cliens.get(0).getReqType() != request.getEstatus().getValue()){
							canLogin = true;
						}else{
							setNumberOfLoginFail(channelHandlerContex,++numberOfFail);
							response.setResult(AckLoginAlarm.RESULT_FAIL);
							response.setResDesc("the login type exist");
							Logger.info(String.format("connection %s login fail count is %s reason is %s",channelHandlerContex.channel().remoteAddress().toString(), numberOfFail,response.getResDesc()));
						}
					}else{
						canLogin = true;
					}
					if(canLogin){
						boolean loginResult = false;
						boolean loginException = false;
						try{
							loginResult = Constants.ALM_LOGIN_MODEL?omcLogin(request):login(request);
						}catch (Exception e) {
							loginException = true;
							Logger.error(String.format("connection %s rmi login invoke error",channelHandlerContex.channel().remoteAddress().toString()),e);
						}
						response.setResult(loginResult?AckLoginAlarm.RESULT_SUCC:AckLoginAlarm.RESULT_FAIL);
						if(!loginResult)
							response.setResDesc(loginException?"remote exception":"account error");
						if(loginResult){
							context.add(channelHandlerContex,request.getEstatus().getValue(),request.getUserName(),request.getEstatus());
							final AlarmServerHandler handler = (AlarmServerHandler)channelHandlerContex.pipeline().get(AlarmServerInitializer.ALARM_SERVER_HANDLER);
							if(handler.getListener()!=null){
								SelfBeanFactoryAware.getTaskThreadPool().submit(new Runnable() {
									@Override
									public void run() {
										handler.getListener().ConnectionLogin(new ConnectionModel(channelHandlerContex.channel().remoteAddress().toString(), EConnectionType.ALM, request.getUserName()));
									}
								});
							}
							Logger.info(String.format("connection %s login success! count is %s",channelHandlerContex.channel().remoteAddress().toString(), numberOfFail));
						}else{
							setNumberOfLoginFail(channelHandlerContex,++numberOfFail);
							Logger.info(String.format("connection %s login fail count is %s reason is %s",channelHandlerContex.channel().remoteAddress().toString(), numberOfFail,response.getResDesc()));
						}
					}
				}else{
					setNumberOfLoginFail(channelHandlerContex,++numberOfFail);
					response.setResult(AckLoginAlarm.RESULT_FAIL);
					response.setResDesc("max number of login");
					Logger.info(String.format("connection %s login fail count is %s reason is %s",channelHandlerContex.channel().remoteAddress().toString(), numberOfFail,response.getResDesc()));
				}
			}else{
				setNumberOfLoginFail(channelHandlerContex,++numberOfFail);
				response.setResult(AckLoginAlarm.RESULT_FAIL);
				response.setResDesc("this socket has logined");
				Logger.info(String.format("connection %s login fail count is %s reason is %s",channelHandlerContex.channel().remoteAddress().toString(), numberOfFail,response.getResDesc()));
			}	
		}
		channelHandlerContex.writeAndFlush(NmsMsgBuilder.buildAckLoginMsg(parser.encode(response)));
		if(numberOfFail == 3){
			Logger.info(String.format("connection %s login fail count is 3~ disconnect it!",channelHandlerContex.channel().remoteAddress().toString()));
			channelHandlerContex.close();
		}
	}

	private void setNumberOfLoginFail(ChannelHandlerContext channelHandlerContex,int i) {
		final AttributeKey<Integer> numberOfLoginFail = AttributeKey.valueOf(channelHandlerContex.channel().id().asLongText());
		Attribute<Integer> n = channelHandlerContex.channel().attr(numberOfLoginFail);
		assert n != null;
		n.set(i);
	}

	private int getNumberOfLoginFail(ChannelHandlerContext channelHandlerContex) {
		final AttributeKey<Integer> numberOfLoginFail = AttributeKey.valueOf(channelHandlerContex.channel().id().asLongText());
		Attribute<Integer> n = channelHandlerContex.channel().attr(numberOfLoginFail);
		if( n.get()==null )
			n.set(0);
		return n.get();
	}

	private boolean login(ReqLoginAlarm login) throws Exception{
		IMgrInf mgrInf = locator.getInstances(IMgrInf.class);
		return mgrInf.authentication(login.getUserName(), login.getPwd());
	}

	private boolean omcLogin(ReqLoginAlarm login)throws Exception{
		return dao.getUserCount(login.getUserName(), login.getPwd()) > 0;
	}
	
}
