package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String id;

    @NotEmpty
    private List<Content> summary;

    @NotEmpty
    private List<TestItem> testItem = new ArrayList<>();

    @NotEmpty
    private List<Function> function = new ArrayList<>();

    @NotEmpty
    private List<Structure> structure = new ArrayList<>();

}
