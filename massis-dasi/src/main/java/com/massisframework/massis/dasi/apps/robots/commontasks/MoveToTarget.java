package com.massisframework.massis.dasi.apps.robots.commontasks;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.location.Location;
import com.massisframework.massis.model.managers.movement.ApproachCallback;
import com.massisframework.massis.pathfinding.straightedge.FindPathResult.PathFinderErrorReason;

public class MoveToTarget
		implements AgentTask<Location, MoveToTarget.MoveToResult, RobotAgent> {

	public static enum MoveToResult {
		OK, REACHED, ERROR
	}

	@Override
	public MoveToResult execute(RobotAgent robot, Location to)
	{
		CompletableFuture<MoveToResult> cf = new CompletableFuture<MoveToTarget.MoveToResult>();
		float consumption = robot.getEnergyConsumption();
		robot.getLowLevelAgent().approachTo(to, new ApproachCallback()
		{

			@Override
			public void onTargetReached(LowLevelAgent agent)
			{
				cf.complete(MoveToResult.REACHED);
			}

			@Override
			public void onSucess(LowLevelAgent agent)
			{

				cf.complete(MoveToResult.OK);
				// Energy
				robot.increaseEnergy(-consumption);
			}

			@Override
			public void onPathFinderError(PathFinderErrorReason reason)
			{
				cf.complete(MoveToResult.ERROR);
			}
		});
		try
		{
			return cf.get();
		}
		catch (InterruptedException | ExecutionException e)
		{
			throw new RuntimeException(e);
		}

	}

}
