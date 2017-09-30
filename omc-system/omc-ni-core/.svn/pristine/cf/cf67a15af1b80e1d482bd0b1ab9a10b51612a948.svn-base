package com.ltln.modules.ni.omc.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.TYPE })
public @interface Facade {
	public  String serviceName();

	public  String serviceBean();

	public  boolean isProxied() default false;

//	public  Class<RemoteInvocationFacade> proxiedFacade();

	public  String module() default "";
}