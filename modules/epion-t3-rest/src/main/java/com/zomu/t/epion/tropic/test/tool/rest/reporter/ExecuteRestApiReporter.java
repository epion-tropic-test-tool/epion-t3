package com.zomu.t.epion.tropic.test.tool.rest.reporter;

import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;
import com.zomu.t.epion.tropic.test.tool.rest.command.ExecuteRestApi;

import java.util.Map;

public class ExecuteRestApiReporter
        extends AbstractThymeleafCommandReporter<ExecuteRestApi> {
    @Override
    public String templatePath() {
        return "execute-rest-api-report";
    }

    @Override
    public void setVariables(Map<String, Object> variable) {
    }
}
