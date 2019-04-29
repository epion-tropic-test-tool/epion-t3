package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class TestItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer order;

    private List<Content> summary;

}
