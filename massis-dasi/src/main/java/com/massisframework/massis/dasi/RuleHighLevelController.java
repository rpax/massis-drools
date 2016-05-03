package com.massisframework.massis.dasi;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.massisframework.gui.javafx.LoggerProvider;
import com.massisframework.gui.javafx.SimulationLoggable;
import com.massisframework.massis.dasi.lowlevel.LowLevelInfo;
import com.massisframework.massis.dasi.lowlevel.SimTick;
import com.massisframework.massis.model.agents.HighLevelController;
import com.massisframework.massis.model.agents.LowLevelAgent;

public class RuleHighLevelController extends HighLevelController
		implements SimulationLoggable {

	private static final long serialVersionUID = 1L;
	private KieSession kieSession;
	private LowLevelInfo lowLevelInfo;
	private FactHandle lowLevelInfoHandle;
	private SimTick simTick;
	private RuleContext ruleEnv;
	private List<LoggerProvider> loggerProviders;

	public RuleHighLevelController(LowLevelAgent agent,
			Map<String, String> metadata, String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.agent.setHighLevelData(this);
		this.ruleEnv = RuleContext.getInstanceFor(this);
		this.kieSession = this.ruleEnv.createKieSession(this,
				this.getRulePaths());
		this.loggerProviders = new ArrayList<>();

	}

	protected String[] getRulePaths()
	{
		String[] paths = metadata.get("rules").split(",");
		return paths;
	}

	@Override
	public void info(String msg, String tipo)
	{
		for (LoggerProvider loggerProvider : loggerProviders)
		{
			loggerProvider.info(this, this.simTick.getTick(), tipo, msg);
		}
	}

	public void addLoggerProvider(LoggerProvider lp)
	{
		this.loggerProviders.add(lp);
	}

	public void removeLoggerProvider(LoggerProvider lp)
	{
		this.loggerProviders.remove(lp);
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
			this.kieSession.insert(simTick);
		}
		simTick.incTick(+1);
		this.kieSession.update(this.kieSession.getFactHandle(this), this);
		this.lowLevelInfo.updateInfo(simTick.getTick());
		this.kieSession.update(this.lowLevelInfoHandle, this.lowLevelInfo);
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