package com.test.marsrover.util;

import java.util.Random;

import com.test.marsrover.domain.PositionCoordinate;
import com.test.marsrover.domain.Rover;
import com.test.marsrover.enums.Command;
import com.test.marsrover.enums.Direction;

public class Utils {
	
	public static Rover getRoverFromArgument(String id, String initialPositionWithDelimiter) {
		String[] initialPosition = initialPositionWithDelimiter.split(Constant.DELIMITER);
		int x = Integer.parseInt(initialPosition[0]);
		int y = Integer.parseInt(initialPosition[1]);
		String currentDirection = initialPosition[2];
		Direction dir = Direction.valueOf(currentDirection);
		return new Rover(id, new PositionCoordinate(x, y), dir);
		
	}
	
	public static boolean isInteger(String str) {
		try {
		  Integer.parseInt(str);
		  // is an integer!
		  return true;
		} catch (NumberFormatException e) {
			// not an integer!
			return false;
		}
	}
	
	public static String generateRandomPosition() {
		Random random = new Random();
		int x = random.nextInt(0, 9); //being hardcoded between 0 - 9
		int y = random.nextInt(0, 9); //being hardcoded between 0 - 9
		Direction direction = randomEnum(Direction.class);
		return String.format("%d,%d,%s", x, y, direction.name());
	}
	
	public static String generateRandomCommands() {
		Random random = new Random();
		int numOfCommands = random.nextInt(1, 10); //being hardcoded to have number of commands between 1 and 10
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<numOfCommands; i++) {
			if (i==0) {
				sb.append(randomEnum(Command.class).getCommand());
			}
			else {
				sb.append(String.format("%s%s", Constant.DELIMITER, randomEnum(Command.class).getCommand()));
			}
		}
		return sb.toString();
	}
	
	public static int generateRandomNumberOfThreads() {
		Random random = new Random();
		return random.nextInt(1, 9); //being hardcoded between 1 - 9
	}
	
	public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Random random = new Random();
		int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
