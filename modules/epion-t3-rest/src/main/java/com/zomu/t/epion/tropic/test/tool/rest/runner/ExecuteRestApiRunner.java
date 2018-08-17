package com.zomu.t.epion.tropic.test.tool.rest.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.rest.command.ExecuteRestApi;
import org.slf4j.Logger;

import java.util.Map;

public class ExecuteRestApiRunner implements CommandRunner<ExecuteRestApi> {

    @Override
    public void execute(
            final ExecuteRestApi process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {


    }
}
