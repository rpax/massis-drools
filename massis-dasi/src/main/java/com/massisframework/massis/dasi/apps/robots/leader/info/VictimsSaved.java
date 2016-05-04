package com.massisframework.massis.dasi.apps.robots.leader.info;

import org.kie.api.definition.type.Modifies;

public class VictimsSaved {

	private int numSaved;

	public VictimsSaved(){
		this.numSaved = 0;
	}
	
	public int getNumSaved() {
		return numSaved;
	}

	@Modifies({ "numSaved" })
	public void setNumSaved(int numSaved) {
		this.numSaved = numSaved;
	}
	
	@Modifies({ "numSaved" })
	public void increaseNumSaved() {
		this.numSaved++;
	}
	
	
}
