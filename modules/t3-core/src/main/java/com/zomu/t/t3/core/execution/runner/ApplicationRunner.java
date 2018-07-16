package com.zomu.t.t3.core.execution.runner;

import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.util.ExecutionFileUtils;

public interface ApplicationRunner<C extends Context> {

    int execute(String[] args);

    void handleGlobalException(final C context, final Throwable t);

}
