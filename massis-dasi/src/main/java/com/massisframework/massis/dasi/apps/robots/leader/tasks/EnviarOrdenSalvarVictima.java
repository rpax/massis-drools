package com.massisframework.massis.dasi.apps.robots.leader.tasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.leader.info.SaveVictimOrderInfo;
import com.massisframework.massis.dasi.apps.robots.messages.SaveVictim;

public class EnviarOrdenSalvarVictima implements AgentTask<SaveVictimOrderInfo, EnviarOrdenSalvarVictima.SaveVictimOrderResult, RobotAgent>{

	public static enum SaveVictimOrderResult {
		OK, SENT, ERROR
	}

	@Override
	public SaveVictimOrderResult execute(RobotAgent rhlc, SaveVictimOrderInfo taskParams) {
		
		String uuid=UUID.randomUUID().toString();
		SimpleTasksFactory.sendMessage(rhlc, taskParams.getSubordinate(), new SaveVictim(taskParams.getRobotVictim(),uuid));
		
		return null;
	}
}
