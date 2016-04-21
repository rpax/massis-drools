package com.massisframework.massis.dasi.apps.robots.subordinate.tasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget.MoveToResult;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.JoinTeamResponse;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.TeamNegotiationInfo;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.TeamNegotiationInfo.NegotiationStatus;
import com.massisframework.massis.dasi.apps.robots.subordinate.info.JoinTeamInfo;

public class EnviarRespuestaHola implements AgentTask<JoinTeamInfo, EnviarRespuestaHola.AnswerHiResult, RobotAgent>{

	public static enum AnswerHiResult {
		OK, SENT, ERROR
	}
	
	@Override
	public AnswerHiResult execute(RobotAgent rhlc, JoinTeamInfo taskParams) {
		
		taskParams.setTeamStatusInfo(NegotiationStatus.WAITING_FOR_RESPONSE);
		taskParams.setTeamPeerInfo(rhlc);
		SimpleTasksFactory.sendMessage(rhlc, taskParams.getSender(), taskParams);
		
		SimpleTasksFactory.sendMessage(rhlc, new JoinTeamResponse(taskParams.getTeamInfo(), taskParams.getUuid()));
		return null;
	}

}
