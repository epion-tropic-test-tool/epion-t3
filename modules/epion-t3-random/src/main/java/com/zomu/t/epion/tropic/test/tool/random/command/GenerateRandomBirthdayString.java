package com.zomu.t.epion.tropic.test.tool.random.command;

import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import com.zomu.t.epion.tropic.test.tool.random.runner.GenerateRandomBirthdayStringRunner;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Command(id = "GenerateRandomBirthdayString", runner = GenerateRandomBirthdayStringRunner.class)
public class GenerateRandomBirthdayString extends Process {

    @NotEmpty
    private String format;

}
