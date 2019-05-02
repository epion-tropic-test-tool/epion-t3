package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.DirectoryCreate;
import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
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
