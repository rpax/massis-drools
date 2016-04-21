package com.massisframework.massis.dasi.apps.robots.victim.tasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget.MoveToResult;
import com.massisframework.massis.dasi.apps.robots.messages.IHaveNoEnergy;

public class Perseguir implements AgentTask<Void, MoveToTarget.MoveToResult, RobotAgent>{

	@Override
	public MoveToResult execute(RobotAgent rhlc, Void taskParams) {
		return null;
	}
}
