package com.ltln.modules.ni.omc.framework.locator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.framework.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.framework.log.Logger;
import com.ltln.netmgt.server.util.PureUtils;

@Component
public final class ServiceLocator implements ILocator {

    Map<String, Object> infMap = new SoftHashMap<>();
    
    final List<String> moduleNameList = new ArrayList<>();
    
    public ServiceLocator() {
    	moduleNameList.addAll( this.getModuleNames() );
	}

    private List<String> getModuleNames() {
    	List<String> result = new ArrayList<>();
		String path = PureUtils.rootDir + "conf/omc/";
		File f = new File(path);
		for (File file : f.listFiles()) {
			if (!file.isDirectory()) {
				String fileName = file.getName().replace("-service.xml", "");
				Logger.info("search file XXXXXXXXXX"+fileName);
				result.add(fileName);
			} 
		}
		return result;
	}

	@Override
    public <T> List<T> getInstances(Class<T> infClazz) {
        List<T> list = new ArrayList<>();
        for (String m : moduleNameList) {
            T instance = this.getInstance(infClazz, m);
            if (instance != null) 
                list.add(instance);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getInstance(Class<T> infClazz, String moduleName) {
        String key = StringUtils.lowerCase(moduleName + "_" + infClazz.getSimpleName());
        Object instance = infMap.get(key);
        if (null != instance)
            return (T) instance;
        Object bean = SelfBeanFactoryAware.getBean(key);
        if (bean == null) {
            Logger.info(String.format("can not load bean from ioc container %s", key));
            return null;
        }
        infMap.put(key, bean);
        return (T) bean;
    }
    
}
