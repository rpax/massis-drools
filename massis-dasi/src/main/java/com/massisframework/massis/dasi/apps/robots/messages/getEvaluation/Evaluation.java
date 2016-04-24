package com.massisframework.massis.dasi.apps.robots.messages.getEvaluation;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;

@PropertyReactive
public class Evaluation {
	private RobotAgent agent;
	private Double value;
	
	Evaluation(RobotAgent a, Double v)
	{
		super();
		agent=a;
		value=v;
	}
	
	public void setAgent(RobotAgent a)
	{
		agent = a;
	}
	public void setValue(Double d)
	{
		value = d;
	}
	
	public Double getValue()
	{
		return value;
	}
	public RobotAgent getAgent()
	{
		return agent;
	}
}
