package com.zomu.t.t3.v10.model.execute;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class ExecuteContextV10 implements Serializable {

    List<ExecuteFlow> flows = new ArrayList<>();

    Map<String, Object> globalVariables = new ConcurrentHashMap<>();

}
