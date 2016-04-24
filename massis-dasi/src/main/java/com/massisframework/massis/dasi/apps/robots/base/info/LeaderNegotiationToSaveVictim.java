package com.massisframework.massis.dasi.apps.robots.base.info;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

@PropertyReactive
public class LeaderNegotiationToSaveVictim {

	private VictimRobot victim;
	private RuleHighLevelController peer;
	private Integer nResponse;
	private RobotAgent bestAgent;
	private Double bestValueAgent;
	public LeaderNegotiationToSaveVictim(Integer nRe ,VictimRobot v)
	{
		this.victim = v;
		this.nResponse = nRe;
		bestValueAgent = (double) -1;
		bestAgent = null;
	}

	@Modifies({ "peer" })
	public void setPeer(Object peer)
	{
		this.peer = (RuleHighLevelController)peer;
	}

	
	@Modifies({ "nResponse" })
	public void setNResponse(int i)
	{
		this.nResponse = nResponse-i;
	}
	
	@Modifies({ "bestAgent" })
	public void setBestAgent(RobotAgent agent)
	{
		bestAgent = agent;
	}
	
	@Modifies({ "bestValueAgent" })
	public void setBestValueAgent(Double v)
	{
		bestValueAgent = v;
	}
	
	public Double getBestValueAgent()
	{
		return bestValueAgent;
	}
	
	public RobotAgent getBestAgent()
	{
		return bestAgent;
	}
	
	public VictimRobot getVictim()
	{
		return victim;
	}
	
	public int getNResponse()
	{
		return nResponse;
	}
	
	public RuleHighLevelController getPeer()
	{
		return peer;
	}

}
