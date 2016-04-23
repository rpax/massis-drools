package com.massisframework.massis.dasi.apps.robots.messages.saveVictimNegotiation;

import com.massisframework.massis.dasi.apps.robots.leader.info.TeamNegotiationToSaveVictim;
import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class DelTeamNegotiation extends RobotMessageContent<TeamNegotiationToSaveVictim> {

	public DelTeamNegotiation(TeamNegotiationToSaveVictim info, String uuid)
	{
		super(info, uuid);
	}

}
