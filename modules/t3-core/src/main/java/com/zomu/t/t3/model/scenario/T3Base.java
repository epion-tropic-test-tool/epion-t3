package com.zomu.t.t3.model.scenario;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import com.zomu.t.t3.core.execution.scenario.resolver.CommandTypeIdResolver;
import lombok.Getter;
import lombok.Setter;

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

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "command")
    @JsonTypeIdResolver(CommandTypeIdResolver.class)
    private List<Process> processes = new ArrayList<>();

    private Variable variables;

    private Profile profiles;

    private Custom customs;

}
