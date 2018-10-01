package com.zomu.t.epion.tropic.test.tool.rest.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum RestMessages implements Messages {

    REST_ERR_9001("com.zomu.t.epion.t3.rest.err.9001");


    private String messageCode;

}
