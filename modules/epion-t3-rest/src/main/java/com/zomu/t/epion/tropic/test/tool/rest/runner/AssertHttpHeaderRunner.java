package com.zomu.t.epion.tropic.test.tool.rest.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.AssertCommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.common.type.AssertStatus;
import com.zomu.t.epion.tropic.test.tool.rest.bean.Response;
import com.zomu.t.epion.tropic.test.tool.rest.command.AssertHttpHeader;
import com.zomu.t.epion.tropic.test.tool.rest.message.RestMessages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class AssertHttpHeaderRunner extends AbstractCommandRunner<AssertHttpHeader> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(final AssertHttpHeader command, final Logger logger) throws Exception {

        AssertCommandResult commandResult = new AssertCommandResult();

        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(RestMessages.REST_ERR_9004);
        }

        if (StringUtils.isEmpty(command.getValue())) {
            throw new SystemException(RestMessages.REST_ERR_9005);
        }

        if (StringUtils.isEmpty(command.getHeader())) {
            throw new SystemException(RestMessages.REST_ERR_9016);
        }

        Response response = referObjectEvidence(command.getTarget());

        List<String> actual = response.getHeaders().get(command.getHeader());
        if (actual != null) {
            commandResult.setActual(actual.stream().collect(Collectors.joining(";")));
        }

        commandResult.setExpected(command.getValue());

        if (commandResult.getExpected().equals(commandResult.getActual())) {
            commandResult.setAssertStatus(AssertStatus.OK);
            commandResult.setMessage(MessageManager.getInstance().getMessage(RestMessages.REST_INFO_1002));
        } else {
            commandResult.setAssertStatus(AssertStatus.NG);
            commandResult.setMessage(MessageManager.getInstance().getMessage(RestMessages.REST_ERR_9017));
        }

        return commandResult;
    }
}
