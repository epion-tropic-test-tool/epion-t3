package com.zomu.t.t3.basic.command.runner;

import com.zomu.t.t3.core.execution.resolver.CommandResolver;
import com.zomu.t.t3.core.execution.runner.CommandRunner;
import com.zomu.t.t3.model.scenario.Process;

import java.util.Map;

public class FileDeleteRunner implements CommandRunner<Process> {
    @Override
    public void execute(final Process process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables) throws Exception {

    }
}
