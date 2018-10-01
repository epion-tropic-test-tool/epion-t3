package com.zomu.t.epion.tropic.test.tool.random.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RandomMessages implements Messages {

    RANDOM_ERR_9001("com.zomu.t.epion.t3.random.err.9001"),

    RANDOM_ERR_9002("com.zomu.t.epion.t3.random.err.9002");


    private String messageCode;

}
