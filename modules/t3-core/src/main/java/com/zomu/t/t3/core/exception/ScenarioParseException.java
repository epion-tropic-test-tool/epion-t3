package com.zomu.t.t3.core.exception;

import com.zomu.t.t3.core.exception.bean.ScenarioParseError;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ScenarioParseException extends RuntimeException {

    @Getter
    private List<ScenarioParseError> errors;

    public ScenarioParseException(List<ScenarioParseError> errors) {
        super("Scenario Parse Error Occurred.");
        this.errors = errors;
    }

    public ScenarioParseException(ScenarioParseError error) {
        super("Scenario Parse Error Occurred.");
        errors = new ArrayList<>();
        errors.add(error);
    }


}
