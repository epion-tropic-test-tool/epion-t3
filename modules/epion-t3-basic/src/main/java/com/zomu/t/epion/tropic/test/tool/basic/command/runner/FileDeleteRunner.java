package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import org.slf4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class FileDeleteRunner implements CommandRunner<Process> {
    @Override
    public void execute(final Process process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables,
                        final Map<String, EvidenceInfo> evidences,
                        final Logger logger) throws Exception {

        logger.info("start FileDelete");
        Files.delete(Paths.get(process.getTarget()));
        logger.info("end FileDelete");


    }
}
