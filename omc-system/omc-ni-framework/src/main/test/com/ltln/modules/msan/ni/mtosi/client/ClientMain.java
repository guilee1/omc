package com.ltln.modules.msan.ni.mtosi.client;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Arrays;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.xml.ws.Holder;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tmforum.mtop.fmw.xsd.hdr.v1.Header;
import org.tmforum.mtop.fmw.xsd.nam.v1.NamingAttributeType;
import org.tmforum.mtop.nra.xsd.cmo.v1.CurrentMaintenanceOperationType;
import org.tmforum.mtop.nra.xsd.cmo.v1.MaintenanceOperationModeEnumType;
import org.tmforum.mtop.rtm.wsdl.mc.v1_0.MaintenanceControl;
import org.tmforum.mtop.rtm.xsd.mc.v1.PerformMaintenanceOperationRequest;

import com.ltln.modules.msan.ni.mtosi.webservice.util.MsanMtosiWebserviceUtil;
import com.ltln.modules.msan.ni.mtosi.webservice.util.ParseNameObj;

public class ClientMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		MaintenanceControl client = context.getBean("maintenanceControl",MaintenanceControl.class);
		configureSSLOnTheClient(client);
		PerformMaintenanceOperationRequest request = new PerformMaintenanceOperationRequest();
		long neId = 191;
		int slotId = 10;
		int portType = 1027;
		int portNum = 1;
		ParseNameObj nameObj = new ParseNameObj("", String.valueOf(neId),
				slotId, portType, "", portNum);
		NamingAttributeType tpName = MsanMtosiWebserviceUtil
				.getNamingAttributeFromParseNameObj(nameObj);
		String maintenanceOperation = "FACILITY_LOOPBACK";
		CurrentMaintenanceOperationType mainOperation = new CurrentMaintenanceOperationType();
		request.setMaintenanceOperation(mainOperation);
		request.setMaintenanceOperationMode(MaintenanceOperationModeEnumType.MOM_OPERATE);
		mainOperation.setTpName(tpName);
		mainOperation.setMaintenanceOperation(maintenanceOperation);
		Header h = new Header();
//		h.setSecurity("admin:admin");
		client.performMaintenanceOperation(new Holder<Header>(), request);
	}

	private static void configureSSLOnTheClient(Object obj) {
		File keyStoreFile = new File(
				"E:\\msan\\module-msan\\build\\trunk\\msan-ni-mtosi\\msan-ni-mtosi-impl\\envconf\\nicfg\\cer\\myclient.ks");
		File trustStoreFile = new File(
				"E:\\msan\\module-msan\\build\\trunk\\msan-ni-mtosi\\msan-ni-mtosi-impl\\envconf\\nicfg\\cer\\myclient.ts");

		Client client = ClientProxy.getClient(obj);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();

		try {
			TLSClientParameters tlsParams = new TLSClientParameters();
			tlsParams.setDisableCNCheck(true);

			KeyStore turstStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			String keypassword = "password";
			String trustPassword = "password";

			turstStore.load(new FileInputStream(trustStoreFile),
					trustPassword.toCharArray());
			TrustManagerFactory trustFactory = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustFactory.init(turstStore);
			TrustManager[] trustManagers = trustFactory.getTrustManagers();
			tlsParams.setTrustManagers(trustManagers);

			keyStore.load(new FileInputStream(keyStoreFile),
					keypassword.toCharArray());
			KeyManagerFactory keyFactory = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyFactory.init(keyStore, keypassword.toCharArray());
			KeyManager[] keyManagers = keyFactory.getKeyManagers();
			tlsParams.setKeyManagers(keyManagers);

			tlsParams.setCipherSuites(Arrays.asList(new String[] {
					"SSL_RSA_WITH_RC4_128_MD5", "SSL_RSA_WITH_DES_CBC_SHA" }));

			httpConduit.setTlsClientParameters(tlsParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
