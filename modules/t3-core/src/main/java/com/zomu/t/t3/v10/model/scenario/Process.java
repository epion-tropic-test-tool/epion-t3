package com.zomu.t.t3.v10.model.scenario;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Process implements Serializable {

    private String id;

    private String summary;

    private String description;

    private String command;

    private String target;

    private String value;

    private ProcessReference ref;

}
