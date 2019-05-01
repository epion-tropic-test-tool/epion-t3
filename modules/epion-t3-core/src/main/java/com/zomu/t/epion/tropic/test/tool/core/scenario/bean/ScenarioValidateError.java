package com.zomu.t.epion.tropic.test.tool.core.scenario.bean;

import com.zomu.t.epion.tropic.test.tool.core.common.type.NotificationType;
import com.zomu.t.epion.tropic.test.tool.core.common.type.StageType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ScenarioValidateError extends ScenarioParseError {

    private final String target;

    private final Object value;

    /**
     * コンストラクタ.
     */
    @Builder(builderMethodName = "scenarioValidateErrorBuilder")
    public ScenarioValidateError(
            StageType stage, NotificationType level, String message, Throwable error,
            String filePath, String target, Object value) {
        super(stage, level, message, error, filePath);
        this.target = target;
        this.value = value;
    }

}
