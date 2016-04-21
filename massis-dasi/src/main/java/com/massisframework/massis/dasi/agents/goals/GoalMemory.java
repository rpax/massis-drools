package com.massisframework.massis.dasi.agents.goals;

import java.util.Collection;
import java.util.HashSet;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

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

	public boolean containsGoalByClass(Class<? extends AgentGoal<?>> clazz)
	{
		/*
		System.out.println(this.goals.stream().filter(clazz::isInstance));
		System.out.println(this.goals.stream().filter(clazz::isInstance).findAny());
		System.out.println(this.goals.stream().filter(clazz::isInstance).findAny().isPresent());
		*/
		
		return this.goals.stream()
				.filter(clazz::isInstance)
				.findAny()
				.isPresent();
	}
	
	public String toString(){
		String aux = "";
		for( AgentGoal<?> goal : this.goals ){
			aux += goal.getClass().toString() + ", " + goal.getGoalState() +"\n";
		}
		return aux;
	}

}
