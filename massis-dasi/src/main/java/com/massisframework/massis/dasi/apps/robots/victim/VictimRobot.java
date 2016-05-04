package com.massisframework.massis.dasi.apps.robots.victim;

import java.util.Map;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.logger.ControladorLog;
import com.massisframework.massis.model.agents.LowLevelAgent;

@PropertyReactive
public class VictimRobot extends RobotAgent {

	private static final long serialVersionUID = 8456736523294234541L;
	private float energyConsumption;

	private boolean esperando;
	public VictimRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.energyConsumption = (float) Math.random() * 2 + 2;
		esperando = false;
	}

	
	public boolean getEsperando()
	{
		return esperando;
	}
	@Modifies("esperando")
	public void setEsperando(boolean esp)
	{
		esperando =esp;
	}
	
	@Override
	public String toString()
	{
		return "VictimRobot [" + this.agent.getID() + "]";
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
