package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.RemoveVariableRunner;
import com.zomu.t.epion.tropic.test.tool.basic.command.runner.SetVariableRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CommandDefinition(id = "RemoveVariable", runner = RemoveVariableRunner.class)
public class RemoveVariable extends Command {
}
