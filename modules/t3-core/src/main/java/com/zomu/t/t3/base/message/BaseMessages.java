package com.zomu.t.t3.base.message;

import com.zomu.t.t3.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseMessages implements Messages {

    /** 共通システムエラー */
    BASE_ERR_9001("com.zomu.t.t3.base.err.9001"),

    BASE_ERR_9002("com.zomu.t.t3.base.err.9002"),

    BASE_ERR_9003("com.zomu.t.t3.base.err.9003"),

    BASE_ERR_9004("com.zomu.t.t3.base.err.9004"),

    BASE_ERR_9005("com.zomu.t.t3.base.err.9005"),;

    private String messageCode;
}
