package com.zomu.t.epion.tropic.test.tool.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum FlowType {

    SCENARIO("scenario"),

    PROCESS("process"),

    CONTROL("control");

    private String value;

    public static FlowType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }
}

