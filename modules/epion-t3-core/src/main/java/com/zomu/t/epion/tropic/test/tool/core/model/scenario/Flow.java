package com.zomu.t.epion.tropic.test.tool.core.model.scenario;

import com.zomu.t.epion.tropic.test.tool.core.annotation.OriginalFlowField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    @OriginalFlowField
    private String type;

}
