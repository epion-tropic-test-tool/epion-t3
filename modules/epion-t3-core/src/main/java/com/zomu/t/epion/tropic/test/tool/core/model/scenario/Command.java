package com.zomu.t.epion.tropic.test.tool.core.model.scenario;

import com.zomu.t.epion.tropic.test.tool.core.annotation.OriginalProcessField;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class Command implements Serializable {

    private static final long serialVersionUID = 1L;

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
