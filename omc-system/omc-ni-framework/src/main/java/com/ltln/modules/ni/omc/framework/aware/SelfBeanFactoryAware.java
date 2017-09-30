package com.ltln.modules.ni.omc.framework.aware;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

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
          return (T) beanFactory.getBean(beanName);  
      }  
      return null;  
  }  

  public static BeanFactory getBeanFactory(){
	  return beanFactory;
  }
}
