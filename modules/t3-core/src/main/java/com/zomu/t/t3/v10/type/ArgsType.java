package com.zomu.t.t3.v10.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 実行引数.
 *
 * @author takashno
 */
@Getter
@AllArgsConstructor
public enum ArgsType {

    VERSION("v", "version", true, "run tool version.", true),

    SCENARIO("t", "target", true, "target of tool run.", true),

    ROOT_PATH("p", "path", true, "scenario root path.", true),

    MODE("m", "mode", true, "mode of tool run.", true),

    DEBUG("d", "debug", true, "run tool for debug.", false),

    HELP("h", "help", true, "show the tool help.", false);


    private String shortName;

    private String longName;

    private boolean hasArg;

    private String description;

    private boolean required;

}
