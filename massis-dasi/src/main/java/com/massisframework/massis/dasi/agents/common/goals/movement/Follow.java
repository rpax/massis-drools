package com.massisframework.massis.dasi.agents.common.goals.movement;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.agents.common.goals.AgentGoal;
import com.massisframework.massis.model.building.LocationHolder;
import com.massisframework.massis.model.location.Location;

@PropertyReactive
public class Follow
		extends AgentGoal<LocationHolder> {

	public Follow(RuleHighLevelController ruleHighLevelController,LocationHolder lh) {

		super(ruleHighLevelController,lh,
				(hlc, rlp) -> {
					return GoalEvaluation.FAILED;
				});
	}

	@PropertyReactive
	public static class Params {
		private Location target;
		private double reachDistance;

		public Params(Location target, double reachDistance) {
			super();
			this.target = target;
			this.reachDistance = reachDistance;
		}

		public Location getTarget() {
			return target;
		}

		@Modifies({ "target" })
		public void setTarget(Location target) {
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
