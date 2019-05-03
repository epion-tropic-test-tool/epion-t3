package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.SetVariable;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * @author takashno
 */
public class SetVariableRunner extends AbstractCommandRunner<SetVariable> {

    @Override
    public CommandResult execute(
            final SetVariable command,
            final Logger logger) throws Exception {

        // 対象必須
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }

        // 値必須
        if (StringUtils.isEmpty(command.getValue())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9003);
        }

        // 登録
        setVariable(command.getTarget(), command.getValue());

        return CommandResult.getSuccess();

    }
}
