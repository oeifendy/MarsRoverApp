package com.test.marsrover.domain;

import java.util.Objects;

public class PositionCoordinate {
	private int x;
	private int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public PositionCoordinate() {
		
	}
	public PositionCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionCoordinate other = (PositionCoordinate) obj;
		return x == other.x && y == other.y;
	}
}
