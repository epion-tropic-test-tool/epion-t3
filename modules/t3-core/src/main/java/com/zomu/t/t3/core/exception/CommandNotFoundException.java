package com.zomu.t.t3.core.exception;

public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String commandId) {
        super("not found command: '" + commandId + "'");
    }
}
