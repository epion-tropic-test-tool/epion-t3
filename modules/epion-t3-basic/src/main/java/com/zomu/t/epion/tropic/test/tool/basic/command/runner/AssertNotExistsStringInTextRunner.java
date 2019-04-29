package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.AssertNotExistsStringInText;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.core.command.model.AssertCommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.common.type.AssertStatus;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertNotExistsStringInTextRunner extends AbstractCommandRunner<AssertNotExistsStringInText> {

    @Override
    public CommandResult execute(
            AssertNotExistsStringInText command,
            Logger logger) throws Exception {

        AssertCommandResult commandResult = AssertCommandResult.getSuccess();
        commandResult.setExpected("指定テキストファイルに、指定した文字列が含まれていない");

        Path targetFile = referFileEvidence(command.getTarget());

        Pattern p = null;
        if (command.getRegexp()) {
            p = Pattern.compile(command.getValue());
        }

        boolean existsFlg = false;
        List<String> lineList = null;
        try {
            lineList = FileUtils.readLines(targetFile.toFile(), command.getEncoding());
            for (String line : lineList) {
                if (p != null) {
                    Matcher m = p.matcher(line);
                    if (m.find()) {
                        existsFlg = true;
                        break;
                    }
                } else {
                    if (line.contains(command.getValue())) {
                        existsFlg = true;
                        break;
                    }
                }
            }

            if (!existsFlg) {
                commandResult.setMessage(MessageManager.getInstance().getMessage(
                        BasicMessages.BASIC_ERR_9002, command.getValue()));
                commandResult.setAssertStatus(AssertStatus.OK);
                commandResult.setActual("指定テキストファイルに、指定した文字列が含まれていない");
            } else {
                commandResult.setMessage(MessageManager.getInstance().getMessage(
                        BasicMessages.BASIC_INF_0001, command.getValue()));
                commandResult.setAssertStatus(AssertStatus.NG);
                commandResult.setActual("指定テキストファイルに、指定した文字列が含まれている");
            }


        } catch (IOException e) {
            throw new SystemException(BasicMessages.BASIC_ERR_9008, targetFile.toString());
        }
        return commandResult;
    }
}