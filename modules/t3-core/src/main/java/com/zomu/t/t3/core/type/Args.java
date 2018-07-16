package com.zomu.t.t3.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 */
@Getter
@AllArgsConstructor
public enum Args {

    VERSION("v", "version", true, "run tool version.", true),

    SCENARIO("t", "target", true, "target of tool run.", true),

    ROOT_PATH("r", "rootpath", true, "scenario root path.", true),

    RESULT_ROOT_PATH("o", "output", true, "scenario output root path.", false),

    FILESYSTEM_KIND("f", "filesystem", true, "scenario manage filesystem kind.", false),

    PROFILE("p", "profile", true, "profile of tool run.", false),

    MODE("m", "mode", true, "mode of tool run.", true),

    DEBUG("d", "debug", false, "run tool for debug.", false),

    HELP("h", "help", true, "show the tool help.", false);

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
