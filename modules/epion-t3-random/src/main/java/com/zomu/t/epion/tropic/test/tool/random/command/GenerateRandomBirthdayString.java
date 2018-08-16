package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomBirthdayStringRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@com.zomu.t.epion.tropic.test.tool.core.annotation.Command(id = "GenerateRandomBirthdayString", runner = GenerateRandomBirthdayStringRunner.class)
public class GenerateRandomBirthdayString extends Command {

    @NotEmpty
    private String format;

}
