package com.zomu.t.epion.tropic.test.tool.core.context;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Message implements Serializable {

    private String id;

    private String content;
}
