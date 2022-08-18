package com.test.marsrover.domain;

import com.test.marsrover.enums.Direction;

public class Rover {
	private String id;
	private PositionCoordinate coordinate;
	private Direction direction;
	public Rover() {
		
	}
	public Rover(Rover rover) {
		this.id = rover.getId();
		this.coordinate = new PositionCoordinate(rover.getCoordinate().getX(), rover.getCoordinate().getY());
		this.direction = rover.getDirection();
	}
	public Rover(String id, PositionCoordinate coordinate, Direction direction) {
		this.id = id;
		this.coordinate = coordinate;
		this.direction = direction;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PositionCoordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(PositionCoordinate coordinate) {
		this.coordinate = coordinate;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
