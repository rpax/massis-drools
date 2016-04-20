package com.massisframework.massis.dasi.apps.robots.victim.tasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget.MoveToResult;
import com.massisframework.massis.dasi.apps.robots.messages.getHelp.IHaveNoEnergy;
import com.massisframework.massis.model.location.Location;

public class EnviarMensajeAyuda implements AgentTask<Void, MoveToTarget.MoveToResult, RobotAgent>{

	@Override
	public MoveToResult execute(RobotAgent rhlc, Void taskParams) {
		String uuid=UUID.randomUUID().toString();
		SimpleTasksFactory.sendMessage(rhlc,new IHaveNoEnergy(rhlc.getLocation(), uuid));
		return null;
	}

}
