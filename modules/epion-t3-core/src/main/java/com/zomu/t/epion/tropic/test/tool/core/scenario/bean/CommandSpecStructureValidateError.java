package com.zomu.t.epion.tropic.test.tool.core.scenario.bean;

import com.zomu.t.epion.tropic.test.tool.core.common.type.NotificationType;
import com.zomu.t.epion.tropic.test.tool.core.common.type.StageType;
import lombok.Builder;
import lombok.Getter;

/**
 *
 */
@Getter
public class CommandSpecStructureValidateError extends CommandSpecValidateError {

    /**
     * 要素名
     */
    private final String structureName;

    /**
     * コンストラクタ.
     */
    @Builder(builderMethodName = "commandSpecStructureValidateErrorBuilder")
    public CommandSpecStructureValidateError(StageType stage, NotificationType level, String message, Throwable error, String customName, String structureName) {
        super(stage, level, message, error, customName);
        this.structureName = structureName;
    }
}
