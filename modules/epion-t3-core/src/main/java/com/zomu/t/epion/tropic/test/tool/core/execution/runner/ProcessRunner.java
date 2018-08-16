package com.zomu.t.epion.tropic.test.tool.core.execution.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

/**
 * @param <CONTEXT>
 * @param <SCENARIO>
 * @param <PROCESS>
 */
public interface ProcessRunner<CONTEXT extends Context, SCENARIO extends ExecuteScenario, PROCESS extends ExecuteCommand> {

    /**
     * @param context
     * @param executeScenario
     * @param executeProcess
     */
    void execute(CONTEXT context, SCENARIO executeScenario, PROCESS executeProcess);
}
