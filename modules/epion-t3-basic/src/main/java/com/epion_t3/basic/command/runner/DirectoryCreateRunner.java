package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.DirectoryCreate;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DirectoryCreateRunner extends AbstractCommandRunner<DirectoryCreate> {
    @Override
    public CommandResult execute(DirectoryCreate command, Logger logger) throws Exception {
        Files.createDirectories(Paths.get(command.getTarget()));
        return CommandResult.getSuccess();
    }
}
