package com.massisframework.massis.dasi.apps.robots.messages.getEvaluation;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class EvalVictim extends RobotMessageContent<VictimRobot> {

	public EvalVictim(VictimRobot vt,	String uuid)
	{
		super(vt, uuid);
	}

}
