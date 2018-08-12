package com.zomu.t.epion.tropic.test.tool.core.execution.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Process;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioScopeVariables;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
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
                 final Map<String, EvidenceInfo> evidences,
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
     * シナリオ格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    default Path getScenarioDirectoryPath(final Map<String, Object> scenarioScopeVariables) {
        return Path.class.cast(scenarioScopeVariables
                .get(ScenarioScopeVariables.SCENARIO_DIR.getName()));
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

    /**
     * エビデンス格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    default Path getEvidenceDirectoryPath(final Map<String, Object> scenarioScopeVariables) {
        return Path.class.cast(scenarioScopeVariables
                .get(ScenarioScopeVariables.EVIDENCE_DIR.getName()));
    }

    default Path getEvidencePath(final Map<String, Object> scenarioScopeVariables, String fileExtension) {
        return Paths.get(
                getEvidenceDirectoryPath(scenarioScopeVariables).toString(),
                getEvidenceBaseName(scenarioScopeVariables) + "." + fileExtension);
    }

    /**
     * エビデンスを登録する.
     *
     * @param scenarioScopeVariables
     * @param evidences
     * @param evidence
     */
    default void registEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Path evidence) {
        EvidenceInfo evidenceInfo = new EvidenceInfo();
        evidenceInfo.setFqsn(
                scenarioScopeVariables.get(
                        ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(
                scenarioScopeVariables.get(
                        ScenarioScopeVariables.CURRENT_PROCESS.getName()).toString());
        evidenceInfo.setName(
                getEvidenceBaseName(
                        scenarioScopeVariables));
        evidenceInfo.setExecuteProcessId(
                scenarioScopeVariables.get(
                        ScenarioScopeVariables.CURRENT_PROCESS_EXECUTEID.getName()).toString());
        evidenceInfo.setPath(evidence);
        evidences.put(getEvidenceBaseName(scenarioScopeVariables), evidenceInfo);
    }

    /**
     * @param scenarioScopeVariables
     * @param evidences
     * @param evidence
     * @param name
     */
    default void registEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Path evidence,
            String name) {
        EvidenceInfo evidenceInfo = new EvidenceInfo();
        evidenceInfo.setFqsn(scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_PROCESS.getName()).toString());
        evidenceInfo.setName(name);
        evidenceInfo.setPath(evidence);
        evidences.put(name, evidenceInfo);
    }

    /**
     * エビデンス名を取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    default String getEvidenceBaseName(
            final Map<String, Object> scenarioScopeVariables) {
        return scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_PROCESS.getName()).toString() + "_evidence";
    }
}
