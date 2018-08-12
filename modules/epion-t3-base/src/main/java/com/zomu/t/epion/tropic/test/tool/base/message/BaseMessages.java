package com.zomu.t.epion.tropic.test.tool.base.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum BaseMessages implements Messages {

    /**
     * 共通システムエラー
     */
    BASE_ERR_0001("com.zomu.t.epion.t3.base.err.0001"),

    /**
     *
     */
    BASE_ERR_0002("com.zomu.t.epion.t3.base.err.0002"),

    /**
     *
     */
    BASE_ERR_0003("com.zomu.t.epion.t3.base.err.0003"),

    /**
     *
     */
    BASE_ERR_0004("com.zomu.t.epion.t3.base.err.0004"),

    /**
     *
     */
    BASE_ERR_0005("com.zomu.t.epion.t3.base.err.0005"),

    /**
     *
     */
    BASE_WRN_0001("com.zomu.t.epion.t3.base.wrn.0001"),

    /**
     *
     */
    BASE_ERR_1001("com.zomu.t.epion.t3.base.err.1001"),

    /**
     *
     */
    BASE_ERR_1002("com.zomu.t.epion.t3.base.err.1002"),;

    private String messageCode;
}
