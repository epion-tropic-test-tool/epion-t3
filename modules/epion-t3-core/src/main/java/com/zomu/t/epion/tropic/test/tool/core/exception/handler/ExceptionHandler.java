package com.zomu.t.epion.tropic.test.tool.core.exception.handler;

import com.zomu.t.epion.tropic.test.tool.core.common.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;

public interface ExceptionHandler<C extends Context, E extends ExecuteContext> {

    void handle(final C context,final E executeContext, final Throwable t);

}
