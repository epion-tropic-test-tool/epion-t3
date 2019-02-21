package com.zomu.t.epion.tropic.test.tool.core.command.resolver.impl;

import com.zomu.t.epion.tropic.test.tool.core.command.handler.CommandRunnerInvocationHandler;
import com.zomu.t.epion.tropic.test.tool.core.command.resolver.CommandRunnerResolver;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.BaseMessages;
import com.zomu.t.epion.tropic.test.tool.core.context.CommandInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.CommandNotFoundException;
import com.zomu.t.epion.tropic.test.tool.core.holder.CustomPackageHolder;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Proxy;

/**
 * @author takashno
 */
public final class CommandRunnerResolverImpl implements CommandRunnerResolver {

    /**
     * インスタンス.
     */
    private static final CommandRunnerResolverImpl instance = new CommandRunnerResolverImpl();

    /**
     * プライベートコンストラクタ.
     */
    private CommandRunnerResolverImpl() {
        // Do Nothing...
    }

    /**
     * インスタンス取得.
     *
     * @return
     */
    public static CommandRunnerResolverImpl getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @param commandId
     * @return
     */
    @Override
    public CommandRunnerInvocationHandler getCommandRunner(String commandId) {

        if (StringUtils.isEmpty(commandId)) {
            // 不正
            throw new SystemException(BaseMessages.BASE_ERR_0001);
        }

        CommandInfo commandInfo = CustomPackageHolder.getInstance().getCustomCommandInfo(commandId);

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
            CommandRunnerInvocationHandler commandRunnerInvocationHandler =
                    (CommandRunnerInvocationHandler)Proxy.newProxyInstance(
                            CommandRunnerResolverImpl.class.getClassLoader(),
                            new Class[]{CommandRunner.class},
                            new CommandRunnerInvocationHandler(
                                    CommandRunner.class.cast(runnerClass.newInstance())));
            return commandRunnerInvocationHandler;
        } catch (Exception e) {
            throw new SystemException(e, BaseMessages.BASE_ERR_0001);
        }
    }
}
