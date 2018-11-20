package com.zomu.t.epion.tropic.test.tool.selenium.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeleniumMessages implements Messages {

    SELENIUM_ERR_9001("com.zomu.t.t3.epion.selenium.err.9001"),

    SELENIUM_ERR_9004("com.zomu.t.t3.epion.selenium.err.9004"),

    SELENIUM_ERR_9005("com.zomu.t.t3.epion.selenium.err.9005");

    private String messageCode;

}
