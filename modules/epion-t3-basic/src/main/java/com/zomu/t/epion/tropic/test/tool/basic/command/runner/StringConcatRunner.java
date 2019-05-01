package com.zomu.t.epion.tropic.test.tool.basic.command.runner;

import com.zomu.t.epion.tropic.test.tool.basic.command.model.StringConcat;
import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * コンソール入力コマンド実行クラス.
 * ユーザからのコンソール入力を受付、入力された文字列をシナリオスコープの変数に設定する.
 *
 * @author takashno
 */
public class StringConcatRunner extends AbstractCommandRunner<StringConcat> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(final StringConcat process,
                                 final Logger logger) throws Exception {

        List<String> rawValues = new ArrayList<>();

        for (String referenceVariable : process.getReferenceVariables()) {
            Object variable = resolveVariables(referenceVariable);
            if (variable != null) {
                rawValues.add(variable.toString());
            }
        }

        String joinedValue = StringUtils.join(rawValues.toArray(new String[0]));
        logger.info("Joined Value : {}", joinedValue);
        getScenarioScopeVariables().put(process.getTarget(), joinedValue);
        
        return CommandResult.getSuccess();
    }

}
