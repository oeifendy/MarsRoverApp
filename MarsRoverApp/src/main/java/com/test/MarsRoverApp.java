package com.test;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.marsrover.cache.RoverCacheManager;
import com.test.marsrover.domain.Rover;
import com.test.marsrover.exception.MarsRoverException;
import com.test.marsrover.service.MarsRoverService;
import com.test.marsrover.util.Utils;

@SpringBootApplication
public class MarsRoverApp implements CommandLineRunner {

	@Autowired
	MarsRoverService marsRoverService;
	
	Map<String, Rover> myCache = Collections.synchronizedMap(new WeakHashMap<String, Rover>());

	
	public static void main(String[] args) {
		SpringApplication.run(MarsRoverApp.class, args);
	}
	
	/*
	 * Assumptions: 
	 * - each rover will have random number of commands
	 * - each rover will wait for 1sec before executing next command
	 * - new rover will be spawned 1sec after previous rover
	 * - for testing purpose the arguments position, commands and number of rovers are generated randomly
	 * Notes:
	 * - no unit test created
	 */
	public void run(String... args) throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
		//START - for testing purpose
		String position = args.length > 0 ? args[0] : "3,4,N"; //hardcoded for testing only
		String commands = args.length > 1 ? args[1]  : "f,f,r,f,f"; //hardcoded for testing only
		int threadCount = args.length > 2 && Utils.isInteger(args[2]) ? Integer.parseInt(args[2]) : 1; //default to 1 thread
		boolean useRandom = args.length < 2 || threadCount > 1;
		if (useRandom) {
			System.out.println("Running using random data");
			threadCount = Utils.generateRandomNumberOfThreads();
		} else {
			System.out.println("Running using arguments" + position + ":" + commands);
		}
		//END - for testing purpose
		System.out.format("Running %d rovers%n", threadCount);
		//create new threads based on the number of thread count
		for (int i = 1; i < threadCount + 1; i++) {
			final String id = Integer.toString(i);
			final String newPosition = useRandom ? Utils.generateRandomPosition() : position;
			final String newCommands = useRandom ? Utils.generateRandomCommands() : commands;
			executor.execute(new Runnable() {
				@Override
				public void run() {
					Rover rover = new Rover();
					try {
						Rover initialRover = Utils.getRoverFromArgument(id, newPosition);
						RoverCacheManager.getInstance().put(id, initialRover);
						rover = marsRoverService.move(initialRover, newCommands);
						Thread.sleep(1000);
					} catch (InterruptedException | MarsRoverException e) {
						System.out.println("An error has occurred."); //simplied error handling
					}
					System.out.format("Final Coordinate %s: %d, %d\nFinal Direction %s: %s%n", rover.getId(), rover.getCoordinate().getX(), rover.getCoordinate().getY(), rover.getId(), rover.getDirection().getDirection());
				}
			});
		}
    }
}
