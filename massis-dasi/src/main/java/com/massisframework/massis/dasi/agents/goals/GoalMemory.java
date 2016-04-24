package com.massisframework.massis.dasi.agents.goals;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.agents.goals.AgentGoal.GoalState;

@PropertyReactive
public class GoalMemory {

	private Collection<AgentGoal<?>> goals;

	public GoalMemory()
	{
		this.goals = new HashSet<>();
	}

	public Collection<AgentGoal<?>> getGoals()
	{
		return goals;
	}

	@Modifies({ "goals" })
	public void setGoals(Collection<AgentGoal<?>> goals)
	{
		this.goals = goals;
	}

	@Modifies({ "goals" })
	public void addGoal(AgentGoal<?> goal)
	{
		this.goals.add(goal);
	}

	@Modifies({ "goals" })
	public void delGoal(AgentGoal<?> goal)
	{
		this.goals.remove(goal);
	}
	
	public boolean containsGoalByClass(Class<? extends AgentGoal<?>> clazz)
	{
		return this.goals.stream()
				.filter(clazz::isInstance)
				.findAny()
				.isPresent();
	}
	
	public boolean containsGoalByState(GoalState state2)
	{
		Iterator<AgentGoal<?>> it = goals.iterator();
		while(it.hasNext())
		{
			if(it.next().getGoalState()==state2)
				return true;
		}
		return false;
	}
}
