package com.zomu.t.epion.tropic.test.tool.basic.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ReferenceVariableType {

    GLOBAL("global"),

    SCENARIO("scenario"),

    FIX("fix");

    private String name;

    public static ReferenceVariableType valueOfByName(String name) {
        return Arrays.stream(values()).filter(x -> x.getName().equals(name)).findFirst().orElse(null);
    }
}
