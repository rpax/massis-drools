package com.massisframework.massis.dasi.apps.robots.victim;

import java.util.Map;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.model.agents.LowLevelAgent;

@PropertyReactive
public class VictimRobot extends RobotAgent {

	private static final long serialVersionUID = 8456736523294234541L;

	public VictimRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
	}

	// com.massisframework.massis.dasi.apps.robots.RechargeStation
	@Override
	protected String[] getRulePaths()
	{
		return new String[] { "rules/victim.drl" };
	}

	@Override
	public float getEnergyConsumption()
	{
		return 10;
	}

}
