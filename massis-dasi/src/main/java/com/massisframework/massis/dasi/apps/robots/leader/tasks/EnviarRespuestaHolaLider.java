package com.massisframework.massis.dasi.apps.robots.leader.tasks;

import java.util.UUID;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget;
import com.massisframework.massis.dasi.apps.robots.commontasks.MoveToTarget.MoveToResult;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.ImLeader;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.TeamNegotiationInfo;


public class EnviarRespuestaHolaLider implements AgentTask<TeamNegotiationInfo, EnviarRespuestaHolaLider.LeaderAnswerHiResult, RobotAgent>{

	public static enum LeaderAnswerHiResult {
		OK, SENT, ERROR
	}
	
	@Override
	public LeaderAnswerHiResult execute(RobotAgent rhlc, TeamNegotiationInfo taskParams) {
		String uuid=UUID.randomUUID().toString();
		SimpleTasksFactory.sendMessage(rhlc,new ImLeader(rhlc.getTeamName(), uuid));
		return null;
	}



}
