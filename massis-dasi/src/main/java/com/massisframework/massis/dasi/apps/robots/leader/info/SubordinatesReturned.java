package com.massisframework.massis.dasi.apps.robots.leader.info;

import org.kie.api.definition.type.Modifies;

public class SubordinatesReturned {

	private int numReturned;

	public SubordinatesReturned(){
		this.numReturned = 0;
	}
	
	public int getNumReturned() {
		return numReturned;
	}

	@Modifies({ "numReturned" })
	public void setNumReturned(int numReturned) {
		this.numReturned = numReturned;
	}
	
	@Modifies({ "numReturned" })
	public void increaseNumReturned() {
		this.numReturned++;
	}
	
}
