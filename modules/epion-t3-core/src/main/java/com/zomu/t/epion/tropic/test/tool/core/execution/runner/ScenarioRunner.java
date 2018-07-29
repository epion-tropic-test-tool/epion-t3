package com.zomu.t.epion.tropic.test.tool.core.execution.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;

/**
 * @param <C>
 */
public interface ScenarioRunner<C extends Context> {


    /**
     * @param context
     */
    void execute(C context);

}
