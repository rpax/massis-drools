package com.massisframework.massis.dasi.apps.robots.leader.goals;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.agents.goals.AgentGoal;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

@PropertyReactive
public class KnowPool extends AgentGoal<String> {

	public KnowPool()
	{
		super(null);
	}
}
