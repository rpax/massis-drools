package com.massisframework.massis.dasi;

import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.location.Location;
import com.massisframework.massis.model.managers.movement.ApproachCallback;
import com.massisframework.massis.pathfinding.straightedge.FindPathResult.PathFinderErrorReason;;

public class MovementUtils {
	private static ApproachCallback emptyAPCallback = new ApproachCallback() {

		@Override
		public void onTargetReached(LowLevelAgent agent) {
		}

		@Override
		public void onSucess(LowLevelAgent agent) {
		}

		@Override
		public void onPathFinderError(PathFinderErrorReason reason) {
		}
	};

	public static void approachTo(LowLevelAgent lla, Location loc) {
		lla.approachTo(loc, emptyAPCallback);
	}
}
