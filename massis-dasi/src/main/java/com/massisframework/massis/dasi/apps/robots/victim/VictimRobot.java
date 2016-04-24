package com.massisframework.massis.dasi.apps.robots.victim;

import java.util.Map;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.model.agents.LowLevelAgent;

@PropertyReactive
public class VictimRobot extends RobotAgent {

	private static final long serialVersionUID = 8456736523294234541L;
	private float energyConsumption;

	public VictimRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.energyConsumption = (float)Math.random()*2+2;
	}

	@Override
	protected String[] getRulePaths()
	{
		return new String[] { "rules/victim.drl" };
	}

	@Override
	public float getEnergyConsumption()
	{
		return this.energyConsumption;
	}

	public void infoLogger(String mensaje){
		logger.info(this.toString()+" : "+mensaje);
	}
	
	@Override
	public String toString()
	{
		return "Victim ["+this.agent.getID()+"]";
	}
}
