package com.massisframework.massis.dasi.apps.robots.leader.info;

import org.kie.api.definition.type.Modifies;

public class VictimsDiscovered {

	private int numDiscovered;

	public VictimsDiscovered(){
		this.numDiscovered = 0;
	}
	
	public int getNumDiscovered() {
		return numDiscovered;
	}

	@Modifies({ "numDiscovered" })
	public void setNumDiscovered(int numDiscovered) {
		this.numDiscovered = numDiscovered;
	}
	
	@Modifies({ "numDiscovered" })
	public void increaseNumDiscovered() {
		this.numDiscovered++;
	}
	
}
