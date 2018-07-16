package com.zomu.t.t3.core.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum CoreMessages implements Messages {

    /**  */
    CORE_ERR_9001("com.zomu.t.t3.core.err.9001"),
    /**  */
    CORE_ERR_9002("com.zomu.t.t3.core.err.9002"),
    /**  */
    CORE_ERR_9003("com.zomu.t.t3.core.err.9003"),



    ;

    private String messageCode;
}
