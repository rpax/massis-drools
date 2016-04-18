package com.massisframework.massis.dasi.apps.robots.messages.team;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class HiTeamResponseMember extends RobotMessageContent<String> {

	public HiTeamResponseMember(String teamId, String uuid)
	{
		super(teamId, uuid);
	}

}
