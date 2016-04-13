package com.massisframework.massis.dasi.environment;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.ReleaseId;
import org.kie.api.builder.Results;
import org.kie.api.runtime.Environment;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.io.ResourceFactory;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.model.building.SimulationObject;
import com.massisframework.massis.model.managers.EnvironmentManager;

public class RuleEnvironment {

	private static final String RULE_ENVIRONMENT = "RULE_ENVIRONMENT";

	private static HashMap<EnvironmentManager, RuleEnvironment> envMap = new HashMap<>();

	private Environment kieEnv;

	private RuleEnvironment() {
		this.kieEnv = KieServices.Factory.get().newEnvironment();
		this.kieEnv.set(RULE_ENVIRONMENT, this);
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
		KieBase kieBase = kiecontainer.newKieBase(kieBaseConfiguration);

		// Configure and create the KieSession
		KieSessionConfiguration kieSessionConfiguration = kieServices
				.newKieSessionConfiguration();

		KieSession kieSession = kieBase.newKieSession(kieSessionConfiguration,this.kieEnv);

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
