package com.zomu.t.epion.tropic.test.tool.core.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Nullable;
import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StructureType {

    STRING("string"),
    NUMBER("number"),
    BOOLEAN("boolean"),
    ARRAY("array"),
    OBJECT("object");


    private String value;

    @Nullable
    public static StructureType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }

}
