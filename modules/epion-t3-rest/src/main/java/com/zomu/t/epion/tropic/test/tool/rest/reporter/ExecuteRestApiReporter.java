package com.zomu.t.epion.tropic.test.tool.rest.reporter;

import com.zomu.t.epion.tropic.test.tool.core.command.model.AssertCommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.util.EvidenceUtils;
import com.zomu.t.epion.tropic.test.tool.rest.command.ExecuteRestApi;

import java.util.Map;

public class ExecuteRestApiReporter
        extends AbstractThymeleafCommandReporter<ExecuteRestApi, CommandResult> {
    @Override
    public String templatePath() {
        return "execute-rest-api-report";
    }

    @Override
    public void setVariables(Map<String, Object> variable,
                             ExecuteRestApi command,
                             CommandResult commandResult,
                             ExecuteContext executeContext,
                             ExecuteScenario executeScenario,
                             ExecuteFlow executeFlow,
                             ExecuteCommand executeCommand) {
        variable.put("request", command.getRequest());
        variable.put("response", EvidenceUtils.getInstance().referObjectEvidence(
                executeContext, executeScenario, executeFlow.getFlow().getId()));
    }
}
