package com.massisframework.massis.dasi.apps.robots.messages.getHelp;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import com.massisframework.massis.model.location.Location;

public class IKnowYourState extends RobotMessageContent<String> {

	public IKnowYourState(String uuid)
	{
		super("I know your state.", uuid);
	}

}
