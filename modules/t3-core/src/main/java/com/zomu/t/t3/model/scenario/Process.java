package com.zomu.t.t3.model.scenario;

import com.zomu.t.t3.core.annotation.OriginalProcessField;
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
    @OriginalProcessField
    private String id;

    @OriginalProcessField
    private String summary;

    @OriginalProcessField
    private String description;

    @NotNull
    @OriginalProcessField
    private String command;

    @OriginalProcessField
    private String target;

    @OriginalProcessField
    private String value;

    @OriginalProcessField
    private ProcessReference ref;

}
