package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.ConsoleInput;
import com.zomu.t.epion.tropic.test.tool.basic.command.model.StringConcat;
import com.zomu.t.epion.tropic.test.tool.basic.messages.BasicMessages;
import com.zomu.t.epion.tropic.test.tool.basic.type.ReferenceVariableType;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * コンソール入力コマンド実行クラス.
 * ユーザからのコンソール入力を受付、入力された文字列をシナリオスコープの変数に設定する.
 *
 * @author takashno
 */
public class StringConcatRunner implements CommandRunner<StringConcat> {

    private Pattern EXTRACT_PATTERN = Pattern.compile("([^.]+)\\.(.+)");

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final StringConcat process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables,
                        final Map<String, EvidenceInfo> evidences,
                        final Logger logger) throws Exception {

        logger.info("start StringConcat");

        List<String> rawValues = new ArrayList<>();

        for (String referenceVariable : process.getReferenceVariables()) {

            Matcher m = EXTRACT_PATTERN.matcher(referenceVariable);

            if (m.find()) {
                ReferenceVariableType referenceVariableType = ReferenceVariableType.valueOfByName(m.group(1));
                if (referenceVariableType != null) {
                    switch (referenceVariableType) {
                        case FIX:
                            rawValues.add(m.group(2));
                            break;
                        case GLOBAL:
                            rawValues.add(globalScopeVariables.get(m.group(2)).toString());
                            break;
                        case SCENARIO:
                            rawValues.add(scenarioScopeVariables.get(m.group(2)).toString());
                            break;
                        default:
                            throw new SystemException(BasicMessages.BASIC_ERR_9001, m.group(1));
                    }
                } else {
                    throw new SystemException(BasicMessages.BASIC_ERR_9001, m.group(1));
                }
            } else {
                rawValues.add(referenceVariable);
            }
        }

        String joinedValue = StringUtils.join(rawValues.toArray(new String[0]));
        logger.info("Joined Value : {}", joinedValue);
        scenarioScopeVariables.put(process.getTarget(), joinedValue);
        logger.info("end StringConcat");
    }

}
