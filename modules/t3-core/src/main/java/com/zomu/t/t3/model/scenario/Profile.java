package com.zomu.t.t3.model.scenario;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class Profile implements Serializable {

    private Map<String, Map<String, String>> values;
}
