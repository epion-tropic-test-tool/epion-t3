package com.zomu.t.t3.core.exception;

import com.zomu.t.t3.core.message.MessageManager;

/**
 * @author takashno
 */
public class SystemException extends RuntimeException {

    public SystemException(Throwable t, String messageCode) {
        super(MessageManager.getInstance().getMessage(messageCode), t);
    }

    public SystemException(Throwable t, String messageCode, Object... objects) {
        super(MessageManager.getInstance().getMessage(messageCode, objects), t);
    }

    public SystemException(Throwable t) {
        super(t);
    }

    public SystemException(String messageCode) {
        super(MessageManager.getInstance().getMessageWithCode(messageCode));
    }

    public SystemException(String messageCode, Object... objects) {
        super(MessageManager.getInstance().getMessageWithCode(messageCode, objects));
    }

}
