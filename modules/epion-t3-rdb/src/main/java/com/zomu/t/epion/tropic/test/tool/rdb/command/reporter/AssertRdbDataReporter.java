package com.zomu.t.epion.tropic.test.tool.rdb.command.reporter;

import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.AbstractThymeleafCommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.common.context.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.common.bean.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.rdb.bean.AssertRdbDataResult;
import com.zomu.t.epion.tropic.test.tool.rdb.command.model.AssertRdbData;

import java.util.Map;

public class AssertRdbDataReporter
        extends AbstractThymeleafCommandReporter<AssertRdbData, AssertRdbDataResult> {
    @Override
    public String templatePath() {
        return "assert-rdb-data-report";
    }

    @Override
    public void setVariables(Map<String, Object> variable,
                             AssertRdbData command,
                             AssertRdbDataResult commandResult,
                             ExecuteContext executeContext,
                             ExecuteScenario executeScenario,
                             ExecuteFlow executeFlow,
                             ExecuteCommand executeCommand) {

        variable.put("tables", commandResult.getTables());
    }
}
