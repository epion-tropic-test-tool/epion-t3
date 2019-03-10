package com.zomu.t.epion.tropic.test.tool.rest.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RestMessages implements Messages {

    REST_ERR_9001("com.zomu.t.epion.t3.rest.err.9001"),
    REST_ERR_9002("com.zomu.t.epion.t3.rest.err.9002"),
    REST_ERR_9003("com.zomu.t.epion.t3.rest.err.9003"),
    REST_INFO_1001("com.zomu.t.epion.t3.rest.info.1001"),
    ;


    private String messageCode;

}
