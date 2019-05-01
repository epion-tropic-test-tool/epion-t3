package com.zomu.t.epion.tropic.test.tool.ssh.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.ssh.command.model.EndSsh;
import org.slf4j.Logger;

public class EndSshRunner extends AbstractCommandRunner<EndSsh> {
    @Override
    public CommandResult execute(EndSsh command, Logger logger) throws Exception {
        return null;
    }
}
