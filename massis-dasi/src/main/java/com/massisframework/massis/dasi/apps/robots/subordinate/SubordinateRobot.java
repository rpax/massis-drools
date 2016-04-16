package com.massisframework.massis.dasi.apps.robots.subordinate;

import java.util.Map;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.model.agents.LowLevelAgent;

@PropertyReactive
public class SubordinateRobot extends RobotAgent {

	
	private static final long serialVersionUID = 1L;

	public SubordinateRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
	}

	@Override
	protected String[] getRulePaths()
	{
		return new String[]{"rules/subordinate.drl"};
	}

	@Override
	public String toString()
	{
		return "SubordinateRobot ["+this.agent.getID()+"]";
	}

	@Override
	public float getEnergyConsumption()
	{
		return 0;
	}

}
