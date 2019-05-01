package com.zomu.t.epion.tropic.test.tool.core.exception;

/**
 * メッセージが見つからない例外.
 *
 * @author takashno
 */
public class MessageNotFoundException extends SystemException {

    private String messageCode;

    public MessageNotFoundException(String messageCode) {
        super("'" + messageCode + "' is not found in message resource...");
    }


}
