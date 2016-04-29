package com.massisframework.massis.dasi.apps.robots.leader.info;

import org.kie.api.definition.type.Modifies;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

public class VictimKnown {
	
	private RobotAgent victim;
	
	public VictimKnown(RobotAgent victim){
		this.victim = victim;
	}

	public RobotAgent getVictim() {
		return victim;
	}
	
	@Modifies({ "victim" })
	public void setVictim(RobotAgent victim) {
		this.victim = victim;
	}

}
