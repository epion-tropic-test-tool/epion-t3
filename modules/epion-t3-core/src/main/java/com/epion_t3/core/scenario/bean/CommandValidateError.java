package com.epion_t3.core.scenario.bean;

import com.epion_t3.core.common.bean.ET3Notification;
import com.epion_t3.core.common.type.NotificationType;
import com.epion_t3.core.common.type.StageType;
import lombok.Builder;

public class CommandValidateError extends ET3Notification {

    private String commandId;

    /**
     * コンストラクタ.
     */
    @Builder(builderMethodName = "commandValidateErrorBuilder")
    public CommandValidateError(StageType stage, NotificationType level, String message, Throwable error, String commandId) {
        super(stage, level, message, error);
        this.commandId = commandId;
    }
}
