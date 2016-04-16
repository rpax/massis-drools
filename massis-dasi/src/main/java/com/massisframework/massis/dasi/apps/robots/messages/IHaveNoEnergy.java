package com.massisframework.massis.dasi.apps.robots.messages;

import com.massisframework.massis.model.location.Location;

public class IHaveNoEnergy extends RobotMessageContent<Location> {

	public IHaveNoEnergy(Location info, String uuid)
	{
		super(info, uuid);
	}

	

}
