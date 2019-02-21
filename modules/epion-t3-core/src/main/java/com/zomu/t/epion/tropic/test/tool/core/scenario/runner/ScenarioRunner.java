package com.zomu.t.epion.tropic.test.tool.core.scenario.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;

/**
 *
 * @param <CONTEXT>
 * @param <EXECUTE_CONTEXT>
 */
public interface ScenarioRunner<CONTEXT extends Context, EXECUTE_CONTEXT extends ExecuteContext> {


    /**
     * @param context
     */
    void execute(CONTEXT context, EXECUTE_CONTEXT executeContext);

}
