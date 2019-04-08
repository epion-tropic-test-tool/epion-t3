package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ET3Spec implements Serializable {

    /**
     * バージョン.
     */
    private String et3 = "1.0";

    private Information info;


}
