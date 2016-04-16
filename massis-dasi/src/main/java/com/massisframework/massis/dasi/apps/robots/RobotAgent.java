package com.massisframework.massis.dasi.apps.robots;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.building.WayPoint;
import com.massisframework.massis.model.location.Location;
import com.massisframework.massis.model.managers.movement.Path;
import com.massisframework.massis.pathfinding.straightedge.FindPathResult;

import straightedge.geom.KPoint;

@PropertyReactive
public abstract class RobotAgent extends RuleHighLevelController {

	@Override
	protected abstract String[] getRulePaths();

	protected Logger logger;
	private static final long serialVersionUID = -3482235328135497066L;

	private float energy;

	private String teamName;
	private Collection<RobotAgent> teamMembers;

	public RobotAgent(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.logger = Logger.getLogger("RobotAgent_" + this.agent.getID());
		this.energy = 1000;
	}

	public String getTeamName()
	{
		return teamName;
	}

	public boolean isTeamKnown()
	{
		return this.teamName != null;
	}

	public Collection<RobotAgent> getTeamMembers()
	{
		return teamMembers;
	}

	@Modifies("teamName")
	public void setTeamName(String teamName)
	{
		this.teamName = teamName;
	}

	@Modifies("teamMembers")
	public void setTeamMembers(Collection<RobotAgent> teamMembers)
	{
		this.teamMembers = teamMembers;
	}

	public Logger getLogger()
	{
		return logger;
	}

	public float getEnergy()
	{
		return energy;
	}

	@Modifies("energy")
	public void setEnergy(float energy)
	{
		this.energy = energy;
	}

	@Modifies("energy")
	public void increaseEnergy(float quantity)
	{
		this.energy += quantity;
	}

	//
	public double distanceTo(Location to)
	{
		CompletableFuture<Double> cf = new CompletableFuture<Double>();
		this.agent.getLocation().getFloor().findPath(this.agent.getLocation(),
				to, new FindPathResult()
				{
					@Override
					public void onSuccess(Path path)
					{
						double distance = 0;
						KPoint lastPoint = null;
						for (WayPoint wp : path.getPoints())
						{
							if (lastPoint != null)
							{
								distance += KPoint.distance(wp.getXY(),
										lastPoint);
							}
							lastPoint = wp.getXY();
						}
						cf.complete(distance);
					}

					@Override
					public void onError(PathFinderErrorReason reason)
					{
						throw new RuntimeException(
								"Error when finding path. Reason");
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

	public abstract float getEnergyConsumption();

}
