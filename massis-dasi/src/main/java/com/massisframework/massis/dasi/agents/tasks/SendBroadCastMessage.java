package com.massisframework.massis.dasi.agents.tasks;

import com.massisframework.massis.dasi.RuleContext;
import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.events.EntryPointNames;
import com.massisframework.massis.dasi.events.messages.MessageReceived;

public class SendBroadCastMessage<T> implements AgentTask<T, Void,RuleHighLevelController> {

	@Override
	public  Void execute(RuleHighLevelController sender,
			T data)
	{
		RuleContext.getInstanceFor(sender)
				.getRuleAgents().stream()
				.map(RuleHighLevelController::getKieSession)
				.map(ks -> ks.getEntryPoint(EntryPointNames.MESSAGING.name()))
				.filter(ep -> ep != null)
				.forEach(ep -> ep.insert(new MessageReceived<>(sender, data)));
		return null;

	}
}
