package com.massisframework.massis.dasi.apps.robots.leader.info;

import org.kie.api.definition.type.Modifies;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class VictimAssignedToSubordinate {
	private RobotAgent victim;
	private RobotAgent subordinate;
	public VictimAssignedToSubordinate(VictimRobot VTSictim, RobotAgent subordinate)
	{
		
		this.subordinate = subordinate;
		this.victim = VTSictim;
	}

	public RobotAgent getVictim()
	{
		return victim;
	}
	
	@Modifies({ "victim" })
	public void setVictim(RobotAgent victim)
	{
		this.victim = victim;
	}
	public RobotAgent getSubordinate()
	{
		return subordinate;
	}
	
	@Modifies({ "subordinate" })
	public void setSubordinate(RobotAgent subordinate)
	{
		this.subordinate = subordinate;
	}
	
	
}
