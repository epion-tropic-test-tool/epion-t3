package com.zomu.t.epion.tropic.test.tool.core.application.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;

public interface ApplicationRunner<C extends Context> {

    int execute(String[] args);

    void handleGlobalException(final C context, final Throwable t);

}
