package com.zomu.t.t3.basic.command.runner;

import com.zomu.t.t3.core.execution.resolver.CommandResolver;
import com.zomu.t.t3.core.execution.runner.CommandRunner;
import com.zomu.t.t3.model.scenario.Process;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileDeleteRunner implements CommandRunner<Process> {
    @Override
    public void execute(final Process process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables,
                        final Logger logger) throws Exception {

        logger.info("start FileDelete");
        Files.delete(Paths.get(process.getTarget()));
        logger.info("end FileDelete");


    }
}
