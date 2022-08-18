package com.test.marsrover.service;

import com.test.marsrover.domain.Rover;
import com.test.marsrover.exception.MarsRoverException;

public interface MarsRoverService {
	Rover move(Rover rover, String commands) throws MarsRoverException;
}
