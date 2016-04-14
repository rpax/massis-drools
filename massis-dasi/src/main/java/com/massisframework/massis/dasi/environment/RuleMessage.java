package com.massisframework.massis.dasi.environment;

import java.util.function.Predicate;

import com.massisframework.massis.dasi.RuleHighLevelController;

public class RuleMessage<T> {

	protected T messageData;
	protected Predicate<RuleHighLevelController> predicateFilter;

	public RuleMessage(T messageData,
			Predicate<RuleHighLevelController> predicateFilter) {
		this.messageData = messageData;
		this.predicateFilter = predicateFilter;
	}
	
	public T getMessageData() {
		return messageData;
	}

	public void setMessageData(T messageData) {
		this.messageData = messageData;
	}

	public boolean validFor(RuleHighLevelController hlc) {
		return this.predicateFilter.test(hlc);
	}

}
