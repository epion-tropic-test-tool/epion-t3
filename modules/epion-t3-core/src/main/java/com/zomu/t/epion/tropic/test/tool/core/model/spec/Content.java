package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private String lang;

    private String contents;

}
