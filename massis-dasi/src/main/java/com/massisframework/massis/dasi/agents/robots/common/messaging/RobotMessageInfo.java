package com.massisframework.massis.dasi.agents.robots.common.messaging;

import com.massisframework.massis.dasi.RuleHighLevelController;

public class RobotMessageInfo<T> {

	private RuleHighLevelController sender;
	private T messageContent;

	public RobotMessageInfo(RuleHighLevelController sender, T data) {
		super();
		this.sender = sender;
		this.messageContent = data;
	}

	public RuleHighLevelController getSender() {
		return sender;
	}

	public void setSender(RuleHighLevelController sender) {
		this.sender = sender;
	}

	public T getData() {
		return messageContent;
	}

	public void setData(T data) {
		this.messageContent = data;
	}

}
