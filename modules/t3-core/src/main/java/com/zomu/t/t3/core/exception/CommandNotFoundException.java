package com.zomu.t.t3.core.exception;

/**
 * コマンドが見つからない例外.
 *
 * @author takashno
 */
public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String commandId) {
        super("not found command: '" + commandId + "'");
    }
}
