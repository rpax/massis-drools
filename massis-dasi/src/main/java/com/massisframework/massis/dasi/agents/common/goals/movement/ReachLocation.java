package com.massisframework.massis.dasi.agents.common.goals.movement;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.agents.common.goals.AgentGoal;
import com.massisframework.massis.model.building.LocationHolder;

@PropertyReactive
public class ReachLocation
		extends AgentGoal<ReachLocation.Params> {

	public ReachLocation(RuleHighLevelController ruleHighLevelController,
			LocationHolder target, double reachDistance) {

		super(ruleHighLevelController,
				new Params(target, reachDistance),
				(hlc, rlp) -> {
					if (!hlc.getLowLevelAgent().getLocation().isInSameFloor(rlp.target.getLocation()))
						return GoalEvaluation.FAILED;
					if (hlc.getLowLevelAgent().getLocation().distance2D(rlp.target.getLocation()) >= rlp.getReachDistance())
						return GoalEvaluation.FAILED;
					return GoalEvaluation.PASSED;
				});
	}

	@PropertyReactive
	public static class Params {
		private LocationHolder target;
		private double reachDistance;

		public Params(LocationHolder target, double reachDistance) {
			super();
			this.target = target;
			this.reachDistance = reachDistance;
		}

		public LocationHolder getTarget() {
			return target;
		}

		@Modifies({ "target" })
		public void setTarget(LocationHolder target) {
			this.target = target;
		}

		public double getReachDistance() {
			return reachDistance;
		}

		@Modifies({ "target" })
		public void setReachDistance(double reachDistance) {
			this.reachDistance = reachDistance;
		}

	}
}
