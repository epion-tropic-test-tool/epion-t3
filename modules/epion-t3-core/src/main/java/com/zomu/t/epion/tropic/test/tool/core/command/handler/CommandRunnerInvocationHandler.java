package com.zomu.t.epion.tropic.test.tool.core.command.handler;

import com.zomu.t.epion.tropic.test.tool.core.command.handler.listener.CommandListenerFactory;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * コマンド実行時のプロキシクラス.
 * このプロキシクラスでは、以下のハンドリングを行う.
 *
 * @author takashno
 */
public class CommandRunnerInvocationHandler<COMMAND_RUNNER extends CommandRunner> implements InvocationHandler {

    private final COMMAND_RUNNER commandRunner;

    private final ExecuteContext executeContext;

    private final ExecuteScenario executeScenario;

    private final ExecuteFlow executeFlow;

    private final ExecuteCommand executeCommand;

    public CommandRunnerInvocationHandler(
            COMMAND_RUNNER commandRunner,
            ExecuteContext executeContext,
            ExecuteScenario executeScenario,
            ExecuteFlow executeFlow,
            ExecuteCommand executeCommand) {
        this.commandRunner = commandRunner;
        this.executeContext = executeContext;
        this.executeScenario = executeScenario;
        this.executeFlow = executeFlow;
        this.executeCommand = executeCommand;
    }

    /**
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;

        try {

            // コマンド前処理リスナーを実行
            CommandListenerFactory.getInstance().getBeforeListener().forEach(x -> x.beforeCommand(
                    commandRunner,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand
            ));

            result = method.invoke(commandRunner, args);

            // コマンド後処理リスナーを実行
            CommandListenerFactory.getInstance().getAfterListener().forEach(x -> x.afterCommand(
                    commandRunner,
                    executeContext,
                    executeScenario,
                    executeFlow,
                    executeCommand
            ));

        } catch (Exception e) {

        }

        return result;
    }
}
