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



    ;

    private String messageCode;
}
