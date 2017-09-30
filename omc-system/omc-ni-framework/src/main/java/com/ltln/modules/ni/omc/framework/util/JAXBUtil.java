package com.ltln.modules.ni.omc.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;

import com.ltln.modules.ni.omc.framework.log.Logger;

/**
 * utility class for marshal from Object to XML format string
 * @author Administrator
 */
public class JAXBUtil {


    public static void marshal(Object element, OutputStream os) {
        try {
            JAXBContext jc = JAXBContext.newInstance(element.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            marshaller.marshal(element, os);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public static String getObjectToXml(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        marshal(object, byteArrayOutputStream);
        String contents = StringUtils.EMPTY;
        try {
            contents = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        	Logger.error(e);
        }
        Logger.debug(String.format("[Object2Xml##]:[class]:%s ## [info]:%s", object.getClass().getName(),contents));
        return contents;
    }
    

}
