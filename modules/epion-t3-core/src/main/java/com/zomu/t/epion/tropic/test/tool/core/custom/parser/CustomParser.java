package com.zomu.t.epion.tropic.test.tool.core.custom.parser;

import com.zomu.t.epion.tropic.test.tool.core.common.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;

/**
 * カスタム機能解析インタフェース.
 *
 * @author takashno
 */
public interface CustomParser<C extends Context, E extends ExecuteContext> {

    /**
     * @param context
     */
    void parse(final C context, final E executeContext);


}
