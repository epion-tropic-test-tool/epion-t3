package com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.bean.NoneCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;

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
