package com.massisframework.massis.dasi.agents.robots.worker.info;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.model.agents.LowLevelAgent;
@PropertyReactive
public class WorkerInfo {
	
	private LowLevelAgent followTarget;

	public LowLevelAgent getFollowTarget() {
		return followTarget;
	}
	@Modifies({"followTarget"})
	public void setFollowTarget(LowLevelAgent followTarget) {
		this.followTarget = followTarget;
	}

}
