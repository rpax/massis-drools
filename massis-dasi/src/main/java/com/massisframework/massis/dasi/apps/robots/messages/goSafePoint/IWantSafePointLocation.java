package com.massisframework.massis.dasi.apps.robots.messages.goSafePoint;

import com.massisframework.massis.dasi.apps.robots.messages.RobotMessageContent;
import com.massisframework.massis.model.location.Location;

public class IWantSafePointLocation extends RobotMessageContent<Location> {

	public IWantSafePointLocation(Location info, String uuid) {
		super(info, uuid);
	}

}
