package com.massisframework.massis.dasi.apps.robots.messages.team;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class AddedToMyPoolLeader extends RobotMessageContent<String> {

	public AddedToMyPoolLeader(String info, String uuid)
	{
		super(info, uuid);
	}

}
