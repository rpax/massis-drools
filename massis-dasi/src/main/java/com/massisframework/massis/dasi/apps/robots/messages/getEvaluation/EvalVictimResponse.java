package com.massisframework.massis.dasi.apps.robots.messages.getEvaluation;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class EvalVictimResponse extends RobotMessageContent<Evaluation> {

	public EvalVictimResponse(Evaluation vt,	String uuid)
	{
		super(vt, uuid);
	}

}
