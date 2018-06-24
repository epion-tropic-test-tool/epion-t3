package com.zomu.t.t3.core.exception;

public class T3ScenarioParseException extends RuntimeException {

    public T3ScenarioParseException(String message, Throwable t) {
        super(message, t);
    }

    public T3ScenarioParseException(Throwable t) {
        super(t);
    }

    public T3ScenarioParseException(String message) {
        super(message);
    }

}
