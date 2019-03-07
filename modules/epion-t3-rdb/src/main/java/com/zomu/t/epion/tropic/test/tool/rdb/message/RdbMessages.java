package com.zomu.t.epion.tropic.test.tool.rdb.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RdbMessages implements Messages {

    RDB_ERR_0001("com.zomu.t.epion.t3.rdb.err.0001"),
    RDB_ERR_0002("com.zomu.t.epion.t3.rdb.err.0002"),;


    private String messageCode;
}
