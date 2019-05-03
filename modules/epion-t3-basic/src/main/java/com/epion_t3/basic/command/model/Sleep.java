package com.epion_t3.basic.command.model;

import com.epion_t3.basic.command.runner.SleepRunner;
import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
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
