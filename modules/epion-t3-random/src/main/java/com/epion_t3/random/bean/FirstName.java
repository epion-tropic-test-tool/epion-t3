package com.epion_t3.random.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FirstName {

    @JsonProperty(value = "male")
    private List<List<String>> male;

    @JsonProperty(value = "female")
    private List<List<String>> female;
}
