package com.massisframework.massis.dasi.agents.robots.rescuer.tasks;

import com.massisframework.massis.dasi.environment.RuleMessage;

public class StringMessage extends RuleMessage<String>{

	public StringMessage(String messageData) {
		super(messageData, (a)->true);
	}

}
