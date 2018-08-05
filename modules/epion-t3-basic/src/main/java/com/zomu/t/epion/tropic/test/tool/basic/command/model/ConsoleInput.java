package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.ConsoleInputRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@Command(id = "ConsoleInput", runner = ConsoleInputRunner.class)
public class ConsoleInput extends Process {
}
