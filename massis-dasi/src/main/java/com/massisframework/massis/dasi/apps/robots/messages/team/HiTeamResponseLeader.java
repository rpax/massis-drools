package com.massisframework.massis.dasi.apps.robots.messages.team;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class HiTeamResponseLeader extends RobotMessageContent<String> {

	public HiTeamResponseLeader(String teamId, String uuid)
	{
		super(teamId, uuid);
	}

}
