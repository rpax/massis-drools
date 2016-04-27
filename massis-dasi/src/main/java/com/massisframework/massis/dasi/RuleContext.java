package com.massisframework.massis.dasi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.internal.io.ResourceFactory;

import com.massisframework.massis.model.building.SimulationObject;
import com.massisframework.massis.model.managers.EnvironmentManager;

public class RuleContext {

	private static HashMap<EnvironmentManager, RuleContext> envMap = new HashMap<>();
	private final HashMap<Class<?>, KieBase> knowledgeBases = new HashMap<>();

	private static enum REKeys {
		RULE_ENVIRONMENT
	}

	private Environment kieEnv;
	private List<RuleHighLevelController> ruleAgents;

	private RuleContext()
	{
		this.kieEnv = KieServices.Factory.get().newEnvironment();
		this.kieEnv.set(REKeys.RULE_ENVIRONMENT.name(), this);
		this.ruleAgents = new ArrayList<>();
	}

	public KieSession createKieSession(RuleHighLevelController hlc,
			String... rulesPath)
	{
		return this.createKieSession(hlc, Arrays.asList(rulesPath));
	}

	public KieSession createKieSession(RuleHighLevelController hlc,
			Collection<String> rulesPath)
	{

		KieServices kieServices = KieServices.Factory.get();
		if (!this.knowledgeBases.containsKey(hlc.getClass()))
		{
			this.knowledgeBases.put(hlc.getClass(),this.createKieBase(hlc.getClass(),rulesPath));
		}
		KieBase kieBase = this.knowledgeBases.get(hlc.getClass());
		// Configure and create the KieSession
		KieSessionConfiguration kieSessionConfiguration = kieServices
				.newKieSessionConfiguration();
		kieSessionConfiguration.setOption(ClockTypeOption.get("pseudo"));

		KieSession kieSession = kieBase.newKieSession(kieSessionConfiguration,this.kieEnv);
		this.ruleAgents.add(hlc);
		
		return kieSession;
	}

	private KieBase createKieBase(
			Class<? extends RuleHighLevelController> clazz, Collection<String> rulesPath)
	{

		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

		for (String path : rulesPath)
		{
			kieFileSystem.write(ResourceFactory.newClassPathResource(path));
		}

		// Create the builder for the resources of the File System
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);

		kieBuilder.buildAll();
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Level.ERROR))
		{
			System.err.println("Errors when building rules: \n" + results
					+ ". Shutting down");
			System.exit(-1);
		}
		if (!results.getMessages().isEmpty())
			System.err.println(kieBuilder.getResults().getMessages());
		// Get the Release ID
		ReleaseId relaseId = kieBuilder.getKieModule().getReleaseId();
		System.out.println("Release Id: " + relaseId);
		// Create the Container, wrapping the KieModule with the given ReleaseId
		KieContainer kiecontainer = kieServices.newKieContainer(relaseId);

		// Configure and create a KieContainer that reads the drools files
		KieBaseConfiguration kieBaseConfiguration = kieServices
				.newKieBaseConfiguration();
		kieBaseConfiguration.setOption(EventProcessingOption.CLOUD);

		KieBase kieBase = kiecontainer.newKieBase(kieBaseConfiguration);

		return kieBase;
	}

	public static synchronized RuleContext getInstanceFor(
			RuleHighLevelController hlc)
	{
		EnvironmentManager envKey = ((SimulationObject) hlc.getLowLevelAgent())
				.getEnvironment();
		RuleContext envValue = envMap.get(envKey);
		if (envValue == null)
		{
			envValue = new RuleContext();
			envMap.put(envKey, envValue);
		}
		return envValue;

	}

	public List<RuleHighLevelController> getRuleAgents()
	{
		return ruleAgents;
	}
}
