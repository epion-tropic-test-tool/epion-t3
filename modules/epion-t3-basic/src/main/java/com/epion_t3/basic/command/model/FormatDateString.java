package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.FormatDateStringRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

@Getter
@Setter
@CommandDefinition(id = "FormatDateString", runner = FormatDateStringRunner.class)
public class FormatDateString extends Command {

    @NotEmpty
    private String formattedTarget;
}
