package com.ltln.modules.ni.omc.system.core.aware;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.system.core.dao.IOmcDao;
import com.ltln.modules.ni.omc.system.core.log.Logger;

/**
 * utility class for get spring bean by name.
 * @author Administrator
 *
 */
@Component
public final class SelfBeanFactoryAware implements BeanFactoryAware {

  private static BeanFactory beanFactory;  
  

  @Override
  public void setBeanFactory(BeanFactory factory){  
        setBeanFactoryAware(factory);
  }  

  static void setBeanFactoryAware(BeanFactory factory){
	  beanFactory = factory;
  }
  
  @SuppressWarnings("unchecked")
  public static <T> T getBean(String beanName) {  
      if (null != beanFactory) {  
    	  try{
    		  return (T) beanFactory.getBean(beanName); 
    	  }catch (Exception e) {
			Logger.error(e);
			return null;
		}
      }  
      return null;  
  }  

  public static BeanFactory getBeanFactory(){
	  return beanFactory;
  }
  
  public static ThreadPoolTaskExecutor getTaskThreadPool(){
	  return getBean("threadPoolTaskExecutor");
  }
  
  public static ThreadPoolTaskExecutor getTelnetReaderThreadPool(){
	  return getBean("telnetReaderThreadPool");
  }
  
  public static IOmcDao getDao(){
	  return getBean("omcDao");
  }
}
