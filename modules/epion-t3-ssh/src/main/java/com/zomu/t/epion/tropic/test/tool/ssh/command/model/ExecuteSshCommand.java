package com.zomu.t.epion.tropic.test.tool.ssh.command.model;

import com.zomu.t.epion.tropic.test.tool.core.annotation.CommandDefinition;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.command.runner.ExecuteSshCommandRunner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Getter
@Setter
@CommandDefinition(id = "ExecuteSshCommand", runner = ExecuteSshCommandRunner.class)
public class ExecuteSshCommand extends Command {

    private String waitFor;

    private String encoding;

    private Long timeout;

    private List<String> treatedAsErrors;

}
