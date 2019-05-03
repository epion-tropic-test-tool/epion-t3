package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.FormatDateString;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FormatDateStringRunner extends AbstractCommandRunner<FormatDateString> {

    @Override
    public CommandResult execute(FormatDateString command, Logger logger) throws Exception {
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }
        if (StringUtils.isEmpty(command.getValue())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9003);
        }
        if (StringUtils.isEmpty(command.getFormattedTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9010);
        }

        Object variable = resolveVariables(command.getTarget());

        if (variable == null) {
            throw new SystemException(BasicMessages.BASIC_ERR_9011, command.getTarget());
        }

        if (!Date.class.isAssignableFrom(variable.getClass())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9009);
        }

        Date date = Date.class.cast(variable);

        SimpleDateFormat sdf = new SimpleDateFormat(command.getValue());

        String formattedString = sdf.format(date);

        setVariable(command.getFormattedTarget(), formattedString);

        return CommandResult.getSuccess();
    }
}
