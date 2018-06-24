package com.zomu.t.t3.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum Args {

    VERSION("v", "version", true, "run tool version.", true);

    /**
     * 短いオプション名.
     */
    private String shortName;

    /**
     * 長いオプション名.
     */
    private String longName;

    /**
     * 引数の値をとるかどうかの指定.
     */
    private boolean hasArg;

    /**
     * 説明.
     */
    private String description;

    /**
     * 引数が必須かどうかの指定.
     */
    private boolean required;

}
