package com.massisframework.massis.dasi.apps.robots.messages.negotiation;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;

public class SaveVictimNegotiationTerms extends RobotMessageContent<NegotiationTerms> {

	public SaveVictimNegotiationTerms(NegotiationTerms info, String uuid)
	{
		super(info, uuid);
	}

}
