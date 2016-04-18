package com.massisframework.massis.dasi.apps.robots.messages;

import com.massisframework.massis.dasi.apps.robots.leader.TeamNegotiationToSaveVictim;

public class DelTeamNegotiation extends RobotMessageContent<TeamNegotiationToSaveVictim> {

	public DelTeamNegotiation(TeamNegotiationToSaveVictim info, String uuid)
	{
		super(info, uuid);
	}

}
