package com.zomu.t.epion.tropic.test.tool.rdb.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OperationType {

    INSERT("insert"),

    CLEAN_INSERT("clean_insert"),

    DELETE("delete"),

    DELETE_ALL("delete_all"),

    UPDATE("update"),

    TRUNCATE_TABLE("truncate_table"),

    REFRESH("refresh"),

    NONE("none");

    /**
     * 値.
     */
    private String value;

    /**
     * 値から列挙子を取得.
     *
     * @param value
     * @return
     */
    public static OperationType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }
}
