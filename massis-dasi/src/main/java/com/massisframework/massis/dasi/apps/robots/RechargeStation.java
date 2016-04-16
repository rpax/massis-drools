package com.massisframework.massis.dasi.apps.robots;

import java.util.Map;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.model.agents.LowLevelAgent;

public class RechargeStation extends RuleHighLevelController {

	private static final long serialVersionUID = -5603629053266292496L;

	public RechargeStation(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
	}

	@Override
	protected String[] getRulePaths()
	{
		return new String[] { "rules/rechargestation.drl" };
	}

	/*
	 * StreamSupport .stream(this.agent.getAgentsInRange(500).spliterator(),
	 * false) .filter(RobotAgent.class::isInstance) .map(RobotAgent.class::cast)
	 * .forEach(r -> { r.setEnergy(r.getEnergy() + 1); });
	 */

}
