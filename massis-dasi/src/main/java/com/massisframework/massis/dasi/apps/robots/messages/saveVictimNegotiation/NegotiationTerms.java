package com.massisframework.massis.dasi.apps.robots.messages.saveVictimNegotiation;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import org.kie.api.definition.type.Modifies;

@PropertyReactive
public class NegotiationTerms {

	private Double value;
	private VictimRobot victim;
	public NegotiationTerms(Double v, VictimRobot vi)
	{
		value = v;
		victim = vi;
	}
	
	public Double getValue()
	{
		return value;
	}
	
	@Modifies("value")
	public void setValue(Double v)
	{
		value = v;
	}
	
	@Modifies("victim")
	public void setVictim(VictimRobot v)
	{
		victim = v;
	}
	
	public VictimRobot getVictim()
	{
		return victim;
	}
	
}
