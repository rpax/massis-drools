package com.massisframework.massis.dasi.apps.robots.commontasks;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget.MoveToResult;
import com.massisframework.massis.dasi.apps.robots.leader.VictimToSave;
import com.massisframework.massis.model.location.Location;

public class Recargar implements AgentTask<RobotAgent, Recargar.RechargeResult, RobotAgent>{

	public static enum RechargeResult {
		OK, RECHARGED, ERROR
	}
	
	@Override
	public RechargeResult execute(RobotAgent rhlc, RobotAgent taskParams) {
		 taskParams.setEnergy(1000);
		return RechargeResult.RECHARGED;
	}

}
