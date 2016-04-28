package com.massisframework.massis.dasi.apps.robots.victim.goals;

import com.massisframework.massis.dasi.agents.goals.AgentGoal;
import com.massisframework.massis.dasi.apps.robots.leader.LeaderRobot;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

public class GoToSafePoint extends AgentGoal<LeaderRobot>{

	public GoToSafePoint(LeaderRobot goalData) {
		super(goalData);
	}

}
