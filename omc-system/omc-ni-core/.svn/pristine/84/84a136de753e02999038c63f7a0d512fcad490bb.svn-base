package com.ltln.modules.ni.omc.core;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Assert util class for entity validating when setter-method invoked
 *
 */
public class Asserter {

	static boolean PRODUCT_MODE = false;
	private static final Log log = LogFactory.getLog(Asserter.class);
	public static void fail(Class<?> clazz, String propertyName) {
		String error = String.format("fail in class %s,property %s", clazz.getName(),propertyName);
		if(PRODUCT_MODE){
			log.error(error);
		}else{
			throw new RuntimeException(error);
		}
	}

}
