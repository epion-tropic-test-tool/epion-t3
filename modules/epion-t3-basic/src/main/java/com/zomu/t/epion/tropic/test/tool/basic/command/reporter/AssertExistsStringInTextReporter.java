package com.zomu.t.epion.tropic.test.tool.basic.command.reporter;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.AssertExistsStringInText;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;

import java.util.Map;

/**
 *
 */
public class AssertExistsStringInTextReporter
        extends AbstractThymeleafCommandReporter<AssertExistsStringInText> {

    @Override
    public String templatePath() {
        return "scenario";
    }

    /**
     *
     * @param variable
     * @param command
     * @param executeContext
     * @param executeScenario
     * @param executeFlow
     * @param executeCommand
     */
    @Override
    public void setVariables(Map<String, Object> variable,
                             AssertExistsStringInText command,
                             ExecuteContext executeContext,
                             ExecuteScenario executeScenario,
                             ExecuteFlow executeFlow,
                             ExecuteCommand executeCommand) {
        // Do Nothing...
    }

}
