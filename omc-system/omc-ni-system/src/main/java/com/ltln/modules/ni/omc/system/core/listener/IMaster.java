package com.ltln.modules.ni.omc.system.core.listener;

import com.ltln.modules.ni.omc.core.vo.AlarmVo;

public interface IMaster {

	void fireMsgEvent(AlarmVo msg);
}
