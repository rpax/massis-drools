package com.massisframework.massis.dasi.apps.robots.commontasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.Recargar.RechargeResult;
import com.massisframework.massis.dasi.apps.robots.messages.VictimSaved;

public class EnviarYaEstoyDisponible implements AgentTask<RobotAgent, EnviarYaEstoyDisponible.IdleResult, RobotAgent>{

	public static enum IdleResult {
		OK, SENT, ERROR
	}
	
	@Override
	public IdleResult execute(RobotAgent rhlc, RobotAgent taskParams) {
		String uuid=UUID.randomUUID().toString();
		SimpleTasksFactory.sendMessage(rhlc,new VictimSaved(taskParams, uuid));;
		return IdleResult.SENT;
	}
	
}
