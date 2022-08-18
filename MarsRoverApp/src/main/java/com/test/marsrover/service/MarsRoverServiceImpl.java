package com.test.marsrover.service;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.test.marsrover.cache.RoverCacheManager;
import com.test.marsrover.domain.PositionCoordinate;
import com.test.marsrover.domain.Rover;
import com.test.marsrover.enums.Command;
import com.test.marsrover.enums.Direction;
import com.test.marsrover.exception.MarsRoverException;
import com.test.marsrover.util.Constant;

@Component
public class MarsRoverServiceImpl implements MarsRoverService {
	
	@Override
	public Rover move(Rover rover, String commandsWithDelimiter) throws MarsRoverException {
		String[] commands = commandsWithDelimiter.split(Constant.DELIMITER);
		boolean isCollided = false;
		Rover prev = new Rover(rover);
		Rover current = new Rover(rover);
		try {
			for (String command : commands) {
				Command cmd = Command.fromValue(command);
				PositionCoordinate coordinate = current.getCoordinate();
				Direction direction = current.getDirection();
				int x = coordinate.getX();
				int y = coordinate.getY();
				switch (cmd) {
				case FORWARD:
					coordinate.setX(x + direction.getForward().getX());
					coordinate.setY(y + direction.getForward().getY());
					isCollided = isCollided(current);
					break;
				case BACKWARD:
					coordinate.setX(x + direction.getBackward().getX());
					coordinate.setY(y + direction.getBackward().getY());
					isCollided = isCollided(current);
					break;
				case ROTATE_CLOCKWISE:
					current.setDirection(Direction.valueOf(direction.getRotateClockwiseDirection()));
					break;
				case ROTATE_ANTI_CLOCKWISE:
					current.setDirection(Direction.valueOf(direction.getRotateAntiClockwiseDirection()));
					break;
				default:
					break;
				}
				if (isCollided) {
					break;
				} else {
					prev = new Rover(current);
				}
				Thread.sleep(1000);
			}
		} catch (Exception e) {
			throw new MarsRoverException("An error has occurred.", e); //simplified exception handling
		}
		return prev;
	}
	
	/*
	 * Check for rover collision
	 */
	private boolean isCollided(Rover rover) {
		Map<String, Rover> rovers = RoverCacheManager.getInstance().getAll();
		//no need to check for collision in case there is only one rovers
		if (rovers.size() == 1) {
			return false;
		}
			
		for (Map.Entry<String, Rover> entry : rovers.entrySet()) {
		    Rover cachedRover = entry.getValue();
		    //no need to check current rover
		    if (rover.getId() == cachedRover.getId()) {
		    	continue;
		    }
		    if (rover.getCoordinate().equals(cachedRover.getCoordinate())) {
		    	System.out.format("Collision has occurred between %s and %s at (%d,%d)%n", rover.getId(), cachedRover.getId(), rover.getCoordinate().getX(), rover.getCoordinate().getY());
		    	return true;
		    } else {
		    	RoverCacheManager.getInstance().put(rover.getId(), new Rover(rover));
		    }
		}
		return false;
	}
}
