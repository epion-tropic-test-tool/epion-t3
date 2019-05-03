package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.RemoveVariableRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "RemoveVariable", runner = RemoveVariableRunner.class)
public class RemoveVariable extends Command {
}
