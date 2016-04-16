package com.massisframework.massis.dasi.agents.tasks;

import com.massisframework.massis.dasi.RuleHighLevelController;

public interface AgentTask<TP,ResultType,RHLC extends RuleHighLevelController> {
	
	public ResultType execute(RHLC rhlc, TP taskParams);
}
