package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Element implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer order;

    private Boolean required;

    private String type;

    private String pattern;

    private List<Content> summary;

}
