package com.zomu.t.epion.tropic.test.tool.core.command.handler.listener;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;

/**
 * コマンド前処理リスナーインターフェース.
 *
 * @author takashno
 */
public interface CommandBeforeListener {

    /**
     * コマンド前処理.
     *
     * @param commandRunner
     * @param executeContext
     * @param executeScenario
     * @param executeFlow
     * @param executeCommand
     */
    void beforeCommand(CommandRunner commandRunner,
                       ExecuteContext executeContext,
                       ExecuteScenario executeScenario,
                       ExecuteFlow executeFlow,
                       ExecuteCommand executeCommand);

}
