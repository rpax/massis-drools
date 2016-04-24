package com.massisframework.massis.dasi.apps.robots.messages.getHelp;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import com.massisframework.massis.model.location.Location;

public class NoEnergy extends RobotMessageContent<Location> {

	public NoEnergy(Location L, String uuid)
	{
		super(L, uuid);
	}

}
