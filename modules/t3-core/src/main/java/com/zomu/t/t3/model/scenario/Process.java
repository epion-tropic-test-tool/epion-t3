package com.zomu.t.t3.model.scenario;

import lombok.*;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Process implements Serializable {

    @NotNull
    private String id;

    private String summary;

    private String description;

    @NotNull
    private String command;

    private String target;

    private String value;

    private ProcessReference ref;

}
