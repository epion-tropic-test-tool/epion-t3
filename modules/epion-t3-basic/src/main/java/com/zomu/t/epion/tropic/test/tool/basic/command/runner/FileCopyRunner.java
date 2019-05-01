package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.FileCopy;
import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import org.slf4j.Logger;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class FileCopyRunner extends AbstractCommandRunner<FileCopy> {

    /**
     * @param process
     * @param logger
     * @throws Exception
     */
    @Override
    public CommandResult execute(final FileCopy process,
                                 final Logger logger) throws Exception {
        Path from = Paths.get(process.getFrom());
        try (OutputStream os = new FileOutputStream(process.getTo())) {
            Files.copy(from, os);
        }
        return CommandResult.getSuccess();
    }

}
