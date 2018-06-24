package com.zomu.t.t3.v10.model.scenario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Flow implements Serializable {

    private int order = 0;

    private String ref;

}
