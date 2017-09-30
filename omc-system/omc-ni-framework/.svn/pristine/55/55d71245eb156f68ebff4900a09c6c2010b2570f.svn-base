package com.ltln.modules.ni.omc.framework.msg.convertor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConverter;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;

public class JmsMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object obj, Session session) throws JMSException {
    	ActiveMQObjectMessage txtMsg = (ActiveMQObjectMessage) session.createObjectMessage();
    	txtMsg.setObject((AlarmVo)obj);
        return txtMsg;
    }

    @Override
    public Object fromMessage(Message msg) throws JMSException {
        if (msg instanceof ActiveMQObjectMessage) {
        	ActiveMQObjectMessage txtMsg = (ActiveMQObjectMessage) msg;
            return txtMsg.getObject();
        }
        return null;
    }
}
