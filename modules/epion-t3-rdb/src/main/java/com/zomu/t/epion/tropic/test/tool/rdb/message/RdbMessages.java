package com.zomu.t.epion.tropic.test.tool.rdb.message;

import com.zomu.t.epion.tropic.test.tool.core.message.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RdbMessages implements Messages {

    RDB_ERR_0001("com.zomu.t.epion.t3.rdb.err.0001"),
    RDB_ERR_0002("com.zomu.t.epion.t3.rdb.err.0002"),
    RDB_ERR_0003("com.zomu.t.epion.t3.rdb.err.0003"),
    RDB_ERR_0004("com.zomu.t.epion.t3.rdb.err.0004"),
    RDB_ERR_0005("com.zomu.t.epion.t3.rdb.err.0005"),
    RDB_ERR_0006("com.zomu.t.epion.t3.rdb.err.0006"),
    RDB_ERR_0007("com.zomu.t.epion.t3.rdb.err.0007"),
    RDB_ERR_0008("com.zomu.t.epion.t3.rdb.err.0008"),;


    private String messageCode;
}
