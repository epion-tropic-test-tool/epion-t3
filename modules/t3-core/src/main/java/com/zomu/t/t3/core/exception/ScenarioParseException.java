package com.zomu.t.t3.core.exception;

public class ScenarioParseException extends RuntimeException {

    public ScenarioParseException(String message, Throwable t) {
        super(message, t);
    }

    public ScenarioParseException(Throwable t) {
        super(t);
    }

    public ScenarioParseException(String message) {
        super(message);
    }

}
