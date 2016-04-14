package com.massisframework.massis.dasi.agents.robots.rescuer.messaging;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.agents.robots.common.messaging.RobotMessage;
import com.massisframework.massis.dasi.agents.robots.common.messaging.RobotMessageInfo;

public class IAmHereMessage extends RobotMessage<String> {

	public IAmHereMessage(RuleHighLevelController rhlc, String data) {
		super(new RobotMessageInfo<>(rhlc, data), (a) -> true);
	}

}
