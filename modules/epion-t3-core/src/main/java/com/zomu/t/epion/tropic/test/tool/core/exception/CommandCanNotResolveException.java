package com.zomu.t.epion.tropic.test.tool.core.exception;

import lombok.Getter;

@Getter
public class CommandCanNotResolveException extends SystemException {

    private String commandId;

    public CommandCanNotResolveException(String commandId) {
        super("can't resolve command: '" + commandId + "'");
        this.commandId = commandId;
    }

}
