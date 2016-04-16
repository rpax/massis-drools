package com.massisframework.massis.dasi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.massisframework.massis.dasi.lowlevel.LowLevelInfo;
import com.massisframework.massis.dasi.lowlevel.SimTick;
import com.massisframework.massis.model.agents.HighLevelController;
import com.massisframework.massis.model.agents.LowLevelAgent;

public class RuleHighLevelController extends HighLevelController {

	private static final long serialVersionUID = 1L;
	private KieSession kieSession;
	private LowLevelInfo lowLevelInfo;
	private FactHandle lowLevelInfoHandle;
	private FactHandle simTickHandle;
	private SimTick simTick;
	private RuleContext ruleEnv;

	public RuleHighLevelController(LowLevelAgent agent,
			Map<String, String> metadata, String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.agent.setHighLevelData(this);
		this.ruleEnv = RuleContext.getInstanceFor(this);
		this.startKieSession(getRulePaths());
	}

	protected String[] getRulePaths()
	{
		String[] paths = metadata.get("rules").split(",");
		return paths;
	}

	protected void startKieSession(String[] paths)
	{
		ArrayList<String> rulesPath = new ArrayList<>();
		rulesPath.add("rules/common/lowlevel.drl");
		// rulesPath.add("rules/common/goals.drl");
		Arrays.stream(paths).forEach(rulesPath::add);
		this.kieSession = ruleEnv.createKieSession(this, rulesPath);
	}

	@Override
	public void stop()
	{
		/*
		 * Clean resources, threads...etc
		 */
		this.kieSession.dispose();
	}

	@Override
	public void step()
	{

		if (this.lowLevelInfo == null)
		{
			this.lowLevelInfo = new LowLevelInfo(this.agent);
			// Task executor
			this.lowLevelInfoHandle = this.kieSession.insert(this.lowLevelInfo);
			this.kieSession.insert(this);
			simTick = new SimTick(-1);
			this.simTickHandle = this.kieSession.insert(simTick);
		}
		simTick.incTick(+1);
		this.kieSession.update(simTickHandle,simTick);
		this.kieSession.update(this.lowLevelInfoHandle, this.lowLevelInfo);
		this.kieSession.startProcess("com.massisframework.massis.dasi.simulationflow");
		this.kieSession.update(simTickHandle,simTick);
		this.kieSession.fireAllRules();
		
		SessionPseudoClock clock = this.kieSession.getSessionClock();
		clock.advanceTime(1, TimeUnit.SECONDS);
	}

	public LowLevelAgent getLowLevelAgent()
	{
		return this.agent;
	}

	public RuleContext getRuleEnvironment()
	{
		return this.ruleEnv;
	}

	public KieSession getKieSession()
	{
		return kieSession;
	}

}