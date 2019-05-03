package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.SetVariableRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "SetVariable", runner = SetVariableRunner.class)
public class SetVariable extends Command {
}
