package com.massisframework.massis.dasi.agents.goals;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public abstract class AgentGoal<T> {

	public static final int PENDING = 1;
	
	public static final int SOLVING = 2;

	public static final int SOLVED = 3;

	public static final int REFINED = 4;
	
	public static final int FAILED = 5;
	
	private T goalData;
	private int goalState;

	public AgentGoal(T goalData)
	{
		this.goalData = goalData;
		this.goalState=PENDING;
	}

	public T getGoalData()
	{
		return goalData;
	}

	@Modifies({ "goalData" })
	public void setGoalData(T goalData)
	{
		this.goalData = goalData;
	}

	@Modifies({ "goalState" })
	public void setGoalState(int goalState)
	{
		this.goalState = goalState;
	}

	public int getGoalState()
	{
		return goalState;
	}

}
