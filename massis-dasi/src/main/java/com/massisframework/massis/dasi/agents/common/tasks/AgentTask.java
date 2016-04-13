package com.massisframework.massis.dasi.agents.common.tasks;

import java.util.concurrent.CompletionStage;

import com.massisframework.massis.model.agents.LowLevelAgent;

public interface AgentTask<TP,ResultType> {
	
	public CompletionStage<ResultType> execute(LowLevelAgent agent, TP taskParams);
}
