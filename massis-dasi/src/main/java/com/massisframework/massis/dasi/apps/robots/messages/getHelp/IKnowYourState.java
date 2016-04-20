package com.massisframework.massis.dasi.apps.robots.messages.getHelp;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class IKnowYourState extends RobotMessageContent<String> {

	public IKnowYourState(String uuid)
	{
		super("Dont worry. I am going to help you", uuid);
	}

}
