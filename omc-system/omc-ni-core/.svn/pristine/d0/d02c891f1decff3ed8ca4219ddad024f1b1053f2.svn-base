package com.ltln.modules.ni.omc.core.util;

import static com.ltln.modules.ni.omc.core.util.ReflectionUtil.getFieldNameList;
import static com.ltln.modules.ni.omc.core.util.ReflectionUtil.getFieldVal;

import java.io.File;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ltln.modules.ni.omc.core.vo.BaseVO;
import com.ltln.modules.ni.omc.core.vo.DataFile;
import com.ltln.modules.ni.omc.core.vo.DataFile.FileHeader;
import com.ltln.modules.ni.omc.core.vo.DataFile.Objects.FieldName;
import com.ltln.modules.ni.omc.core.vo.DataFile.Objects.FieldName.N;
import com.ltln.modules.ni.omc.core.vo.DataFile.Objects.FieldValue;
import com.ltln.modules.ni.omc.core.vo.DataFile.Objects.FieldValue.Object.V;
/**
 * utility class for marshal from Object to XML format string
 * @author Administrator
 */
public class ResFileUtil {


	public static DataFile convertToDataFile(final List<? extends BaseVO> objList){
		DataFile dataFile = new DataFile();
		if(objList.isEmpty())
			return dataFile;
		dataFile.setFileHeader(buildHeader());
		DataFile.Objects objects = new DataFile.Objects();
		dataFile.setObjects(objects);
		Class<?> clazz = objList.get(0).getClass();
		objects.setFieldName(buildFieldName(clazz));
		objects.setFieldValue(buildFieldValue(objList));
		return dataFile;
	}
	
    static FieldValue buildFieldValue(final List<? extends BaseVO> objList) {
    	FieldValue fieldValue = new FieldValue();
    	if(objList.isEmpty())
    		return fieldValue;
    	List<String> fieldNames = getFieldNameList(objList.get(0).getClass());
    	for(BaseVO resObj : objList){
    		DataFile.Objects.FieldValue.Object object = buildFieldObject(fieldNames, resObj);
    		fieldValue.getObject().add(object);
    	}
		return fieldValue;
	}

    public static DataFile.Objects.FieldValue.Object buildFieldObject(BaseVO resObj){
    	List<String> fieldNames = getFieldNameList(resObj.getClass());
    	return buildFieldObject(fieldNames,resObj);
    }
    
	static DataFile.Objects.FieldValue.Object buildFieldObject(List<String> fieldNames, BaseVO resObj) {
		DataFile.Objects.FieldValue.Object object = new DataFile.Objects.FieldValue.Object();
		for(int i=0;i<fieldNames.size();++i){
			V v = new V();
			v.setI(BigInteger.valueOf(i+1));
			v.setValue(getFieldVal(resObj,fieldNames.get(i)));
			object.getV().add(v);
			object.setRmUID(resObj.getFileRmUID());
		}
		return object;
	}



	static FieldName buildFieldName(Class<?> clazz) {
    	FieldName fieldName = new FieldName();
    	List<String> fieldNames = getFieldNameList(clazz);
    	for(int i=0;i<fieldNames.size();++i){
    		N n = new N();
    		n.setI(BigInteger.valueOf(i+1));
    		n.setValue(fieldNames.get(i));
    		fieldName.getN().add(n);
    	}
		return fieldName;
	}

	static FileHeader buildHeader() {
    	FileHeader header = new FileHeader();
    	header.setCmVersion("V1.0.0");
    	header.setElementType("PTN");
    	header.setVendorName("SSDX");
    	header.setTimeStamp(convertToXMLGregorianCalendar(new Date()));
    	header.setTimeZone("UTC+8");
		return header;
	}

    public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        XMLGregorianCalendar gc = null;
        try {
            gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
        } catch (DatatypeConfigurationException e) {
        	e.printStackTrace();
        }
        return gc;
    }
    
    public static void marshal(Object element, File tempFile,String encoding)throws JAXBException {
    	 JAXBContext jc = JAXBContext.newInstance(element.getClass());
         Marshaller marshaller = jc.createMarshaller();
         marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
         marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
         marshaller.marshal(element, tempFile);
    }

    public static String getObjectToXml(DataFile.Objects.FieldValue.Object object) {
    	StringBuilder rsult = new StringBuilder();
    	rsult.append("            <Object rmUID=\"");
    	rsult.append(object.getRmUID()).append("\">").append("\n");
    	for(V v : object.getV()){
    		rsult.append("                <V i=\"");
    		rsult.append(v.getI().intValue());
    		rsult.append("\">");
    		rsult.append(v.getValue());
    		rsult.append("</V>").append("\n");
    	}
    	rsult.append("            </Object>");
    	return rsult.toString();
    }

}
