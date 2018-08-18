package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.StringConcat;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * コンソール入力コマンド実行クラス.
 * ユーザからのコンソール入力を受付、入力された文字列をシナリオスコープの変数に設定する.
 *
 * @author takashno
 */
public class StringConcatRunner implements CommandRunner<StringConcat> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final StringConcat process,
                        final Map<String, Object> globalScopeVariables,
                        final Map<String, Object> scenarioScopeVariables,
                        final Map<String, Object> flowScopeVariables,
                        final Map<String, EvidenceInfo> evidences,
                        final Logger logger) throws Exception {

        logger.info("start StringConcat");

        List<String> rawValues = new ArrayList<>();

        for (String referenceVariable : process.getReferenceVariables()) {
            Object variable = resolveVariables(
                    globalScopeVariables,
                    scenarioScopeVariables,
                    flowScopeVariables,
                    referenceVariable);
            if (variable != null) {
                rawValues.add(variable.toString());
            }
        }

        String joinedValue = StringUtils.join(rawValues.toArray(new String[0]));
        logger.info("Joined Value : {}", joinedValue);
        scenarioScopeVariables.put(process.getTarget(), joinedValue);
        logger.info("end StringConcat");
    }

}
