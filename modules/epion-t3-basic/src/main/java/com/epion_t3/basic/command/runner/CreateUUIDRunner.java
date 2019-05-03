package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.CreateUUID;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.UUID;


public class CreateUUIDRunner extends AbstractCommandRunner<CreateUUID> {

    @Override
    public CommandResult execute(CreateUUID command, Logger logger) throws Exception {
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }
        UUID uuid = UUID.randomUUID();
        setVariable(command.getTarget(), uuid);
        return CommandResult.getSuccess();
    }
}
