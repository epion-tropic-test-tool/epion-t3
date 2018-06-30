package com.zomu.t.t3.core.runner;

import com.zomu.t.t3.core.model.context.Context;
import com.zomu.t.t3.v10.model.context.ExecuteContextV10;

/**
 * @param <C>
 */
public interface ScenarioRunner<C extends Context> {


    /**
     * @param context
     */
    void execute(C context);

}
