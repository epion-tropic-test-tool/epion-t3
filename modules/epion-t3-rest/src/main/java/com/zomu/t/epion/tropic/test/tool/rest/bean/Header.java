package com.zomu.t.epion.tropic.test.tool.rest.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Header implements Serializable {

    private String name;

    private String value;

}
