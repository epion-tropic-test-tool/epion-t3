package com.zomu.t.epion.tropic.test.tool.core.exception.handler;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;

public interface ExceptionHandler<C extends Context> {

    void handle(final C context, final Throwable t);

}
