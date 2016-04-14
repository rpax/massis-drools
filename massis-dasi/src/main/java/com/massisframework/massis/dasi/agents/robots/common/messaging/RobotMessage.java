package com.massisframework.massis.dasi.agents.robots.common.messaging;

import java.util.function.Predicate;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.environment.RuleMessage;

public abstract class RobotMessage<T> extends RuleMessage<RobotMessageInfo<T>> {

	public RobotMessage(RobotMessageInfo<T> messageData,
			Predicate<RuleHighLevelController> predicateFilter) {
		super(messageData, predicateFilter);
	}

}
