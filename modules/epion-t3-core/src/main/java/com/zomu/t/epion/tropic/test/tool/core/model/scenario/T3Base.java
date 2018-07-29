package com.zomu.t.epion.tropic.test.tool.core.model.scenario;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.zomu.t.epion.tropic.test.tool.core.execution.resolver.CommandTypeIdResolver;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class T3Base implements Serializable {

    private String t3 = "1.0";

    private String type;

    private Information info;

    private List<Flow> flows = new ArrayList<>();

    // visible属性を「true」にしないとパースした際に値が設定されないらしい
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "command", visible = true)
    @JsonTypeIdResolver(CommandTypeIdResolver.class)
    @Valid
    private List<com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process> processes = new ArrayList<>();

    private Variable variables;

    private Profile profiles;

    private Custom customs;

}
