package com.massisframework.massis.dasi.apps.robots.victim;

import java.util.Map;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.logger.ControladorLog;
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
		ControladorLog.getInstance().addAgent(this);
	}
	public void info(String texto,String tipo)
	{
		ControladorLog.getInstance().appendInfo(this.toString(), texto, tipo);
	}
	
	@Override
	public String toString()
	{
		return "VictimRobot ["+this.agent.getID()+"]";
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

}
