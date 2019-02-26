package com.zomu.t.epion.tropic.test.tool.rdb.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.custom.configuration.resolver.CustomConfigurationTypeIdResolver;
import com.zomu.t.epion.tropic.test.tool.core.holder.CustomConfigurationHolder;
import com.zomu.t.epion.tropic.test.tool.rdb.command.model.ExecuteRdbQuery;
import org.slf4j.Logger;

public class ExecuteRdbQueryRunner  extends AbstractCommandRunner<ExecuteRdbQuery> {

    @Override
    public CommandResult execute(ExecuteRdbQuery command, Logger logger) throws Exception {

        CustomConfigurationHolder.getInstance().getCustomConfigurationInfo(command.getTarget());

        return null;
    }

}
