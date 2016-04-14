package com.massisframework.massis.dasi.agents.common.tasks;

import java.util.concurrent.CompletionStage;

import com.massisframework.massis.dasi.RuleHighLevelController;

public interface AgentTask<TP,ResultType> {
	
	public <TP2 extends TP> CompletionStage<ResultType> execute(RuleHighLevelController rhlc, TP2 taskParams);
}
