package com.zomu.t.epion.tropic.test.tool.core.execution.resolver.impl;

import com.zomu.t.epion.tropic.test.tool.core.execution.resolver.CommandRunnerResolver;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.BaseMessages;
import com.zomu.t.epion.tropic.test.tool.core.context.CommandInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.CommandNotFoundException;
import com.zomu.t.epion.tropic.test.tool.core.holder.CustomConfigHolder;
import org.apache.commons.lang3.StringUtils;

/**
 * @author takashno
 */
public final class BaseCommandRunnerResolver implements CommandRunnerResolver {

    /**
     * インスタンス.
     */
    private static final BaseCommandRunnerResolver instance = new BaseCommandRunnerResolver();

    /**
     * プライベートコンストラクタ.
     */
    private BaseCommandRunnerResolver() {
        // Do Nothing...
    }

    /**
     * インスタンス取得.
     *
     * @return
     */
    public static BaseCommandRunnerResolver getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @param commandId
     * @return
     */
    @Override
    public CommandRunner getCommandRunner(String commandId) {

        if (StringUtils.isEmpty(commandId)) {
            // 不正
            throw new SystemException(BaseMessages.BASE_ERR_0001);
        }

        CommandInfo commandInfo = CustomConfigHolder.getInstance().getCustomCommandInfo(commandId);

        if (commandInfo == null) {
            // コマンド解決が出来ない場合
            throw new CommandNotFoundException(commandId);
        }

        // 実行クラスを取得
        Class runnerClass = commandInfo.getRunner();

        if (runnerClass == null) {
            // クラスが設定されていない場合（コンパイルが通らないレベルのため通常発生しない）
            throw new SystemException(BaseMessages.BASE_ERR_0001);
        }

        try {
            // インスタンス生成＋返却
            return CommandRunner.class.cast(runnerClass.newInstance());
        } catch (Exception e) {
            throw new SystemException(e, BaseMessages.BASE_ERR_0001);
        }
    }
}
