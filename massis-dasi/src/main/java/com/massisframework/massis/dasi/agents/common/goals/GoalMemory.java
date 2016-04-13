package com.massisframework.massis.dasi.agents.common.goals;

import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public class GoalMemory {

	// priorityQueue.

	private AgentGoal<?> currentGoal;
	private ConcurrentLinkedQueue<AgentGoal<?>> goalQueue;

	public GoalMemory() {
		this(new ConcurrentLinkedQueue<>(), null);
	}

	public GoalMemory(ConcurrentLinkedQueue<AgentGoal<?>> goalQueue) {
		this(goalQueue, null);
	}

	public GoalMemory(ConcurrentLinkedQueue<AgentGoal<?>> goalQueue,
			AgentGoal<?> currentGoal) {
		this.currentGoal = currentGoal;
		this.goalQueue = goalQueue;
	}

	@Modifies({ "currentGoal" })
	public void setCurrentGoal(AgentGoal<?> goal) {
		if (!this.goalQueue.contains(goal))
			throw new NoSuchElementException(
					"Goal " + goal + "not in the goal queue");
		this.currentGoal = goal;
	}

	@Modifies({ "goalQueue" })
	public void addGoal(AgentGoal<?> goal) {
		this.goalQueue.add(goal);
	}

	public AgentGoal<?> getCurrentGoal() {
		return currentGoal;
	}

	public ConcurrentLinkedQueue<AgentGoal<?>> getGoalQueue() {
		return goalQueue;
	}

}
