package com.ltln.modules.ni.omc.framework.msg;

import javax.jms.Destination;

import org.springframework.jms.core.JmsTemplate;

public class JmsSender {

    private JmsTemplate template;
    private Destination alarm;


    public Destination getAlarm() {
        return alarm;
    }

    public void setAlarm(Destination alarm) {
        this.alarm = alarm;
    }


    public JmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }


    /**
     * send alarm message
     *
     * @param message
     */
    public void sendAlarmMsg(Object message) {
        template.convertAndSend(this.alarm, message);
    }

}
