package com.massisframework.massis.dasi.apps.robots.messages;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

public class SaveVictim extends RobotMessageContent<RobotAgent> {

	public SaveVictim(RobotAgent info, String uuid)
	{
		super(info, uuid);
	}

}
