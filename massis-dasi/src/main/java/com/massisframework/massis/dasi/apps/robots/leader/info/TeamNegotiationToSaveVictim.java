package com.massisframework.massis.dasi.apps.robots.leader.info;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.apps.robots.victim.VictimRobot;

@PropertyReactive
public class TeamNegotiationToSaveVictim extends TermNegotiation{

	private Double bestEva;
	private NegotiationStatus status;
	private VictimRobot victim;
	private RuleHighLevelController peer;
	private Integer nResponse;
	public TeamNegotiationToSaveVictim(Integer nRe ,VictimRobot v,NegotiationStatus negotiationStatus)
	{
		this.victim = v;
		this.status = negotiationStatus;
		this.nResponse = nRe;
		bestEva = (double) -1;
	}

	public Double getBestEva(	)
	{
		return this.bestEva;
	}
	
	@Modifies({ "bestEva" })
	public void setBestEva(Double Eva)
	{
		this.bestEva = Eva;
	}

	@Modifies({ "peer" })
	public void setPeer(Object peer)
	{
		this.peer = (RuleHighLevelController)peer;
	}

	@Modifies({ "status" })
	public void setStatus(NegotiationStatus status)
	{
		this.status = status;
	}

	
	@Modifies({ "nResponse" })
	public void setNResponse(int i)
	{
		this.nResponse = nResponse-i;
	}
	
	
	public VictimRobot getVictim()
	{
		return victim;
	}
	
	public int getNResponse()
	{
		return nResponse;
	}
	
	
	public NegotiationStatus getStatus()
	{
		return status;
	}

	public RuleHighLevelController getPeer()
	{
		return peer;
	}

}
