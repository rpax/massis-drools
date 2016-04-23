package com.massisframework.massis.dasi.apps.robots.messages.getHelp;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class IAmGoingToHelpYou extends RobotMessageContent<String> {

	public IAmGoingToHelpYou(String uuid)
	{
		super("Dont worry. I am going to help you", uuid);
	}

}
