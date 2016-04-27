package com.massisframework.massis.dasi.agents.tasks;

import java.util.Collection;
import java.util.function.Predicate;

import com.massisframework.massis.dasi.RuleContext;
import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.events.EntryPointNames;
import com.massisframework.massis.dasi.events.messages.MessageReceived;
import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.building.LocationHolder;
import com.massisframework.massis.model.location.Location;
import com.massisframework.massis.model.managers.movement.ApproachCallback;
import com.massisframework.massis.pathfinding.straightedge.FindPathResult.PathFinderErrorReason;

public class SimpleTasksFactory {

	public static <T> AgentTask<T, Void, RuleHighLevelController> newSendMessage(
			Predicate<RuleHighLevelController> predicateFilter)
	{
		return new AgentTask<T, Void, RuleHighLevelController>()
		{

			@Override
			public Void execute(
					RuleHighLevelController sender, T data)
			{
				RuleContext.getInstanceFor(sender)
						.getRuleAgents().stream()
						.filter(predicateFilter)
						.map(RuleHighLevelController::getKieSession)
						.map(ks -> ks
								.getEntryPoint(
										EntryPointNames.MESSAGING.name()))
						.filter(ep -> ep != null)
						.forEach(ep -> ep
								.insert(new MessageReceived<>(sender, data)));
				return null;
			}
		};
	}

	public static <T> void sendMessage(RuleHighLevelController sender, T data)
	{
		RuleContext.getInstanceFor(sender)
				.getRuleAgents().stream()
				.map(RuleHighLevelController::getKieSession)
				.map(ks -> ks.getEntryPoint(EntryPointNames.MESSAGING.name()))
				.filter(ep -> ep != null)
				.forEach(ep -> ep.insert(new MessageReceived<>(sender, data)));
	}

	public static <T> void sendMessageMultiples(RuleHighLevelController sender,
			Collection<Object> receivers, T data)
	{
		receivers.forEach(r -> sendMessage(sender, r, data));

	}

	public static <T> void sendMessage(RuleHighLevelController sender,
			Object receiver, T data)
	{
		RuleContext.getInstanceFor(sender)
				.getRuleAgents().stream()
				.filter(a -> a == receiver)
				.map(RuleHighLevelController::getKieSession)
				.map(ks -> ks.getEntryPoint(EntryPointNames.MESSAGING.name()))
				.filter(ep -> ep != null)
				.forEach(ep -> ep.insert(new MessageReceived<>(sender, data)));
	}

	public static <T> AgentTask<T, Void, RuleHighLevelController> newSendBroadCastMessage()
	{
		return newSendMessage((a) -> true);
	}

	public static AgentTask<LocationHolder, Void, RuleHighLevelController> newFollow()
	{
		return new AgentTask<LocationHolder, Void, RuleHighLevelController>()
		{

			@Override
			public Void execute(
					RuleHighLevelController agent,
					LocationHolder l)
			{
				agent.getLowLevelAgent().approachTo(l.getLocation(),
						EMPTY_APPROACH_CALLBACK);
				return null;
			}
		};
	}

	public static AgentTask<Location, Void, RuleHighLevelController> newApproachTo()
	{
		return new AgentTask<Location, Void, RuleHighLevelController>()
		{

			@Override
			public Void execute(RuleHighLevelController agent, Location l)
			{
				agent.getLowLevelAgent().approachTo(l,
						EMPTY_APPROACH_CALLBACK);
				return null;
			}
		};
	}

	private static final ApproachCallback EMPTY_APPROACH_CALLBACK = new ApproachCallback()
	{
		@Override
		public void onTargetReached(LowLevelAgent agent)
		{
		}

		@Override
		public void onSucess(LowLevelAgent agent)
		{
		}

		@Override
		public void onPathFinderError(PathFinderErrorReason reason)
		{
		}

	};

}
