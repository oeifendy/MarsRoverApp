package com.test.marsrover.enums;

import com.test.marsrover.domain.PositionCoordinate;

public enum Direction {
	E("East", new PositionCoordinate(1, 0), new PositionCoordinate(-1, 0), "S", "N"),
	W("West", new PositionCoordinate(-1, 0), new PositionCoordinate(1, 0), "N", "S"),
	N("North", new PositionCoordinate(0, 1), new PositionCoordinate(0, -1), "E", "W"),
	S("South", new PositionCoordinate(0, -1), new PositionCoordinate(0, 1), "W", "E");
	
	private String direction;
	private PositionCoordinate forward;
	private PositionCoordinate backward;
	private String rotateClockwiseDirection;
	private String rotateAntiClockwiseDirection;
	private Direction(String direction, PositionCoordinate forward, PositionCoordinate backward, String rotateClockwiseDirection, String rotateAntiClockwiseDirection) {
		this.direction = direction;
		this.forward = forward;
		this.backward = backward;
		this.rotateClockwiseDirection = rotateClockwiseDirection;
		this.rotateAntiClockwiseDirection = rotateAntiClockwiseDirection;
	}

	public String getDirection() {
		return direction;
	}
	public PositionCoordinate getForward() {
		return forward;
	}
	public PositionCoordinate getBackward() {
		return backward;
	}
	public String getRotateClockwiseDirection() {
		return rotateClockwiseDirection;
	}
	public String getRotateAntiClockwiseDirection() {
		return rotateAntiClockwiseDirection;
	}
}
