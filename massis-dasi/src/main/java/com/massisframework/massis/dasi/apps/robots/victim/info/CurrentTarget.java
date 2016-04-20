package com.massisframework.massis.dasi.apps.robots.victim.info;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.model.location.Location;

@PropertyReactive
public class CurrentTarget {

	private Location location;
	
	public CurrentTarget(Location location)
	{
		this.location = location;
	}
	
	public Location getLocation()
	{
		return location;
	}
	@Modifies({"location"})
	public void setLocation(Location location)
	{
		this.location = location;
	}
	
	
	
	
}
