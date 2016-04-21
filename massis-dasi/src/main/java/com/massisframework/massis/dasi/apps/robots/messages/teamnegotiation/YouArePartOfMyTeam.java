package com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class YouArePartOfMyTeam extends RobotMessageContent<String> {

	public YouArePartOfMyTeam(String teamId, String uuid)
	{
		super(teamId, uuid);
	}

}
