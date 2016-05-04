package com.massisframework.massis.dasi.apps.robots.messages.orders;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class ACKGoToLeader extends RobotMessageContent<RobotAgent>{

	public ACKGoToLeader(RobotAgent info, String uuid)
	{
		super(info, uuid);
	}
}
