package com.massisframework.massis.dasi.apps.robots.messages.goSafePoint;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.model.location.Location;

public class SafePointReached extends RobotMessageContent<Location> {

	public SafePointReached(Location info, String uuid) {
		super(info, uuid);
	}
}
