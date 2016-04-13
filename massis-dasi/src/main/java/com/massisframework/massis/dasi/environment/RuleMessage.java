package com.massisframework.massis.dasi.environment;

import java.util.function.Predicate;

import com.massisframework.massis.model.agents.LowLevelAgent;

public class RuleMessage<T> {

	protected T messageData;
	protected Predicate<? super LowLevelAgent> predicateFilter;

	public RuleMessage(T messageData,
			Predicate<? super LowLevelAgent> predicateFilter) {
		this.messageData = messageData;
		this.predicateFilter = predicateFilter;
	}

}
