package com.zomu.t.epion.tropic.test.tool.core.message.impl;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum CoreMessages implements Messages {

    /**  */
    CORE_ERR_0001("com.zomu.t.epion.t3.core.err.0001"),
    /**  */
    CORE_ERR_0002("com.zomu.t.epion.t3.core.err.0002"),
    /**  */
    CORE_ERR_0003("com.zomu.t.epion.t3.core.err.0003"),
    /**  */
    CORE_ERR_0004("com.zomu.t.epion.t3.core.err.0004"),
    /**  */
    CORE_WRN_0001("com.zomu.t.epion.t3.core.wrn.0001"),

    ;

    private String messageCode;
}
