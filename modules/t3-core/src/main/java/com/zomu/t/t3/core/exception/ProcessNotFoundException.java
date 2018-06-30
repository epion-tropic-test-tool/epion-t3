package com.zomu.t.t3.core.exception;

public class ProcessNotFoundException extends RuntimeException {

    private String messageCode;

    public ProcessNotFoundException(String processId) {
        super("'" + processId + "' is not found in scenario...");
    }
}
