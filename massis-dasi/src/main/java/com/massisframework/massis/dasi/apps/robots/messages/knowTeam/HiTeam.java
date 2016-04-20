package com.massisframework.massis.dasi.apps.robots.messages.knowTeam;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class HiTeam extends RobotMessageContent<String> {

	public HiTeam(String teamId, String uuid)
	{
		super(teamId, uuid);
	}

}
