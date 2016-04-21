package com.massisframework.massis.dasi.apps.robots.subordinate.tasks;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.JoinTeamResponse;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.TeamNegotiationInfo.NegotiationStatus;
import com.massisframework.massis.dasi.apps.robots.subordinate.info.JoinTeamInfo;

public class UnirseEquipo implements AgentTask<JoinTeamInfo, UnirseEquipo.UnirseEquipoResult, RobotAgent>{

	public static enum UnirseEquipoResult {
		OK, SENT, ERROR
	}

	@Override
	public UnirseEquipoResult execute(RobotAgent rhlc, JoinTeamInfo taskParams) {
		
	 	taskParams.setTeamStatusInfo( NegotiationStatus.FINISHED );
	 	rhlc.setTeamName(taskParams.getTeamName());
		SimpleTasksFactory.sendMessage(rhlc,taskParams.getSender(),new JoinTeamResponse(taskParams.getTeamInfo(), taskParams.getUuid()));
		rhlc.addTeamMember((RobotAgent)taskParams.getSender());
		
		return null;
	}
	
}
