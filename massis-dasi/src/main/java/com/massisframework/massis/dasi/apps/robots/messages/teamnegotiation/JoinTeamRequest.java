package com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class JoinTeamRequest extends RobotMessageContent<String> {

	public JoinTeamRequest(String uuid)
	{
		super("Do you want to join my team?", uuid);
	}

}
