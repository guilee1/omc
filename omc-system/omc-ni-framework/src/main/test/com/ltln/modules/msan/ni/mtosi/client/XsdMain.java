package com.ltln.modules.msan.ni.mtosi.client;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.tmforum.mtop.fmw.xsd.gen.v1.AnyListType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.ActivityStatusEnumType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.ActivityStatusType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.CommunicationPatternType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.CommunicationStyleType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.CompressionEnumType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.CompressionTypeType;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header.MsgSpecificProperties;
import org.tmforum.mtop.fmw.xsd.hdr.v1.MessageTypeType;
import org.tmforum.mtop.fmw.xsd.msg.v1.BaseExceptionMessageType;
import org.tmforum.mtop.rtm.xsd.pc.v1.CreateProtectionGroupException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.apache.cxf.binding.soap.Soap12;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.common.i18n.Message;
import org.apache.cxf.interceptor.Fault;
import java.util.logging.Logger;
import com.ltln.modules.msan.ni.mtosi.util.ObjectToXML;
import javax.xml.namespace.QName;

public class XsdMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CreateProtectionGroupException root = new CreateProtectionGroupException();
		BaseExceptionMessageType exception = new BaseExceptionMessageType();
		exception.setReason("reason");
		Header soapHeader = new Header();
		soapHeader.setSecurity("context.getSecurity()");
		soapHeader.setCommunicationPattern(CommunicationPatternType.SIMPLE_RESPONSE);
		soapHeader.setCommunicationStyle(CommunicationStyleType.RPC);
		soapHeader.setMsgType(MessageTypeType.ERROR);
		soapHeader.setActivityName("setActivityName");
		ActivityStatusType ast = new ActivityStatusType();
		ast.setValue(ActivityStatusEnumType.FAILURE);
		soapHeader.setActivityStatus(ast);
		soapHeader.setBatchSequenceEndOfReply(false);
		soapHeader.setBatchSequenceNumber(1L);
		CompressionTypeType ctt = new CompressionTypeType();
		ctt.setValue(CompressionEnumType.NO_COMPRESSION);
		soapHeader.setCompressionType(ctt);
		soapHeader.setCorrelationId("setCorrelationId");
		soapHeader.setDestinationURI("setDestinationURI");
		soapHeader.setFailureReplytoURI("setFailureReplytoURI");
		soapHeader.setFileLocationURI("setFileLocationURI");
//		soapHeader.setTimestamp(DateFormatter.convertToXMLGregorianCalendar(new Date()));
		soapHeader.setIteratorReferenceURI("setIteratorReferenceURI");
		MsgSpecificProperties mp = new MsgSpecificProperties();
		soapHeader.setMsgSpecificProperties(mp);
		exception.setHeader(soapHeader);
		AnyListType vendorExtensions = new AnyListType();
		exception.setVendorExtensions(vendorExtensions);
		root.setInvalidInput(exception);
		String xml = ObjectToXML.createXML(CreateProtectionGroupException.class, root);
		System.out.println(xml);
		StringReader sr = new StringReader(xml);
		InputSource is = new InputSource(sr);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(is);
		}  catch (Exception e) {
		}
		
//		Element ele = doc.createElementNS("xmlns:ns0=\"http://www.tmforum.org/mtop/fmw/xsd/gen/v0\"", "ns0:nothing");
		Element ele2 = doc.getDocumentElement();
//		ele.appendChild(ele2);
		
		Message msg = new Message("", Logger.getGlobal(), new Object[]{});
		Fault f = new Fault(msg);
		f.setDetail( ele2);
		SoapFault fault = SoapFault.createFault(f, Soap12.getInstance());
		fault.setMessage("reason");
		fault.setFaultCode(new QName("Server"));
		 throw fault;
//		System.out.println(fault);
	}

	
}
