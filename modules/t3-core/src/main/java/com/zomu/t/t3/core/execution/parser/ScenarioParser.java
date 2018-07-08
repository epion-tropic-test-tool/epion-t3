package com.zomu.t.t3.core.execution.parser;

import com.zomu.t.t3.core.context.Context;

/**
 * @author takashno
 */
public interface ScenarioParser<C extends Context> {

    void parse(final C context);

}
