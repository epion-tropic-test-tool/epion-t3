package com.zomu.t.epion.tropic.test.tool.rest.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.AssertCommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.common.type.AssertStatus;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Response;
import com.zomu.t.epion.tropic.test.tool.rest.command.AssertHttpStatus;
import com.zomu.t.epion.tropic.test.tool.rest.message.RestMessages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

public class AssertHttpStatusRunner extends AbstractCommandRunner<AssertHttpStatus> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(AssertHttpStatus command, Logger logger) throws Exception {

        AssertCommandResult commandResult = new AssertCommandResult();

        Response response = referObjectEvidence(command.getTarget());

        if (!StringUtils.isNumeric(command.getValue())) {
            throw new SystemException(RestMessages.REST_ERR_9002, command.getValue());
        }

        int statusCode = Integer.valueOf(command.getValue());

        commandResult.setExpected(statusCode);
        commandResult.setActual(response.getStatusCode());

        if (response.getStatusCode() == statusCode) {
            commandResult.setAssertStatus(AssertStatus.OK);
            commandResult.setMessage(MessageManager.getInstance().getMessage(RestMessages.REST_INFO_1001));
        } else {
            commandResult.setAssertStatus(AssertStatus.NG);
            commandResult.setMessage(MessageManager.getInstance().getMessage(RestMessages.REST_ERR_9003));
        }

        return commandResult;
    }
}
