package com.massisframework.massis.dasi.apps.robots.leader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.leader.info.VictimToSave;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;
import com.massisframework.massis.dasi.logger.ControladorLog;
import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.location.Location;

@PropertyReactive
public class LeaderRobot extends RobotAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 941413336283805172L;
	private Collection<RobotAgent> teamMembers;
	private Collection<RobotAgent> teamLeaders;

	public LeaderRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.setIdle(false);
		this.teamMembers = new ArrayList<>();
		this.teamLeaders = new ArrayList<>();
		//ControladorLog.getInstance().addAgent(this);
	}
	public void info(String texto,String tipo)
	{
		//ControladorLog.getInstance().appendInfo(this.toString(), texto, tipo);
	}
	@Override
	protected String[] getRulePaths()
	{
		//
		return new String[] { "rules/leader.drl" };
	}
	@Override
	public String toString()
	{
		return "LeaderRobot ["+this.agent.getID()+"]";
	}

	@Override
	public float getEnergyConsumption()
	{
		return 0;
	}
	
	public Collection<RobotAgent> getTeamMembers()
	{
		return teamMembers;
	}
	
	public Collection<RobotAgent> getTeamLeaders()
	{
		return teamLeaders;
	}
	
	
	@Modifies("teamMembers")
	public void setTeamMembers(Collection<RobotAgent> teamMembers)
	{
		this.teamMembers = teamMembers;
	}
	
	@Modifies("teamLeaders")
	public void setTeamLeaders(Collection<RobotAgent> leaders)
	{
		teamLeaders = leaders;
	}

	@Modifies("teamMembers")
	public void addTeamMember(RobotAgent teamMember)
	{
		this.teamMembers.add(teamMember);

	}
	
	@Modifies("teamLeaders")
	public void addTeamLeader(RobotAgent leader)
	{
		this.teamLeaders.add(leader);
	}
	
	
	
	public RobotAgent getBestIdleAgent(VictimRobot tv)
	{
		RobotAgent res = null, aux = null;
		Iterator<RobotAgent> itR = this.getTeamMembers().iterator();
		/*
			aux = this.getTeamMembers().stream()
					.filter(robot -> 
							robot.isIdle())
					.max((p1,p2)  ->
							Double.compare(p1.distanceTo(tv.getVictim().getLocation()),
											p2.distanceTo(tv.getVictim().getLocation()))).get();
			if(aux!=null)
				return aux;
			return null;
		 */
		while(itR.hasNext()){
			aux = itR.next();
	   	  	if(aux.isIdle())
	   	  	{
	   	  		if(res==null) 	
	   	  			res = aux;
	   	  		else if(res.distanceTo(tv.getLocation()) 
		 			> aux.distanceTo(tv.getLocation()))
	   	  			res = aux;
	   	  	}
		}
		return res;
	}
	
	public double getFloatBestIdleAgent(VictimRobot tv)
	{
		double res = -1, aux = 0;
		RobotAgent auxR = null;
		Iterator<RobotAgent> itR = this.getTeamMembers().iterator();
		
		/*
			auxR = this.getTeamMembers().stream()
					.filter(robot -> 
							robot.isIdle())
					.max((p1,p2)  ->
							Double.compare(p1.distanceTo(tv.getVictim().getLocation()),
											p2.distanceTo(tv.getVictim().getLocation()))).get();
			if(auxR!=null)
				return auxR.distanceTo(tv.getVictim().getLocation());
			return -1;
		*/
		while(itR.hasNext()){
			auxR = itR.next();
			if(auxR.isIdle())
			{
				if(res==-1)
					res = auxR.distanceTo(tv.getLocation());
				else if(res > auxR.distanceTo(tv.getLocation()))
					res = aux;
			}
		}
		return res;
	}
	
	
	public boolean closestLeaderTo(Location loc){
		double myDistance = this.distanceTo(loc);
		double distance = Double.POSITIVE_INFINITY;
		for(RobotAgent leader : this.teamLeaders){
			if(leader.distanceTo(loc) < distance)
				distance = leader.distanceTo(loc);
		}
		return myDistance < distance; 
	}
}
