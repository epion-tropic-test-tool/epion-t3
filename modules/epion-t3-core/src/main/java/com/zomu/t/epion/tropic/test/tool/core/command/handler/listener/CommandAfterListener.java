package com.zomu.t.epion.tropic.test.tool.core.command.handler.listener;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;

/**
 * コマンドの後処理リスナー.
 *
 * @author takashno
 */
public interface CommandAfterListener {


    /**
     * コマンド後処理.
     *
     * @param commandRunner
     * @param executeContext
     * @param executeScenario
     * @param executeFlow
     * @param executeCommand
     */
    void afterCommand(CommandRunner commandRunner,
                      ExecuteContext executeContext,
                      ExecuteScenario executeScenario,
                      ExecuteFlow executeFlow,
                      ExecuteCommand executeCommand);


}
