package com.massisframework.massis.dasi.lowlevel;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

@PropertyReactive
public class SimTick {

	private long tick;

	public SimTick(long tick)
	{
		this.tick = tick;
	}

	public long getTick()
	{
		return tick;
	}

	@Modifies({ "tick" })
	public void setTick(long tick)
	{
		this.tick = tick;
	}
	@Modifies({ "tick" })
	public void incTick(long inc)
	{
		this.tick+=inc;
	}
}
