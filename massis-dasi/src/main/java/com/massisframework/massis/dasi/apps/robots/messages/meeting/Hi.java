package com.massisframework.massis.dasi.apps.robots.messages.meeting;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import com.massisframework.massis.model.location.Location;

public class Hi extends RobotMessageContent<String> {

	public Hi(String t, String uuid)
	{
		super(t, uuid);
	}

}
