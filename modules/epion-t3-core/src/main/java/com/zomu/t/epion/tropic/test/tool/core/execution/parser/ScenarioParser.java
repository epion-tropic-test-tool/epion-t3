package com.zomu.t.epion.tropic.test.tool.core.execution.parser;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;

/**
 * @author takashno
 */
public interface ScenarioParser<C extends Context> {

    void parse(final C context);

}
