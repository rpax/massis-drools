package com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.dasi.RuleHighLevelController;

@PropertyReactive
public class TeamNegotiationInfo {

	public static enum NegotiationStatus {
		NOT_STARTED, WAITING_FOR_RESPONSE, ALREADY_HAVE_TEAM,FINISHED
	}

	private NegotiationStatus status;
	private RuleHighLevelController peer;

	public TeamNegotiationInfo(NegotiationStatus negotiationStatus)
	{
		this.status = negotiationStatus;
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

	public NegotiationStatus getStatus()
	{
		return status;
	}

	public RuleHighLevelController getPeer()
	{
		return peer;
	}

}
