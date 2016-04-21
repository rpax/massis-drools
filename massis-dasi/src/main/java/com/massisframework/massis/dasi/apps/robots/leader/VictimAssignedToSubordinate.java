package com.massisframework.massis.dasi.apps.robots.leader;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

public class VictimAssignedToSubordinate {
	private RobotAgent victim;

	public VictimAssignedToSubordinate(RobotAgent victim)
	{
		this.victim = victim;
	}

	public RobotAgent getVictim()
	{
		return victim;
	}

	public void setVictim(RobotAgent victim)
	{
		this.victim = victim;
	}
}
