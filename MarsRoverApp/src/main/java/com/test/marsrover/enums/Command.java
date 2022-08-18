package com.test.marsrover.enums;

import java.util.HashMap;
import java.util.Map;

public enum Command {
	FORWARD("f"),
	BACKWARD("b"),
	ROTATE_CLOCKWISE("r"),
	ROTATE_ANTI_CLOCKWISE("l");
	
	private static final Map<String, Command> commandMap = new HashMap<String, Command>();
	static {
        for (Command cmd : Command.values()) {
        	commandMap.put(cmd.getCommand(), cmd);
        }
    }
	
	public final String command;
	Command(String command) {
		this.command = command;
	}
	public String getCommand() {
		return command;
	}
	
	public static Command fromValue(String command) {
        return commandMap.get(command);
    }
}
