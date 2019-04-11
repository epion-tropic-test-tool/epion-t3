package com.zomu.t.epion.tropic.test.tool.core.model.spec;

import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ET3Spec implements Serializable {

    /**
     * バージョン.
     */
    private String et3 = "1.0";

    @NotNull
    private Information info;

    @NotEmpty
    @Valid
    private List<Command> commands;


}
