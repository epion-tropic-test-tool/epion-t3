package com.zomu.t.t3.model.scenario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class ProcessReference implements Serializable{

    private String id;

    private Map<String, Object> args;

    private Map<String, Object> results;


}

