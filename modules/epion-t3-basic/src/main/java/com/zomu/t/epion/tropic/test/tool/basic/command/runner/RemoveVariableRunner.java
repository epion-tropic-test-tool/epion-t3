package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.RemoveVariable;
import com.zomu.t.epion.tropic.test.tool.basic.command.model.SetVariable;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.CoreMessages;
import com.zomu.t.epion.tropic.test.tool.core.type.ReferenceVariableType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;

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
