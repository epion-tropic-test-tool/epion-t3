package com.zomu.t.epion.tropic.test.tool.core.command.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.FileEvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.ObjectEvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.CoreMessages;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowScopeVariables;
import com.zomu.t.epion.tropic.test.tool.core.type.ReferenceVariableType;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioScopeVariables;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * コマンドの実行処理インターフェース.
 *
 * @param <COMMAND>
 */
public interface CommandRunner<COMMAND extends Command> {

    /**
     * 変数の抽出パターン.
     */
    Pattern EXTRACT_PATTERN = Pattern.compile("([^.]+)\\.(.+)");

    /**
     * @param command
     * @param globalScopeVariables
     * @param scenarioScopeVariables
     * @throws Exception
     */
    void execute(final COMMAND command,
                 final Map<String, Object> globalScopeVariables,
                 final Map<String, Object> scenarioScopeVariables,
                 final Map<String, Object> flowScopeVariables,
                 final Map<String, EvidenceInfo> evidences,
                 final Logger logger) throws Exception;


    /**
     * 変数を解決する.
     *
     * @param globalScopeVariables
     * @param scenarioScopeVariables
     * @param flowScopeVariables
     * @param referenceVariable
     * @return
     */
    default Object resolveVariables(
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final String referenceVariable) {

        Matcher m = EXTRACT_PATTERN.matcher(referenceVariable);

        if (m.find()) {
            ReferenceVariableType referenceVariableType = ReferenceVariableType.valueOfByName(m.group(1));
            if (referenceVariableType != null) {
                switch (referenceVariableType) {
                    case FIX:
                        return m.group(2);
                    case GLOBAL:
                        return globalScopeVariables.get(m.group(2));
                    case SCENARIO:
                        return scenarioScopeVariables.get(m.group(2));
                    case FLOW:
                        return flowScopeVariables.get(m.group(2));
                    default:
                        throw new SystemException(CoreMessages.CORE_ERR_0005, m.group(1));
                }
            } else {
                throw new SystemException(CoreMessages.CORE_ERR_0005, m.group(1));
            }
        }
        return null;
    }


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
     * エビデンスのパスを取得する.
     * ファイルの拡張子を指定することで、Runnerが保存すべきエビデンスの場所を取得するために利用する.
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
     * ファイルエビデンスを登録する.
     *
     * @param scenarioScopeVariables
     * @param flowScopeVariables
     * @param evidences
     * @param evidence
     */
    default void registrationFileEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Path evidence) {
        FileEvidenceInfo evidenceInfo = new FileEvidenceInfo();
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
    default void registrationFileEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Path evidence,
            String name) {
        FileEvidenceInfo evidenceInfo = new FileEvidenceInfo();
        evidenceInfo.setFqsn(scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(scenarioScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(name);
        evidenceInfo.setPath(evidence);
        evidences.put(name, evidenceInfo);
    }

    /**
     * オブジェクトエビデンスを登録する.
     *
     * @param scenarioScopeVariables
     * @param flowScopeVariables
     * @param evidences
     * @param evidence
     */
    default void registrationObjectEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Object evidence) {
        ObjectEvidenceInfo evidenceInfo = new ObjectEvidenceInfo();
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
        evidenceInfo.setObject(evidence);
        evidences.put(getEvidenceBaseName(flowScopeVariables), evidenceInfo);
    }

    /**
     * @param scenarioScopeVariables
     * @param evidences
     * @param evidence
     * @param name
     */
    default void registrationObjectEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Object evidence,
            String name) {
        ObjectEvidenceInfo evidenceInfo = new ObjectEvidenceInfo();
        evidenceInfo.setFqsn(scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(scenarioScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(name);
        evidenceInfo.setObject(evidence);
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
