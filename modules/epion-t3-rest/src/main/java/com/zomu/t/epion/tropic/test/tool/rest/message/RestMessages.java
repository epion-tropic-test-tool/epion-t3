package com.zomu.t.epion.tropic.test.tool.rest.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ETTTのRest系メッセージ.
 */
@Getter
@AllArgsConstructor
public enum RestMessages implements Messages {

    REST_ERR_9001("com.zomu.t.epion.t3.rest.err.9001"),
    REST_ERR_9002("com.zomu.t.epion.t3.rest.err.9002"),
    REST_ERR_9003("com.zomu.t.epion.t3.rest.err.9003"),
    REST_ERR_9004("com.zomu.t.epion.t3.rest.err.9004"),
    REST_ERR_9005("com.zomu.t.epion.t3.rest.err.9005"),
    REST_ERR_9006("com.zomu.t.epion.t3.rest.err.9006"),
    REST_ERR_9007("com.zomu.t.epion.t3.rest.err.9007"),
    REST_ERR_9008("com.zomu.t.epion.t3.rest.err.9008"),
    REST_ERR_9009("com.zomu.t.epion.t3.rest.err.9009"),
    REST_ERR_9010("com.zomu.t.epion.t3.rest.err.9010"),
    REST_ERR_9011("com.zomu.t.epion.t3.rest.err.9011"),
    REST_INFO_1001("com.zomu.t.epion.t3.rest.info.1001"),;


    private String messageCode;

}
