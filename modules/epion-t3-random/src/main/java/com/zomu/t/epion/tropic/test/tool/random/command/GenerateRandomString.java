package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomStringRunner;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "GenerateRandomString", runner = GenerateRandomStringRunner.class)
public class GenerateRandomString extends Command {

    @NotNull
    private Integer length;


}
