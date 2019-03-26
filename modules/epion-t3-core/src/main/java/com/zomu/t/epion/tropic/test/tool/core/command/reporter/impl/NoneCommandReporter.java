package com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.model.NoneCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

import java.util.Map;

public class NoneCommandReporter
        extends AbstractThymeleafCommandReporter<NoneCommand, CommandResult> {
    @Override
    public String templatePath() {
        return null;
    }

    @Override
    public void setVariables(Map<String, Object> variable,
                             NoneCommand command,
                             CommandResult commandResult,
                             ExecuteContext executeContext,
                             ExecuteScenario executeScenario,
                             ExecuteFlow executeFlow,
                             ExecuteCommand executeCommand) {
    }
}
