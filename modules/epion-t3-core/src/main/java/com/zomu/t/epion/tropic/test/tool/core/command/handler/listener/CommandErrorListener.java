package com.zomu.t.epion.tropic.test.tool.core.command.handler.listener;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;

/**
 * コマンドエラー処理リスナー.
 *
 * @author takashno
 */
public interface CommandErrorListener {

    /**
     * コマンドエラー処理.
     *
     * @param commandRunner
     * @param executeContext
     * @param executeScenario
     * @param executeFlow
     * @param executeCommand
     * @param t
     */
    void afterCommand(CommandRunner commandRunner,
                      ExecuteContext executeContext,
                      ExecuteScenario executeScenario,
                      ExecuteFlow executeFlow,
                      ExecuteCommand executeCommand,
                      Throwable t);
}
