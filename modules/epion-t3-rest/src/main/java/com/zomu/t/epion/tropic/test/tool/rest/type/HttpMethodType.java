package com.zomu.t.epion.tropic.test.tool.rest.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum HttpMethodType {

    GET("get"),

    POST("post"),

    PUT("put"),

    PATCH("patch"),

    DELETE("delete");

    private String value;

    public static HttpMethodType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }
}
