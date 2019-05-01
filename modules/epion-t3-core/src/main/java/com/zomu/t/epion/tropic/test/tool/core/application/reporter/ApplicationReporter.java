package com.zomu.t.epion.tropic.test.tool.core.application.reporter;

import com.zomu.t.epion.tropic.test.tool.core.common.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;

/**
 * アプリケーションレポート出力インタフェース.
 *
 * @param <EXECUTE_CONTEXT>
 * @author takashno
 */
public interface ApplicationReporter<EXECUTE_CONTEXT extends ExecuteContext> {

    /**
     * アプリケーションレポート出力.
     *
     * @param context        コンテキスト
     * @param executeContext 実行情報
     */
    void report(Context context,
                EXECUTE_CONTEXT executeContext);
}
