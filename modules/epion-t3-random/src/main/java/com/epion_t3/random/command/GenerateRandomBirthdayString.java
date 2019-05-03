package com.epion_t3.random.command;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.random.runner.GenerateRandomBirthdayStringRunner;
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
