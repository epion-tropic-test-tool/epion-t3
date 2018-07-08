package com.zomu.t.t3.core.exception.handler;

import com.zomu.t.t3.core.context.Context;

public interface ExceptionHandler<C extends Context> {

    void handle(final C context, final Throwable t);

}
