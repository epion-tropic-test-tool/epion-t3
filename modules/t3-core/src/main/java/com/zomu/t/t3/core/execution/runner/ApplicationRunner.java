package com.zomu.t.t3.core.execution.runner;

import com.zomu.t.t3.core.context.Context;

public interface ApplicationRunner<C extends Context> {

    void execute(String[] args);

    void handleGlobalException(final C context, final Throwable t);


}
