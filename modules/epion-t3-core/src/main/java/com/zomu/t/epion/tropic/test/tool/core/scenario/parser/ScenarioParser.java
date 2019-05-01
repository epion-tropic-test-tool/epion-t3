package com.zomu.t.epion.tropic.test.tool.core.scenario.parser;

import com.zomu.t.epion.tropic.test.tool.core.common.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;

/**
 * @author takashno
 */
public interface ScenarioParser<C extends Context, E extends ExecuteContext> {

    /**
     * @param context コンテキスト
     */
    void parse(final C context, final E executeContext);

}
