package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomStringRunner;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Command(id = "GenerateRandomString", runner = GenerateRandomStringRunner.class)
public class GenerateRandomString extends Process {

    @NotNull
    private Integer length;


}
