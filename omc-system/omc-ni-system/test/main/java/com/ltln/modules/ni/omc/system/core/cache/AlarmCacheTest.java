package com.ltln.modules.ni.omc.system.core.cache;

import java.util.List;

import org.junit.Test;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;

public class AlarmCacheTest {

	AlarmCache cache = new AlarmCache();
	
	@Test
	public void testUnLimitMsg(){
		
		for(int i=0;i<1001;++i){
			AlarmVo vo = new AlarmVo();
			cache.put(vo);
		}
		List<AlarmVo> result = cache.find(2);
		System.out.println(result.size());
	}
}
