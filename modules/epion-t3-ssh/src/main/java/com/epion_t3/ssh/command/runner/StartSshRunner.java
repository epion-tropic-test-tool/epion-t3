package com.epion_t3.ssh.command.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.ssh.command.model.StartSsh;
import com.epion_t3.ssh.configuration.model.SshConnectionConfiguration;
import org.slf4j.Logger;

public class StartSshRunner extends AbstractCommandRunner<StartSsh> {
    @Override
    public CommandResult execute(StartSsh command, Logger logger) throws Exception {

        SshConnectionConfiguration sshConnectionConfiguration =
                referConfiguration(command.getSshConnectConfigRef());




        return null;
    }
}
