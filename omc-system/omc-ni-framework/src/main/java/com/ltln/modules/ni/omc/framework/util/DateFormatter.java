package com.ltln.modules.ni.omc.framework.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ltln.modules.ni.omc.framework.log.Logger;

/**
 * utility class for converting between Date and XMLGregorianCalendar
 * @author Administrator
 */
public final class DateFormatter {


    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (Exception e) {
        	Logger.error(e);
        }
        return gc;
    }

    public static Date convertToDate(XMLGregorianCalendar cal)  {
        return cal.toGregorianCalendar().getTime();
    }

    public static String getNow(){
    	return new Date().toString();
    }
}
