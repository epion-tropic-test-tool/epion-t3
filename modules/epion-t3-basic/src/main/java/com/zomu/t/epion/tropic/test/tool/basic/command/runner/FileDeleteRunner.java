package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.FileDelete;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 *
 */
public class FileDeleteRunner extends AbstractCommandRunner<FileDelete> {
    @Override
    public CommandResult execute(final FileDelete command,
                                 final Logger logger) throws Exception {

        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9003);
        }

        Files.delete(Paths.get(command.getTarget()));
        return CommandResult.getSuccess();
    }
}
