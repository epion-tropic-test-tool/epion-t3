package com.zomu.t.t3.core.exception;

public class MessageNotFoundException extends RuntimeException {

    private String messageCode;

    public MessageNotFoundException(String messageCode) {
        super("'" + messageCode + "' is not found in message resource...");
    }


}
