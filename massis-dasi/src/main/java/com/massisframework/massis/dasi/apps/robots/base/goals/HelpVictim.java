package com.massisframework.massis.dasi.apps.robots.base.goals;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.agents.goals.AgentGoal;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

@PropertyReactive
public class HelpVictim extends AgentGoal<VictimRobot> {

	public HelpVictim(VictimRobot a)
	{
		super(a);
	}
}
