package com.zomu.t.epion.tropic.test.tool.core.flow.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.flow.model.CommandExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.flow.model.FlowResult;
import org.slf4j.Logger;

/**
 * コマンド実行Flow処理.
 *
 * @author takashno
 */
public class CommandExecuteFlowRunner
        extends AbstractCommandExecuteFlowRunner<
        BaseContext,
        ExecuteContext,
        ExecuteScenario,
        ExecuteFlow,
        ExecuteCommand,
        CommandExecuteFlow,
        CommandExecuteFlow> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected FlowResult execute(
            final BaseContext context,
            final ExecuteContext executeContext,
            final ExecuteScenario executeScenario,
            final ExecuteFlow executeFlow,
            final CommandExecuteFlow flow,
            final Logger logger) {

        // コマンド実行
        executeCommand(
                context,
                executeContext,
                executeScenario,
                executeFlow,
                flow,
                logger);

        return FlowResult.getDefault();

    }
}
