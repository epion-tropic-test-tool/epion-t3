package com.zomu.t.epion.tropic.test.tool.basic.command.model;

import com.zomu.t.epion.tropic.test.tool.basic.command.runner.LogExtractDuringTimeRunner;
import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import lombok.Getter;
import lombok.Setter;
import org.apache.bval.constraints.NotEmpty;

/**
 *
 */
@Getter
@Setter
@CommandDefinition(id = "LogExtractDuringTime", runner = LogExtractDuringTimeRunner.class)
public class LogExtractDuringTime extends Command {

    @NotEmpty
    private String flowRef;

    @NotEmpty
    private String start;

    @NotEmpty
    private String end;

}
