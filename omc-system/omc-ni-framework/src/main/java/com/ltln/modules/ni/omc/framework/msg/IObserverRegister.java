package com.ltln.modules.ni.omc.framework.msg;


/**
 * a interface that invoked when OMC server starting,
 * 	and sub modules must implement it for object state changing message sending.
 * 
 * caution: the implementor class must use '@Component("modulename_IObserverRegister")' format
 * 
 * @author Administrator
 *
 */
public interface IObserverRegister {

	/**
	 * all observer that would be registered by starting
	 * @param start true is starting,false is shutdown
	 */
	void registerObserver(boolean start);
}
