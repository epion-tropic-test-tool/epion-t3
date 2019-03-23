package com.zomu.t.epion.tropic.test.tool.selenium.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SeleniumMessages implements Messages {

    SELENIUM_ERR_9001("com.zomu.t.epion.t3.selenium.err.9001"),
    SELENIUM_ERR_9004("com.zomu.t.epion.t3.selenium.err.9004"),
    SELENIUM_ERR_9005("com.zomu.t.epion.t3.selenium.err.9005"),
    SELENIUM_ERR_9006("com.zomu.t.epion.t3.selenium.err.9006"),
    SELENIUM_ERR_9007("com.zomu.t.epion.t3.selenium.err.9007"),
    SELENIUM_ERR_9008("com.zomu.t.epion.t3.selenium.err.9008"),
    SELENIUM_ERR_9009("com.zomu.t.epion.t3.selenium.err.9009"),
    SELENIUM_ERR_9010("com.zomu.t.epion.t3.selenium.err.9010"),
    /* 警告 */
    SELENIUM_WRN_2001("com.zomu.t.epion.t3.selenium.wrn.2001"),
    ;

    private String messageCode;

}
