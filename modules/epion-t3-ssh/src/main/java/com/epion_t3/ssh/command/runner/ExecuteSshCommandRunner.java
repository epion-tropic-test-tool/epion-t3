package com.epion_t3.ssh.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.ssh.command.model.ExecuteSshCommand;
import org.slf4j.Logger;

public class ExecuteSshCommandRunner extends AbstractCommandRunner<ExecuteSshCommand> {
    @Override
    public CommandResult execute(ExecuteSshCommand command, Logger logger) throws Exception {
        return null;
    }
}
