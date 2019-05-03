package com.epion_t3.random.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JapaneseNameData {

    @JsonProperty(value = "first_name")
    private FirstName firstName;

    @JsonProperty(value = "last_name")
    private List<List<String>> lastName;

}
