package com.zomu.t.t3.model.scenario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class Custom implements Serializable {

    private final Map<String, String> packages = new ConcurrentHashMap<>();

}
