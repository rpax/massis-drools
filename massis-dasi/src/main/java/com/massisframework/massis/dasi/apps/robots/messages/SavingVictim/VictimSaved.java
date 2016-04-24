package com.massisframework.massis.dasi.apps.robots.messages.SavingVictim;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class VictimSaved extends RobotMessageContent<VictimRobot> {

	public VictimSaved(VictimRobot vt,	String uuid)
	{
		super(vt, uuid);
	}

}
