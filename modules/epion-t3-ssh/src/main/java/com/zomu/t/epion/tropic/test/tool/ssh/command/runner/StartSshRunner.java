package com.zomu.t.epion.tropic.test.tool.ssh.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.ssh.command.model.StartSsh;
import com.zomu.t.epion.tropic.test.tool.ssh.configuration.model.SshConnectionConfiguration;
import org.slf4j.Logger;

public class StartSshRunner extends AbstractCommandRunner<StartSsh> {
    @Override
    public CommandResult execute(StartSsh command, Logger logger) throws Exception {

        SshConnectionConfiguration sshConnectionConfiguration =
                referConfiguration(command.getSshConnectConfigRef());




        return null;
    }
}
