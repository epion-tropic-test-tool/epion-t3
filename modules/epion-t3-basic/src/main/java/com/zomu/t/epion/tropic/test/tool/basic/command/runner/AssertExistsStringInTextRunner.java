package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.AssertExistsStringInText;
import com.zomu.t.epion.tropic.test.tool.basic.command.model.StringConcat;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.model.AssertCommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageResolver;
import com.zomu.t.epion.tropic.test.tool.core.type.AssertStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.CommandStatus;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertExistsStringInTextRunner extends AbstractCommandRunner<AssertExistsStringInText> {

    @Override
    public CommandResult execute(
            AssertExistsStringInText command,
            Logger logger) throws Exception {

        AssertCommandResult commandResult = new AssertCommandResult();

        Path targetFile = referFileEvidence(command.getTarget());

        Pattern p = Pattern.compile(command.getValue());
        boolean existsFlg = false;
        List<String> lineList = null;
        try {
            lineList = FileUtils.readLines(targetFile.toFile(), command.getEncoding());
            for (String line : lineList) {
                Matcher m = p.matcher(line);
                if (m.find()) {
                    existsFlg = true;
                    break;
                }
            }

            if (!existsFlg) {
                commandResult.setMessage(MessageManager.getInstance().getMessage(
                        BasicMessages.BASIC_ERR_9002, command.getValue()));
                commandResult.setAssertStatus(AssertStatus.ERROR);
            } else {
                commandResult.setMessage(MessageManager.getInstance().getMessage(
                        BasicMessages.BASIC_INF_0001, command.getValue()));
                commandResult.setAssertStatus(AssertStatus.SUCCESS);
            }

            // TODO：どう扱う？
            commandResult.setStatus(CommandStatus.FAIL);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return commandResult;
    }
}