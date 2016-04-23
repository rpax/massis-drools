package com.massisframework.massis.dasi.apps.robots.leader.info;

import java.util.LinkedList;
import java.util.Queue;

public class PendingVictims {

	private Queue<VictimToSave> pendingVictims;
	
	public PendingVictims(){
		this.pendingVictims = new LinkedList<VictimToSave>();
	}
	
	public void addPendingVictim(VictimToSave victim){
		this.pendingVictims.add(victim);
	}
	
	public VictimToSave consumePendingVictim(){
		return this.pendingVictims.remove();
	}
	
	public VictimToSave getFirstPendingVictim(){
		return this.pendingVictims.peek();
	}
	
	public boolean isThereAPendingVictim(){
		return this.pendingVictims.size() > 0;
	}
	
	
}
