package com.massisframework.massis.dasi.apps.robots.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.base.goals.HelpVictim;
import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.location.Location;

@PropertyReactive
public class BaseAgent extends RuleHighLevelController {

	
	protected Logger logger;
	private static final long serialVersionUID = -3482235328135497066L;

	private ArrayList<HelpVictim> pendingTasks;
	private Collection<RobotAgent> teamLeaders;
	

	public BaseAgent(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder){
		super(agent, metadata, resourcesFolder);
		this.logger = Logger.getLogger("RobotAgent_" + this.agent.getID());
		agent.setMaxSpeed(agent.getMaxSpeed()+agent.getMaxSpeed()*Math.random());
		this.teamLeaders = new ArrayList<>();	
	}

	protected String[] getRulePaths()
	{
		//
		return new String[] { "rules/base.drl" };
	}
	
	public Collection<RobotAgent> getTeamLeaders(){
		return teamLeaders;
	}
	
	public Location getLocation(){
		return this.agent.getLocation();
	}
	
	
	public boolean anyPendingTask(){
		return !pendingTasks.isEmpty();
	}
	
	
	@Modifies("teamLeaders")
	public void addTeamLeader(RobotAgent leaders){
		teamLeaders.add(leaders);
	}
	
	@Modifies("teamLeaders")
	public void setTeamLeaders(Collection<RobotAgent> leaders){
		teamLeaders = leaders;
	}
	
	
	public Logger getLogger(){
		return logger;
	}
	
	public void infoLogger(String mensaje){
		logger.info(this.toString()+" : "+mensaje);
	}
	
	@Override
	public String toString()
	{
		return "BaseAgent ["+this.agent.getID()+"]";
	}
}
