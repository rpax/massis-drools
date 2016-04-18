package com.massisframework.massis.dasi.apps.robots.leader;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

public class VictimAssignedToSubordinate {
	private RobotAgent victim;

	public VictimAssignedToSubordinate(VictimToSave VTSictim)
	{
		
		this.victim = VTSictim.getVictim();
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
