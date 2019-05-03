package com.zomu.t.epion.tropic.test.tool.core.exception;

import lombok.Getter;

/**
 * コマンドが見つからない例外.
 *
 * @author takashno
 */
@Getter
public class CommandNotFoundException extends SystemException {

    private String commandId;

    public CommandNotFoundException(String commandId) {
        this.commandId = commandId;
    }
}
