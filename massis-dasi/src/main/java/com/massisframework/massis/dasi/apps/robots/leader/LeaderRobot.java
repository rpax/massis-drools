package com.massisframework.massis.dasi.apps.robots.leader;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.leader.info.VictimToSave;
import com.massisframework.massis.model.agents.LowLevelAgent;

@PropertyReactive
public class LeaderRobot extends RobotAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 941413336283805172L;

	public LeaderRobot(LowLevelAgent agent, Map<String, String> metadata,
			String resourcesFolder)
	{
		super(agent, metadata, resourcesFolder);
		this.setIdle(false);
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
	
	public RobotAgent getBestIdleAgent(VictimToSave tv)
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
	   	  		else if(res.distanceTo(tv.getVictim().getLocation()) 
		 			> aux.distanceTo(tv.getVictim().getLocation()))
	   	  			res = aux;
	   	  	}
		}
		return res;
	}
	
	public double getFloatBestIdleAgent(VictimToSave tv)
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
					res = auxR.distanceTo(tv.getVictim().getLocation());
				else if(res > auxR.distanceTo(tv.getVictim().getLocation()))
					res = aux;
			}
		}
		return res;
	}
}
