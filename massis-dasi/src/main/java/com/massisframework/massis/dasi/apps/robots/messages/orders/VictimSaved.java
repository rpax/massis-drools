package com.massisframework.massis.dasi.apps.robots.messages.orders;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class VictimSaved extends RobotMessageContent<RobotAgent>{

	public VictimSaved(RobotAgent info, String uuid)
	{
		super(info, uuid);
	}

}
