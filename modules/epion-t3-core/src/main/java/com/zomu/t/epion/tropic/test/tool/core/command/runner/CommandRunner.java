package com.zomu.t.epion.tropic.test.tool.core.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowScopeVariables;
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
public interface CommandRunner<PROCESS extends Command> {

    /**
     * @param process
     * @param globalScopeVariables
     * @param scenarioScopeVariables
     * @throws Exception
     */
    void execute(final PROCESS process,
                 final Map<String, Object> globalScopeVariables,
                 final Map<String, Object> scenarioScopeVariables,
                 final Map<String, Object> flowScopeVariables,
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

    /**
     *
     * @param scenarioScopeVariables
     * @param flowScopeVariables
     * @param fileExtension
     * @return
     */
    default Path getEvidencePath(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            String fileExtension) {
        return Paths.get(
                getEvidenceDirectoryPath(scenarioScopeVariables).toString(),
                getEvidenceBaseName(flowScopeVariables) + "." + fileExtension);
    }

    /**
     * エビデンスを登録する.
     *
     * @param scenarioScopeVariables
     * @param flowScopeVariables
     * @param evidences
     * @param evidence
     */
    default void registEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Path evidence) {
        EvidenceInfo evidenceInfo = new EvidenceInfo();
        evidenceInfo.setFqsn(
                scenarioScopeVariables.get(
                        ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(
                flowScopeVariables.get(
                        FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(
                getEvidenceBaseName(
                        flowScopeVariables));
        evidenceInfo.setExecuteProcessId(
                flowScopeVariables.get(
                        FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName()).toString());
        evidenceInfo.setPath(evidence);
        evidences.put(getEvidenceBaseName(flowScopeVariables), evidenceInfo);
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
        evidenceInfo.setFqpn(scenarioScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(name);
        evidenceInfo.setPath(evidence);
        evidences.put(name, evidenceInfo);
    }

    /**
     * エビデンス名を取得する.
     *
     * @param flowScopeVariables
     * @return
     */
    default String getEvidenceBaseName(
            final Map<String, Object> flowScopeVariables) {
        return flowScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString() + "_evidence";
    }
}
