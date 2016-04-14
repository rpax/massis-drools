package com.massisframework.massis.dasi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.kie.api.runtime.rule.FactHandle;

import com.massisframework.massis.dasi.agents.common.info.LowLevelInfo;
import com.massisframework.massis.dasi.environment.RuleEnvironment;
import com.massisframework.massis.dasi.environment.RuleMessage;
import com.massisframework.massis.model.agents.HighLevelController;
import com.massisframework.massis.model.agents.LowLevelAgent;

public class RuleHighLevelController extends HighLevelController {

	private static final long serialVersionUID = 1L;
	private KieSession kieSession;
	private long tick = 0;
	private LowLevelInfo lowLevelInfo;
	private FactHandle lowLevelInfoHandle;
	private RuleEnvironment ruleEnv;

	public RuleHighLevelController(LowLevelAgent agent,
			Map<String, String> metadata, String resourcesFolder) {
		super(agent, metadata, resourcesFolder);
		this.agent.setHighLevelData(this);
		this.ruleEnv = RuleEnvironment.getInstanceFor(this);
		String[] paths = metadata.get("rules").split(",");
		this.startKieSession(paths);
		// TODO
	}

	public LowLevelAgent getLowLevelAgent() {
		return this.agent;
	}

	private void startKieSession(String[] paths) {
		ArrayList<String> rulesPath = new ArrayList<>();
		rulesPath.add("rules/common/lowlevel.drl");
		rulesPath.add("rules/common/goals.drl");
		Arrays.stream(paths).forEach(rulesPath::add);
		this.kieSession = ruleEnv.createKieSession(this, rulesPath);
	}

	@Override
	public void stop() {
		/*
		 * Clean resources, threads...etc
		 */
		this.kieSession.dispose();
	}

	@Override
	public void step() {

		if (this.lowLevelInfo == null) {
			this.lowLevelInfo = new LowLevelInfo(this.agent);
			// Task executor
			this.lowLevelInfoHandle = this.kieSession.insert(this.lowLevelInfo);
			this.kieSession.insert(this);
		}
		this.lowLevelInfo.setTick(this.tick++);
		this.kieSession.update(this.lowLevelInfoHandle, this.lowLevelInfo);
		SessionPseudoClock clock = this.kieSession.getSessionClock();
		this.kieSession.fireAllRules();
		clock.advanceTime(1, TimeUnit.SECONDS);
	}

	public <T> void receiveMessage(RuleMessage<T> msg) {
		EntryPoint msgStream = this.kieSession
				.getEntryPoint(RuleEnvironment.REKeys.MSG_ENTRY_POINT.name());
		msgStream.insert(msg);
	}
}