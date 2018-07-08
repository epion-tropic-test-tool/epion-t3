package com.zomu.t.t3.base.execution.resolver;

import com.zomu.t.t3.base.message.BaseMessages;
import com.zomu.t.t3.core.exception.CommandNotFoundException;
import com.zomu.t.t3.core.exception.SystemException;
import com.zomu.t.t3.core.execution.runner.CommandRunner;
import com.zomu.t.t3.core.holder.CustomConfigHolder;
import com.zomu.t.t3.core.context.CommandInfo;
import org.apache.commons.lang3.StringUtils;

/**
 * @author takashno
 */
public final class BaseCommandResolver implements com.zomu.t.t3.core.execution.resolver.CommandResolver {

    /**
     * インスタンス.
     */
    private static final BaseCommandResolver instance = new BaseCommandResolver();

    /**
     * プライベートコンストラクタ.
     */
    private BaseCommandResolver() {
        // Do Nothing...
    }

    /**
     * インスタンス取得.
     *
     * @return
     */
    public static BaseCommandResolver getInstance() {
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
            throw new SystemException(BaseMessages.BASE_ERR_9001);
        }

        CommandInfo commandInfo = CustomConfigHolder.getCustomCommandInfo(commandId);

        if (commandInfo == null) {
            // コマンド解決が出来ない場合
            throw new CommandNotFoundException(commandId);
        }

        // 実行クラスを取得
        Class runnerClass = commandInfo.getRunner();

        if (runnerClass == null) {
            // クラスが設定されていない場合（コンパイルが通らないレベルのため通常発生しない）
            throw new SystemException(BaseMessages.BASE_ERR_9001);
        }

        try {
            // インスタンス生成＋返却
            return CommandRunner.class.cast(runnerClass.newInstance());
        } catch (Exception e) {
            throw new SystemException(e, BaseMessages.BASE_ERR_9001);
        }
    }
}
