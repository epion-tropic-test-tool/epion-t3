package com.zomu.t.epion.tropic.test.tool.core.command.reporter;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.common.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;

/**
 * コマンドレポート出力処理インタフェース.
 *
 * @param <COMMAND>
 * @param <COMMAND_RESULT>
 * @param <EXECUTE_CONTEXT>
 * @param <EXECUTE_SCENARIO>
 * @param <EXECUTE_FLOW>
 * @param <EXECUTE_COMMAND>
 * @author takashno
 */
public interface CommandReporter<
        COMMAND extends Command,
        COMMAND_RESULT extends CommandResult,
        EXECUTE_CONTEXT extends ExecuteContext,
        EXECUTE_SCENARIO extends ExecuteScenario,
        EXECUTE_FLOW extends ExecuteFlow,
        EXECUTE_COMMAND extends ExecuteCommand> {


    void report(COMMAND command,
                COMMAND_RESULT result,
                Context context,
                ExecuteContext executeContext,
                ExecuteScenario executeScenario,
                ExecuteFlow executeFlow,
                ExecuteCommand executeCommand,
                Throwable t);


}
