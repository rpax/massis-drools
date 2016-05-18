package com.massisframework.massis.dasi.apps.robots.leader.info;

import com.massisframework.massis.dasi.apps.robots.leader.LeaderRobot;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class PendingVictim {

	
	protected LeaderRobot leader;
	protected VictimRobot victim;
	
	public PendingVictim(LeaderRobot leader, VictimRobot victim){
		this.leader = leader;
		this.victim = victim;
	}
	
}
