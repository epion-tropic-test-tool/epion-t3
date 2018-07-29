package com.zomu.t.epion.tropic.test.tool.core.execution.runner;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @param <PROCESS>
 */
public interface CommandRunner<PROCESS extends Process> {

    /**
     * @param process
     * @param globalScopeVariables
     * @param scenarioScopeVariables
     * @throws Exception
     */
    void execute(final PROCESS process,
                 final Map<String, Object> globalScopeVariables,
                 final Map<String, Object> scenarioScopeVariables,
                 final Logger logger) throws Exception;
}
