package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.SleepRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * Sleepコマンド.
 *
 * @author takashno
 */
@Getter
@Setter
@CommandDefinition(id = "Sleep", runner = SleepRunner.class)
public class Sleep extends Command {
}
