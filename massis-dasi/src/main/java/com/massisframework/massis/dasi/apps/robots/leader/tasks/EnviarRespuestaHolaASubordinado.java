package com.massisframework.massis.dasi.apps.robots.leader.tasks;

import com.massisframework.massis.dasi.agents.tasks.AgentTask;
import com.massisframework.massis.dasi.agents.tasks.SimpleTasksFactory;
import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.leader.info.HelloAnswerInfo;
import com.massisframework.massis.dasi.apps.robots.leader.info.SaveVictimOrderInfo;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.YouArePartOfMyTeam;

public class EnviarRespuestaHolaASubordinado implements AgentTask<HelloAnswerInfo, EnviarRespuestaHolaASubordinado.SendHelloAnswerSubordinateResult, RobotAgent>{

	public static enum SendHelloAnswerSubordinateResult {
		OK, SENT, ERROR
	}

	@Override
	public SendHelloAnswerSubordinateResult execute(
			RobotAgent rhlc, HelloAnswerInfo taskParams) {
		
		SimpleTasksFactory.sendMessage(rhlc,new YouArePartOfMyTeam("1", taskParams.getUuid()));
	 	rhlc.addTeamMember((RobotAgent)taskParams.getSender());
		
		return null;
	}
}
