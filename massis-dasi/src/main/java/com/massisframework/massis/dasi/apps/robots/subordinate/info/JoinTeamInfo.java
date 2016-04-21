package com.massisframework.massis.dasi.apps.robots.subordinate.info;

import com.massisframework.massis.dasi.RuleHighLevelController;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.TeamNegotiationInfo;
import com.massisframework.massis.dasi.apps.robots.messages.teamnegotiation.TeamNegotiationInfo.NegotiationStatus;

public class JoinTeamInfo {


	private TeamNegotiationInfo teamInfo;
	private RuleHighLevelController sender;
	private String uuid;
	private String teamName;
	
	public JoinTeamInfo(TeamNegotiationInfo teamInfo, RuleHighLevelController sender, String uuid) {
		super();
		this.teamInfo = teamInfo;
		this.sender = sender;
		this.uuid = uuid;
	}
	public JoinTeamInfo(TeamNegotiationInfo teamInfo, Object sender, String uuid) {
		super();
		this.teamInfo = teamInfo;
		this.sender = (RuleHighLevelController)sender;
		this.uuid = uuid;
	}
	public JoinTeamInfo(TeamNegotiationInfo teamInfo, RuleHighLevelController sender, String uuid, String teamName) {
		super();
		this.teamInfo = teamInfo;
		this.sender = sender;
		this.uuid = uuid;
		this.teamName = teamName;
	}
	public JoinTeamInfo(TeamNegotiationInfo teamInfo, Object sender, String uuid, Object teamName) {
		super();
		this.teamInfo = teamInfo;
		this.sender = (RuleHighLevelController)sender;
		this.uuid = uuid;
		this.teamName = (String)teamName;
	}
	public TeamNegotiationInfo getTeamInfo() {
		return teamInfo;
	}
	public void setTeamInfo(TeamNegotiationInfo teamInfo) {
		this.teamInfo = teamInfo;
	}
	public RuleHighLevelController getSender() {
		return sender;
	}
	public void setSender(RuleHighLevelController sender) {
		this.sender = sender;
	}
	public void setSender(Object sender) {
		this.sender = (RuleHighLevelController)sender;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setTeamStatusInfo(NegotiationStatus status) {
		this.teamInfo.setStatus(status);
	}
	
	public void setTeamPeerInfo(Object peer) {
		this.teamInfo.setPeer(peer);
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
}
