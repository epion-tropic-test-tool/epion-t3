package com.zomu.t.epion.tropic.test.tool.core.scenario.runner;

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
