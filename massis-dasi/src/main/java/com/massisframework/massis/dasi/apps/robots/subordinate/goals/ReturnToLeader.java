package com.massisframework.massis.dasi.apps.robots.subordinate.goals;

import com.massisframework.massis.dasi.agents.goals.AgentGoal;
import com.massisframework.massis.dasi.apps.robots.leader.LeaderRobot;

public class ReturnToLeader extends AgentGoal<LeaderRobot> {

	public ReturnToLeader(LeaderRobot a)
	{
		super(a);
	}
}
