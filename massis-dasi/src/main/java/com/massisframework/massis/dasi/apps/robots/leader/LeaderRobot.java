package com.massisframework.massis.dasi.apps.robots.leader;

import java.util.Map;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.model.agents.LowLevelAgent;

@PropertyReactive
public class LeaderRobot extends RobotAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 941413336283805172L;

	public LeaderRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.setIdle(false);
	}
	
	@Override
	protected String[] getRulePaths()
	{
		//
		return new String[] { "rules/leader.drl" };
	}
	@Override
	public String toString()
	{
		return "LeaderRobot ["+this.agent.getID()+"]";
	}

	@Override
	public float getEnergyConsumption()
	{
		return 0;
	}
	
	
}
