package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.ConsoleInputRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@CommandDefinition(id = "ConsoleInput", runner = ConsoleInputRunner.class)
public class ConsoleInput extends Command {
}
