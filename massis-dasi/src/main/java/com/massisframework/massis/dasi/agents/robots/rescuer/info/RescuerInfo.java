package com.massisframework.massis.dasi.agents.robots.rescuer.info;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.model.building.SimRoom;
@PropertyReactive
public class RescuerInfo {

	private Set<SimRoom> roomsUnexplored;
	private SimRoom assignedRoom;
	private boolean workFinished;

	public RescuerInfo(Collection<SimRoom> roomsUnexplored) {
		this.roomsUnexplored = new HashSet<>(roomsUnexplored);
		this.workFinished = false;
		this.assignedRoom = null;

	}

	@Modifies({ "assignedRoom","roomsUnexplored" })
	public void assignRoom(SimRoom assignedRoom) {
		this.assignedRoom = assignedRoom;
		this.roomsUnexplored.remove(this.assignedRoom);
	}
	@Modifies({ "roomsUnexplored" })
	public void setRoomsUnexplored(Set<SimRoom> roomsUnexplored) {
		this.roomsUnexplored = roomsUnexplored;
	}

	@Modifies({ "assignedRoom" })
	public void setAssignedRoom(SimRoom assignedRoom) {
		this.assignedRoom = assignedRoom;
	}

	@Modifies({ "workFinished" })
	public void setWorkFinished(boolean workFinished) {
		this.workFinished = workFinished;
	}
	
	public SimRoom getRandomAvailableRoom() {
		SimRoom[] rem = this.getRoomsUnexplored().toArray(new SimRoom[] {});
		SimRoom ar = rem[ThreadLocalRandom.current().nextInt(rem.length)];
		return ar;
	}
	public Set<SimRoom> getRoomsUnexplored() {
		return roomsUnexplored;
	}

	public SimRoom getAssignedRoom() {
		return assignedRoom;
	}

	public int getRemainingRoomsToExplore() {
		return this.roomsUnexplored.size();
	}

	public boolean isWorkFinished() {
		return workFinished;
	}

}
