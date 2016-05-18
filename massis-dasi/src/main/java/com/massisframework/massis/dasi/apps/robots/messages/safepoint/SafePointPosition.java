package com.massisframework.massis.dasi.apps.robots.messages.safepoint;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.model.location.Location;

public class SafePointPosition extends RobotMessageContent<Location> {

	public SafePointPosition(Location info, String uuid) {
		super(info, uuid);
	}

}
