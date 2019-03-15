package com.zomu.t.epion.tropic.test.tool.log.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LogMessages implements Messages {

    LOG_ERR_9001("com.zomu.t.epion.t3.log.err.9001"),
    LOG_ERR_9002("com.zomu.t.epion.t3.log.err.9002"),
    LOG_ERR_9003("com.zomu.t.epion.t3.log.err.9003"),
    LOG_ERR_9004("com.zomu.t.epion.t3.log.err.9004"),;

    private String messageCode;
}
