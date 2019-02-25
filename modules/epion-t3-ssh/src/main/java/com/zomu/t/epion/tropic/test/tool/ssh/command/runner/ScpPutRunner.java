package com.zomu.t.epion.tropic.test.tool.ssh.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.ssh.command.model.ScpPut;
import org.slf4j.Logger;

import java.util.Map;

public class ScpPutRunner extends AbstractCommandRunner<ScpPut> {

    @Override
    public CommandResult execute(final ScpPut command, final Logger logger) throws Exception {
        return CommandResult.getSuccess();
    }
}
