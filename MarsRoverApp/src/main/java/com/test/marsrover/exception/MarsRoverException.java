package com.test.marsrover.exception;

/*
 * This is a simplified exception
 * 
 */
public class MarsRoverException extends Exception {
	private static final long serialVersionUID = -2430699975735567417L;
	
	public MarsRoverException() {
		
	}
	
	public MarsRoverException(String message) {
		super(message);
		//add logging
	}
	public MarsRoverException(Throwable e) {
		super(e);
		//add logging
	}
	public MarsRoverException(String message, Throwable e) {
		super(message, e);
	}
}
