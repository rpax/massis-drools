package com.massisframework.massis.dasi.agents.common.tasks.movement;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.massisframework.massis.dasi.agents.common.tasks.AgentTask;
import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.building.LocationHolder;
import com.massisframework.massis.model.managers.movement.ApproachCallback;
import com.massisframework.massis.pathfinding.straightedge.FindPathResult.PathFinderErrorReason;

public class MoveTo implements AgentTask<LocationHolder, MoveTo.ApproachResult> {

	public enum ApproachResult {
		TARGET_REACHED, MOVED, ERROR
	};

	@Override
	public CompletionStage<ApproachResult> execute(
			LowLevelAgent agent, LocationHolder loc) {
		CompletableFuture<ApproachResult> cf = new CompletableFuture<MoveTo.ApproachResult>();
		agent.approachTo(loc.getLocation(), new ApproachCallback() {

			@Override
			public void onTargetReached(LowLevelAgent agent) {
				cf.complete(ApproachResult.TARGET_REACHED);
			}

			@Override
			public void onSucess(LowLevelAgent agent) {
				cf.complete(ApproachResult.MOVED);
			}

			@Override
			public void onPathFinderError(PathFinderErrorReason reason) {
				cf.complete(ApproachResult.ERROR);
			}
		});
		
		return cf;
	}

}
