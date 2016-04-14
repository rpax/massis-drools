package com.massisframework.massis.dasi.environment;

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
import org.kie.api.conf.KieBaseOption;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.api.runtime.conf.ClockTypeOption;
import org.kie.api.runtime.conf.SingleValueKieSessionOption;
import org.kie.internal.io.ResourceFactory;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.model.building.Building;
import com.massisframework.massis.model.building.SimulationObject;
import com.massisframework.massis.model.managers.EnvironmentManager;

public class RuleEnvironment {

	private static HashMap<EnvironmentManager, RuleEnvironment> envMap = new HashMap<>();

	public static enum REKeys {
		RULE_ENVIRONMENT, MESSAGE_QUEUE,MSG_ENTRY_POINT
	}

	private Environment kieEnv;
	private List<RuleHighLevelController> ruleAgents;

	private RuleEnvironment() {
		this.kieEnv = KieServices.Factory.get().newEnvironment();
		this.kieEnv.set(REKeys.RULE_ENVIRONMENT.name(), this);
		this.ruleAgents = new ArrayList<>();
	}

	public <T> void sendMessage(RuleHighLevelController from,
			RuleMessage<T> msg) {
		// Tiene que ir por entry points
		this.ruleAgents
				.stream()
				.filter(msg::validFor)
				.forEach(a -> from.receiveMessage(msg));

	}

	public KieSession createKieSession(RuleHighLevelController hlc,
			String... rulesPath) {
		return this.createKieSession(hlc, Arrays.asList(rulesPath));
	}

	public KieSession createKieSession(RuleHighLevelController hlc,
			Collection<String> rulesPath) {
		// De momento solo rescue

		KieServices kieServices = KieServices.Factory.get();

		// Adds the drools file to the KieFileSystem for necessary compilation
		// to occur
		KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
		for (String path : rulesPath) {
			kieFileSystem.write(ResourceFactory.newClassPathResource(path));
		}

		// Create the builder for the resources of the File System
		KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
		kieBuilder.buildAll();
		Results results = kieBuilder.getResults();
		if (results.hasMessages(Level.ERROR)) {
			throw new RuntimeException(
					"Errors when building rules: \n" + results);
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
		kieBaseConfiguration.setOption(EventProcessingOption.STREAM);

		KieBase kieBase = kiecontainer.newKieBase(kieBaseConfiguration);

		// Configure and create the KieSession
		KieSessionConfiguration kieSessionConfiguration = kieServices
				.newKieSessionConfiguration();
		kieSessionConfiguration.setOption(ClockTypeOption.get("pseudo"));

		KieSession kieSession = kieBase.newKieSession(kieSessionConfiguration,
				this.kieEnv);
		this.ruleAgents.add(hlc);
		return kieSession;
	}

	public static synchronized RuleEnvironment getInstanceFor(
			RuleHighLevelController hlc) {
		EnvironmentManager envKey = ((SimulationObject) hlc.getLowLevelAgent())
				.getEnvironment();
		RuleEnvironment envValue = envMap.get(envKey);
		if (envValue == null) {
			envValue = new RuleEnvironment();
			envMap.put(envKey, envValue);
		}
		return envValue;

	}
}
