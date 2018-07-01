package com.zomu.t.t3.core.execution.runner;

import com.zomu.t.t3.core.context.Context;

/**
 * @param <C>
 */
public interface ScenarioRunner<C extends Context> {


    /**
     * @param context
     */
    void execute(C context);

}
