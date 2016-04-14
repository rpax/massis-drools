package com.massisframework.massis.dasi.agents.common.tasks.messaging;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.agents.common.tasks.AgentTask;
import com.massisframework.massis.dasi.environment.RuleMessage;

public class SendMessage<T> implements AgentTask<RuleMessage<T>, Boolean> {

	@Override
	public <RM extends RuleMessage<T>> CompletionStage<Boolean> execute(RuleHighLevelController rhlc, RM msg) {
		rhlc.getRuleEnvironment().sendMessage(rhlc, msg);
		return CompletableFuture.completedFuture(true);
	}

}
