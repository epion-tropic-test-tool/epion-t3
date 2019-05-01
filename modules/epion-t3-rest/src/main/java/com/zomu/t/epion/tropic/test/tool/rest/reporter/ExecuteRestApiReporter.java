package com.zomu.t.epion.tropic.test.tool.rest.reporter;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.common.util.EvidenceUtils;
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
