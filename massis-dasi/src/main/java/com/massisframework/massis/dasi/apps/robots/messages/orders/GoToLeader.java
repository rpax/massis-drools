package com.massisframework.massis.dasi.apps.robots.messages.orders;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class GoToLeader extends RobotMessageContent<RobotAgent>{

	public GoToLeader(RobotAgent info, String uuid)
	{
		super(info, uuid);
	}

}
