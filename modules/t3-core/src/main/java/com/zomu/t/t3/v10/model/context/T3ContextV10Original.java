package com.zomu.t.t3.v10.model.context;

import com.zomu.t.t3.v10.model.scenario.Custom;
import com.zomu.t.t3.v10.model.scenario.T3Base;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Getter
@Setter
public class T3ContextV10Original {

    private Custom custom;

    private final Map<String, T3Base> scenarios = new ConcurrentHashMap<>();

}
