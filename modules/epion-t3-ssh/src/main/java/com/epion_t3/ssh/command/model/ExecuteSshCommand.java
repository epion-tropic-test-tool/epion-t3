package com.epion_t3.ssh.command.model;

import com.epion_t3.core.common.annotation.CommandDefinition;
import com.epion_t3.core.common.bean.scenario.Command;
import com.epion_t3.ssh.command.runner.ExecuteSshCommandRunner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 */
@Getter
@Setter
//@CommandDefinition(id = "ExecuteSshCommand", runner = ExecuteSshCommandRunner.class)
public class ExecuteSshCommand extends Command {

    private String waitFor;

    private String encoding;

    private Long timeout;

    private List<String> treatedAsErrors;

}
