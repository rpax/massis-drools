package com.massisframework.massis.dasi.apps.robots.commontasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget.MoveToResult;
import com.massisframework.massis.dasi.apps.robots.messages.IAmGoingToHelpYou;

public class EnviarTeVoyARemolcar implements AgentTask<Void, EnviarTeVoyARemolcar.RemolcarResult, RobotAgent>{

	public static enum RemolcarResult {
		OK, SENT, ERROR
	}
	
	@Override
	public RemolcarResult execute(RobotAgent rhlc, Void taskParams) {
		String uuid=UUID.randomUUID().toString();
		SimpleTasksFactory.sendMessage(rhlc, new IAmGoingToHelpYou(uuid));
		return null;
	}

}
