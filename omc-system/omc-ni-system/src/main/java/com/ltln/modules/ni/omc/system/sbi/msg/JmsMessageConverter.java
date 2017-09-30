package com.ltln.modules.ni.omc.system.sbi.msg;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.jms.support.converter.MessageConverter;


public class JmsMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object obj, Session session) throws JMSException {
        return null;
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
