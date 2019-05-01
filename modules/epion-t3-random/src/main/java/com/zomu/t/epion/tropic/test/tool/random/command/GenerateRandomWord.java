package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomWordRunner;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@CommandDefinition(id = "GenerateRandomWord", runner = GenerateRandomWordRunner.class)
public class GenerateRandomWord extends Command {

    @NotNull
    private Integer length;


}
