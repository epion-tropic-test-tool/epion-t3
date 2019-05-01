package com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.CommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.scenario.Command;

import java.util.Map;

/**
 * @param <COMMAND>
 * @param <COMMAND_RESULT>
 * @param <EXECUTE_CONTEXT>
 * @param <EXECUTE_SCENARIO>
 * @param <EXECUTE_FLOW>
 * @param <EXECUTE_COMMAND>
 * @author takashno
 */
public interface ThymeleafCommandReporter<
        COMMAND extends Command,
        COMMAND_RESULT extends CommandResult,
        EXECUTE_CONTEXT extends ExecuteContext,
        EXECUTE_SCENARIO extends ExecuteScenario,
        EXECUTE_FLOW extends ExecuteFlow,
        EXECUTE_COMMAND extends ExecuteCommand>
        extends CommandReporter<COMMAND, COMMAND_RESULT, EXECUTE_CONTEXT, EXECUTE_SCENARIO, EXECUTE_FLOW, EXECUTE_COMMAND> {

    /**
     * @return
     */
    String templatePath();

    /**
     * @param variable
     */
    void setVariables(Map<String, Object> variable,
                      COMMAND command,
                      COMMAND_RESULT commandResult,
                      EXECUTE_CONTEXT executeContext,
                      EXECUTE_SCENARIO executeScenario,
                      EXECUTE_FLOW executeFlow,
                      EXECUTE_COMMAND executeCommand);

}

