package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.CreateNowDate;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Date;


public class CreateNowDateRunner extends AbstractCommandRunner<CreateNowDate> {

    @Override
    public CommandResult execute(CreateNowDate command, Logger logger) throws Exception {
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }
        setVariable(command.getTarget(), new Date());
        return CommandResult.getSuccess();
    }
}
