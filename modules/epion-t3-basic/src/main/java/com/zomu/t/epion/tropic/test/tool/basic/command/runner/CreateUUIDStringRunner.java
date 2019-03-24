package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.CreateUUID;
import com.zomu.t.epion.tropic.test.tool.basic.command.model.CreateUUIDString;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.UUID;


public class CreateUUIDStringRunner extends AbstractCommandRunner<CreateUUIDString> {

    @Override
    public CommandResult execute(CreateUUIDString command, Logger logger) throws Exception {
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }
        UUID uuid = UUID.randomUUID();
        setVariable(command.getTarget(), uuid.toString());
        return CommandResult.getSuccess();
    }
}
