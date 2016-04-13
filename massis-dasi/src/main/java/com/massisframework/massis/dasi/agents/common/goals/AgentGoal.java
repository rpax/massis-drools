package com.massisframework.massis.dasi.agents.common.goals;

import java.util.function.BiFunction;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;

@PropertyReactive
public abstract class AgentGoal<T> {

	public static enum GoalState {
		PENDING, SOLVED, UNABLE_TO_SOLVE
	}

	public static enum GoalEvaluation {
		UNABLE, PASSED, FAILED
	}

	private T goalData;
	private GoalState goalState;
	private BiFunction<RuleHighLevelController, T, GoalEvaluation> evaluationFn;
	private RuleHighLevelController highLevelController;

	public AgentGoal(RuleHighLevelController highLevelController, T goalData,
			BiFunction<RuleHighLevelController, T, GoalEvaluation> evaluationFn) {
		this(highLevelController, goalData, evaluationFn, GoalState.PENDING);
	}

	public AgentGoal(RuleHighLevelController highLevelController, T goalData,
			BiFunction<RuleHighLevelController, T, GoalEvaluation> evaluationFn,
			GoalState goalState) {
		this.highLevelController = highLevelController;
		this.goalData = goalData;
		this.goalState = goalState;
		this.evaluationFn = evaluationFn;
	}

	public T getGoalData() {
		return goalData;
	}

	@Modifies({ "goalData" })
	public void setGoalData(T goalData) {
		this.goalData = goalData;
	}

	@Modifies({ "goalState" })
	public void setGoalState(GoalState goalState) {
		this.goalState = goalState;
	}

	public GoalEvaluation evaluateNow() {
		return this.evaluationFn.apply(this.highLevelController, getGoalData());
	}

	public GoalState getGoalState() {
		return goalState;
	}

}
