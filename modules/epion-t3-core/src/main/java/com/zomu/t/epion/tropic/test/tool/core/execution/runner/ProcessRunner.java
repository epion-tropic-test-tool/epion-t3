package com.zomu.t.epion.tropic.test.tool.core.execution.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteProcess;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

/**
 * @param <CONTEXT>
 * @param <SCENARIO>
 * @param <PROCESS>
 */
public interface ProcessRunner<CONTEXT extends Context, SCENARIO extends ExecuteScenario, PROCESS extends ExecuteProcess> {

    /**
     * @param context
     * @param executeScenario
     * @param executeProcess
     */
    void execute(CONTEXT context, SCENARIO executeScenario, PROCESS executeProcess);
}
