package com.massisframework.massis.dasi.agents.goals;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public abstract class AgentGoal<T> {

	public static enum GoalState {
		PENDING, SOLVING, SOLVED, REFINED, FAILED
	}

	private T goalData;
	private GoalState goalState;

	public AgentGoal(T goalData)
	{
		this.goalData = goalData;
		this.goalState=GoalState.PENDING;
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
	public void setGoalState(GoalState goalState)
	{
		this.goalState = goalState;
	}

	public GoalState getGoalState()
	{
		return goalState;
	}
	
	public String toString()
	{
		return goalState.name();
	}
}
