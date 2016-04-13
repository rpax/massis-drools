package com.massisframework.massis.dasi.agents.common.info;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.kie.api.definition.type.Modifies;
import org.kie.api.definition.type.PropertyReactive;

import com.massisframework.massis.model.agents.LowLevelAgent;
import com.massisframework.massis.model.building.SimRoom;
import com.massisframework.massis.model.location.Location;

import straightedge.geom.KPoint;

@PropertyReactive
public class LowLevelInfo {

	private final Set<LowLevelAgent> agentsInVisionRadio;
	private final Set<LowLevelAgent> agentsInRoom;
	private final Collection<SimRoom> roomsInFloor;
	private Point2D acceleration;
	private double bodyRadius;
	private Location location;
	private SimRoom currentRoom;
	private LowLevelAgent lowLevelAgent;
	private long tick;
	private final int lowLevelId;

	public LowLevelInfo(LowLevelAgent lla) {
		this.lowLevelAgent = lla;
		this.lowLevelId = lla.getID();
		this.agentsInVisionRadio = new HashSet<>();
		lla.getAgentsInVisionRadio().forEach(this.agentsInVisionRadio::add);

		this.agentsInRoom = new HashSet<>(lla.getAgentsInRoom());
		this.setAcceleration(lla.getAcceleration().x, lla.getAcceleration().y);

		this.bodyRadius = lla.getBodyRadius();
		this.location = lla.getLocation();
		this.roomsInFloor = new ArrayList<>(
				lla.getLocation().getFloor().getRooms());

	}

	public void updateInfo() {
		shouldUpdateLocation();
		shouldUpdateAgentsVisionRadio();
		shouldUpdateAcceleration();
		shouldUpdateAgentsInRoom();
		shouldUpdateBodyRadius();
		shouldUpdateCurrentRoom();
		shouldUpdateRoomsInFloor();
	}

	/*
	 * Update methods. Check for changes, and if changes exist, set the new
	 * values
	 */
	public boolean shouldUpdateAcceleration() {
		return (!pointEquals(lowLevelAgent.getAcceleration(),
				this.acceleration));

	}

	public boolean shouldUpdateAgentsVisionRadio() {
		// TODO optimizes
		List<LowLevelAgent> newAgentsVision = new ArrayList<>();
		stream(lowLevelAgent.getAgentsInVisionRadio())
				.forEach(newAgentsVision::add);
		return (newAgentsVision.size() != agentsInVisionRadio.size()
				|| !agentsInVisionRadio.containsAll(newAgentsVision));

	}

	public LowLevelAgent getLowLevelAgent() {
		return lowLevelAgent;
	}

	public boolean shouldUpdateAgentsInRoom() {
		// TODO optimize
		List<LowLevelAgent> newAgentsRoom = new ArrayList<>();
		stream(lowLevelAgent.getAgentsInRoom()).forEach(newAgentsRoom::add);
		return (newAgentsRoom.size() != agentsInRoom.size()
				|| !agentsInRoom.containsAll(newAgentsRoom));
	}

	public boolean shouldUpdateBodyRadius() {
		return (lowLevelAgent.getBodyRadius() != this.bodyRadius);
	}

	public boolean shouldUpdateLocation() {
		return (!this.location.equals(lowLevelAgent.getLocation()));
		// this.setLocation(lla.getLocation());
	}

	public boolean shouldUpdateCurrentRoom() {
		return (lowLevelAgent.getRoom() != this.getCurrentRoom());
	}

	public boolean shouldUpdateRoomsInFloor() {
		return (lowLevelAgent.getLocation().getFloor() != this.getLocation()
				.getFloor()
				|| this.getRoomsInFloor().isEmpty());
	}

	/*
	 * Modifiers
	 */
	@Modifies({ "agentsInVisionRadio" })
	public void setAgentsInVisionRadio(
			Iterable<? extends LowLevelAgent> agents) {
		this.agentsInVisionRadio.clear();
		agents.forEach(this.agentsInVisionRadio::add);
	}

	@Modifies({ "acceleration" })
	public void setAcceleration(double x, double y) {
		if (this.acceleration == null)
			this.acceleration = new Point2D.Double();
		this.acceleration.setLocation(x, y);
	}

	@Modifies({ "acceleration" })
	public void setAcceleration(Point2D acceleration) {
		this.acceleration = acceleration;
	}

	@Modifies({ "agentsInRoom" })
	public void setAgentsInRoom(Iterable<? extends LowLevelAgent> agents) {
		this.agentsInRoom.clear();
		agents.forEach(this.agentsInRoom::add);
	}

	@Modifies({ "bodyRadius" })
	public void setBodyRadius(double br) {
		this.bodyRadius = br;

	}

	@Modifies({ "location" })
	public void setLocation(Location l) {
		this.location = l;
	}

	@Modifies({ "currentRoom" })
	public void setCurrentRoom(SimRoom currentRoom) {
		this.currentRoom = currentRoom;
	}

	@Modifies({ "roomsInFloor" })
	public void setRoomsInFloor(Collection<SimRoom> rooms) {
		this.roomsInFloor.clear();
		this.roomsInFloor.addAll(rooms);
	}

	@Modifies({ "tick" })
	public void setTick(long tick) {
		this.tick = tick;
	}

	/*
	 * Getters
	 */
	public SimRoom getCurrentRoom() {
		return this.currentRoom;
	}

	public Point2D getAcceleration() {
		return acceleration;
	}

	public Set<LowLevelAgent> getAgentsInVisionRadio() {
		return agentsInVisionRadio;
	}

	public Set<LowLevelAgent> getAgentsInRoom() {
		return agentsInRoom;
	}

	public Collection<SimRoom> getRoomsInFloor() {
		return roomsInFloor;
	}

	public double getBodyRadius() {
		return bodyRadius;
	}

	public Location getLocation() {
		return location;
	}

	public long getTick() {
		return tick;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lowLevelId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LowLevelInfo other = (LowLevelInfo) obj;
		if (lowLevelId != other.lowLevelId)
			return false;
		return true;
	}

	/*
	 * Utility methods
	 */
	@SuppressWarnings("unused")
	private static boolean pointEquals(Point2D p1, KPoint p2) {
		return p1.getX() == p1.getX() && p1.getY() == p2.getY();
	}

	@SuppressWarnings("unused")
	private static boolean pointEquals(Point2D p1, Point2D p2) {
		return p1.getX() == p1.getX() && p1.getY() == p2.getY();
	}

	private static boolean pointEquals(KPoint p1, Point2D p2) {
		return p1.getX() == p1.getX() && p1.getY() == p2.getY();
	}

	private static <T> Stream<T> stream(Iterable<T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}

	private static <T> Stream<T> stream(Collection<T> iterable) {
		return iterable.stream();
	}

}
