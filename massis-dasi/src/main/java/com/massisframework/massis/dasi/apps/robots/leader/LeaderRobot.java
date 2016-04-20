package com.massisframework.massis.dasi.apps.robots.leader;

import java.util.Iterator;
import java.util.Map;
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
		while(itR.hasNext()){
    	  aux = itR.next();
	   	  if(aux.isIdle())
		  {
	   	    if(res==null) 
			{	
		      res = aux;
		  	}
		 	else if(res.getLocation().distance2D(tv.getVictim().getLocation()) > aux.getLocation().distance2D(tv.getVictim().getLocation()))
			{	
			  res = aux;
			}
		  }		
		}
		return res;
	}
	
	public double getFloatBestIdleAgent(VictimToSave tv)
	{
		double res = -1, aux = 0;
		RobotAgent auxR = null;
		Iterator<RobotAgent> itR = this.getTeamMembers().iterator();
		while(itR.hasNext()){
		  auxR = itR.next();
		  if(auxR.isIdle())
		  {
			if(res==-1){
				res = auxR.getLocation().distance2D(tv.getVictim().getLocation());
			}
			else if(res > auxR.getLocation().distance2D(tv.getVictim().getLocation()))
			{
				res = aux;
			}
		  }
		}
		return res;
	}
	
}
