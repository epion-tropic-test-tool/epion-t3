package com.zomu.t.epion.tropic.test.tool.core.scenario.bean;

import com.zomu.t.epion.tropic.test.tool.core.common.bean.ET3Notification;
import com.zomu.t.epion.tropic.test.tool.core.common.type.NotificationType;
import com.zomu.t.epion.tropic.test.tool.core.common.type.StageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommandSpecValidateError extends ET3Notification {

    private final String customName;

    /**
     * コンストラクタ.
     */
    @Builder(builderMethodName = "csommandSpecValidateErrorBuilder")
    public CommandSpecValidateError(StageType stage, NotificationType level, String message, Throwable error, String customName) {
        super(stage, level, message, error);
        this.customName = customName;
    }
}
