package com.zomu.t.epion.tropic.test.tool.selenium.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BrowserType {

    FIREFOX("firefox"),

    CHROME("chrome"),

    IE("ie"),

    PHANTOMJS("phantomjs"),;


    private String value;

    public static BrowserType valueOfByValue(final String value) {
        return Arrays.stream(values()).filter(x -> x.value.equals(value)).findFirst().orElse(null);
    }
}
