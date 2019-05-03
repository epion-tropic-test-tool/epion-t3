package com.epion_t3.basic.command.runner;

import com.epion_t3.basic.command.model.RemoveVariable;
import com.epion_t3.basic.messages.BasicMessages;
import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

/**
 * 変数削除コマンド実行処理.
 *
 * @author takashno
 */
public class RemoveVariableRunner extends AbstractCommandRunner<RemoveVariable> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(
            final RemoveVariable command,
            final Logger logger) throws Exception {

        // 対象必須
        if (StringUtils.isEmpty(command.getTarget())) {
            throw new SystemException(BasicMessages.BASIC_ERR_9005);
        }

        // 削除
        removeVariable(command.getTarget());

        return CommandResult.getSuccess();
    }
}
