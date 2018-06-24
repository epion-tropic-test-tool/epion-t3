package com.zomu.t.t3.core.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgsType {

    VERSION("v", "version", true, "run tool version.", true);

    private String shortName;

    private String longName;

    private boolean hasArg;

    private String description;

    private boolean required;

}
