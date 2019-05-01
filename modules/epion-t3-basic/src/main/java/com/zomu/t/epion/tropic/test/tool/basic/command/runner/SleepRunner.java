package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.Sleep;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * Sleepコマンド処理.
 *
 * @author takashno
 */
public class SleepRunner extends AbstractCommandRunner<Sleep> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(
            Sleep command,
            Logger logger) throws Exception {

        if (StringUtils.isEmpty(command.getValue())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9003);
        }

        if (!StringUtils.isNumeric(command.getValue())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9004);
        }

        // Sleep...
        Thread.sleep(Long.valueOf(command.getValue()));

        return CommandResult.getSuccess();

    }
}
