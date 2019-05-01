package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomBirthdayStringRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "GenerateRandomBirthdayString", runner = GenerateRandomBirthdayStringRunner.class)
public class GenerateRandomBirthdayString extends Command {

    @NotEmpty
    private String format;

}
