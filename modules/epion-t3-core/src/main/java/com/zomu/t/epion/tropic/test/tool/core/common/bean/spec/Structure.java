package com.zomu.t.epion.tropic.test.tool.core.common.bean.spec;

import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class Structure implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer order;

    @NotEmpty
    private String name;

    @NotNull
    private Boolean required;

    @NotEmpty
    private String type;

    private String pattern;

    @NotEmpty
    @Valid
    private List<Content> summary;

    private List<Content> description;

}
