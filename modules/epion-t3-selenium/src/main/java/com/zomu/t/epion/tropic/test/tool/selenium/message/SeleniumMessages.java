package com.zomu.t.epion.tropic.test.tool.selenium.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeleniumMessages implements Messages {

    SELENIUM_ERR_9001("com.zomu.t.t3.epion.selenium.err.9001");

    private String messageCode;

}
