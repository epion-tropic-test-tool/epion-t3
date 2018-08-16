package com.zomu.t.epion.tropic.test.tool.core.execution.resolver.impl;

import com.zomu.t.epion.tropic.test.tool.core.execution.resolver.FlowRunnerResolver;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.BaseMessages;
import com.zomu.t.epion.tropic.test.tool.core.context.FlowInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.FlowNotFoundException;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.flow.runner.FlowRunner;
import com.zomu.t.epion.tropic.test.tool.core.holder.CustomConfigHolder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author takashno
 */
public final class BaseFlowRunnerResolver implements FlowRunnerResolver {

    /**
     * インスタンス.
     */
    private static final BaseFlowRunnerResolver instance = new BaseFlowRunnerResolver();

    /**
     * プライベートコンストラクタ.
     */
    private BaseFlowRunnerResolver() {
        // Do Nothing...
    }

    /**
     * インスタンス取得.
     *
     * @return
     */
    public static BaseFlowRunnerResolver getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @param type
     * @return
     */
    @Override
    public FlowRunner getFlowRunner(String type) {

        if (StringUtils.isEmpty(type)) {
            // 不正
            throw new SystemException(BaseMessages.BASE_ERR_0001);
        }

        FlowInfo flowInfo = CustomConfigHolder.getInstance().getCustomFlowInfo(type);

        if (flowInfo == null) {
            // コマンド解決が出来ない場合
            throw new FlowNotFoundException(type);
        }

        // 実行クラスを取得
        Class runnerClass = flowInfo.getRunner();

        if (runnerClass == null) {
            // クラスが設定されていない場合（コンパイルが通らないレベルのため通常発生しない）
            throw new SystemException(BaseMessages.BASE_ERR_0001);
        }

        try {
            // インスタンス生成＋返却
            return FlowRunner.class.cast(runnerClass.newInstance());
        } catch (Exception e) {
            throw new SystemException(e, BaseMessages.BASE_ERR_0001);
        }
    }
}
