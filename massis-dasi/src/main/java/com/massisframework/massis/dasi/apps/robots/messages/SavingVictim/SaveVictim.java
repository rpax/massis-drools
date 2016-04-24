package com.massisframework.massis.dasi.apps.robots.messages.SavingVictim;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class SaveVictim extends RobotMessageContent<VictimRobot> {

	public SaveVictim(VictimRobot vt,	String uuid)
	{
		super(vt, uuid);
	}

}
