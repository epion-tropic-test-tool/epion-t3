package com.epion_t3.ssh.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.ssh.command.model.EndSsh;
import org.slf4j.Logger;

public class EndSshRunner extends AbstractCommandRunner<EndSsh> {
    @Override
    public CommandResult execute(EndSsh command, Logger logger) throws Exception {
        return null;
    }
}
