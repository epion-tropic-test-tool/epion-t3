package com.zomu.t.t3.basic.command.runner;

import com.zomu.t.t3.basic.command.model.FileCopy;
import com.zomu.t.t3.core.execution.runner.CommandRunner;

import java.util.Map;

public class FileCopyRunner implements CommandRunner<FileCopy> {

    @Override
    public void execute(final FileCopy process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables) throws Exception {

    }

}
