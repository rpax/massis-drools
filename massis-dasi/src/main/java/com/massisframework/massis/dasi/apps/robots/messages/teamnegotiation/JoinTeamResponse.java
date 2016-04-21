package com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class JoinTeamResponse
		extends RobotMessageContent<TeamNegotiationInfo> {


	public JoinTeamResponse(TeamNegotiationInfo info, String uuid)
	{
		super(info, uuid);
	}

}
