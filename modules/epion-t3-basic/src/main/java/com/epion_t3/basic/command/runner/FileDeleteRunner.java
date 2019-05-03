package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.FileDelete;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

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
