package com.zomu.t.epion.tropic.test.tool.basic.messages;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum BasicMessages implements Messages {

    /**  */
    BASIC_ERR_9001("com.zomu.t.epion.t3.basic.err.9001"),

    BASIC_ERR_9002("com.zomu.t.epion.t3.basic.err.9002"),

    BASIC_INF_0001("com.zomu.t.epion.t3.basic.inf.0001"),

    ;

    private String messageCode;
}
