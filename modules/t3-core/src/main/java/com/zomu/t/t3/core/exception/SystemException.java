package com.zomu.t.t3.core.exception;

public class SystemException extends RuntimeException {

    public SystemException(String message, Throwable t) {
        super(message, t);
    }

    public SystemException(Throwable t) {
        super(t);
    }

    public SystemException(String message) {
        super(message);
    }

}
