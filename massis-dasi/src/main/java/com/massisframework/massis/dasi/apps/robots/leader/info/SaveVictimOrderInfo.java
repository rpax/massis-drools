package com.massisframework.massis.dasi.apps.robots.leader.info;

import com.massisframework.massis.dasi.apps.robots.RobotAgent;
import com.massisframework.massis.dasi.apps.robots.leader.VictimToSave;
import com.massisframework.massis.dasi.apps.robots.subordinate.SubordinateRobot;

public class SaveVictimOrderInfo {
	
	private VictimToSave victim;
	private SubordinateRobot subordinate;
	public SaveVictimOrderInfo(VictimToSave victim, SubordinateRobot subordinate) {
		super();
		this.victim = victim;
		this.subordinate = subordinate;
	}
	public VictimToSave getVictim() {
		return victim;
	}
	public void setVictim(VictimToSave victim) {
		this.victim = victim;
	}
	public SubordinateRobot getSubordinate() {
		return subordinate;
	}
	public void setSubordinate(SubordinateRobot subordinate) {
		this.subordinate = subordinate;
	}
	public RobotAgent getRobotVictim() {
		return victim.getVictim();
	}
	

}
