package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.FileGet;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileGetRunner extends AbstractCommandRunner<FileGet> {

    @Override
    public CommandResult execute(FileGet command, Logger logger) throws Exception {

        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }

        Path from = Paths.get(command.getTarget());

        if (Files.notExists(from)) {
            throw new SystemException(BasicMessages.BASIC_ERR_9007, from.toString());
        }

        Path to = getEvidencePath(from.getFileName().toString());

        Files.copy(from, to);

        registrationFileEvidence(to);

        return CommandResult.getSuccess();

    }
}
