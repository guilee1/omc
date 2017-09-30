/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ltln.modules.ni.omc.framework.cq;

/**
 *
 * @author Administrator
 */
public class JaxbUtilTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JaxbUtil jaxbUtil= new JaxbUtil(Message.class);
        Message message = new Message();
        DataSet data = new DataSet();
        message.setDataSet(data);
        String str = jaxbUtil.toXml(message, "utf-8");
        System.out.print(str);
        
    }
    
}
