package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.AddDate;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Calendar;
import java.util.Date;


public class AddDateRunner extends AbstractCommandRunner<AddDate> {

    @Override
    public CommandResult execute(AddDate command, Logger logger) throws Exception {
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }
        if (StringUtils.isEmpty(command.getValue())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9003);
        }
        if (!StringUtils.isNumeric(command.getValue())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9004);
        }
        if (StringUtils.isEmpty(command.getAddedTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9011);
        }

        Object variable = resolveVariables(command.getTarget());

        if (variable == null) {
            throw new SystemException(BasicMessages.BASIC_ERR_9011, command.getTarget());
        }

        if (!Date.class.isAssignableFrom(variable.getClass())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9009);
        }

        Date date = Date.class.cast(variable);

        Integer amount = Integer.valueOf(command.getValue());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, amount);

        setVariable(command.getAddedTarget(), cal.getTime());

        return CommandResult.getSuccess();
    }
}
