package com.zomu.t.epion.tropic.test.tool.core.execution.runner;

import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioScopeVariables;
import org.slf4j.Logger;

import java.util.Map;

/**
 * コマンドの実行処理インターフェース.
 *
 * @param <PROCESS>
 */
public interface CommandRunner<PROCESS extends Process> {

    /**
     * @param process
     * @param globalScopeVariables
     * @param scenarioScopeVariables
     * @throws Exception
     */
    void execute(final PROCESS process,
                 final Map<String, Object> globalScopeVariables,
                 final Map<String, Object> scenarioScopeVariables,
                 final Logger logger) throws Exception;

    /**
     * シナリオ格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    default String getScenarioDirectory(final Map<String, Object> scenarioScopeVariables) {
        return scenarioScopeVariables
                .get(ScenarioScopeVariables.SCENARIO_DIR.getName()).toString();
    }

    /**
     * エビデンス格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    default String getEvidenceDirectory(final Map<String, Object> scenarioScopeVariables) {
        return scenarioScopeVariables
                .get(ScenarioScopeVariables.EVIDENCE_DIR.getName()).toString();
    }
}
