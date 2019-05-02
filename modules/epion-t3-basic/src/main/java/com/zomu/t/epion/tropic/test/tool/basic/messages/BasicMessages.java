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

    BASIC_ERR_9001("com.epion_t3.basic.err.9001"),
    BASIC_ERR_9002("com.epion_t3.basic.err.9002"),
    BASIC_ERR_9003("com.epion_t3.basic.err.9003"),
    BASIC_ERR_9004("com.epion_t3.basic.err.9004"),
    BASIC_ERR_9005("com.epion_t3.basic.err.9005"),
    BASIC_ERR_9006("com.epion_t3.basic.err.9006"),
    BASIC_ERR_9007("com.epion_t3.basic.err.9007"),
    BASIC_ERR_9008("com.epion_t3.basic.err.9008"),
    BASIC_ERR_9009("com.epion_t3.basic.err.9009"),
    BASIC_ERR_9010("com.epion_t3.basic.err.9010"),
    BASIC_ERR_9011("com.epion_t3.basic.err.9011"),
    BASIC_INF_0001("com.epion_t3.basic.inf.0001"),

    ;

    private String messageCode;
}
