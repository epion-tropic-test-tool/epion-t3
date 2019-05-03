package com.epion_t3.random.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.random.runner.GenerateRandomStringRunner;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@CommandDefinition(id = "GenerateRandomString", runner = GenerateRandomStringRunner.class)
public class GenerateRandomString extends Command {

    @NotNull
    private Integer length;


}
