package com.massisframework.massis.dasi.apps.robots.leader.goals;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.agents.goals.AgentGoal;

@PropertyReactive
public class KnowMyTeam extends AgentGoal<Void> {

	public KnowMyTeam()
	{
		super(null);
	}
}
