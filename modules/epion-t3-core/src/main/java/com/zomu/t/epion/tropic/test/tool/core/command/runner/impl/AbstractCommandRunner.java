package com.zomu.t.epion.tropic.test.tool.core.command.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.FileEvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.ObjectEvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
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

public abstract class AbstractCommandRunner<
        COMMAND extends Command,
        CONTEXT extends Context> implements CommandRunner<COMMAND, CONTEXT, ExecuteContext, ExecuteScenario, ExecuteFlow> {

    private COMMAND command;
    private CONTEXT context;
    private ExecuteContext executeContext;
    private ExecuteScenario executeScenario;
    private ExecuteFlow executeFlow;

    @Override
    public void execute(
            final COMMAND command,
            final CONTEXT context,
            final ExecuteContext executeContext,
            final ExecuteScenario executeScenario,
            final ExecuteFlow executeFlow,
            final Logger logger) throws Exception {

        this.command = command;
        this.context = context;
        this.executeContext = executeContext;
        this.executeScenario = executeScenario;
        this.executeFlow = executeFlow;

        // コマンド実行
        execute(command, logger);

    }


    /**
     * 変数を解決する.
     *
     * @param globalScopeVariables   グローバル変数
     * @param scenarioScopeVariables シナリオ変数
     * @param flowScopeVariables     フロー変数
     * @param referenceVariable      参照変数
     * @return 解決した値
     */
    protected Object resolveVariables(
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
    protected String getScenarioDirectory(final Map<String, Object> scenarioScopeVariables) {
        return scenarioScopeVariables
                .get(ScenarioScopeVariables.SCENARIO_DIR.getName()).toString();
    }

    /**
     * シナリオ格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    protected Path getScenarioDirectoryPath(final Map<String, Object> scenarioScopeVariables) {
        return Path.class.cast(scenarioScopeVariables
                .get(ScenarioScopeVariables.SCENARIO_DIR.getName()));
    }

    /**
     * エビデンス格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    protected String getEvidenceDirectory(final Map<String, Object> scenarioScopeVariables) {
        return scenarioScopeVariables
                .get(ScenarioScopeVariables.EVIDENCE_DIR.getName()).toString();
    }

    /**
     * エビデンス格納ディレクトリを取得する.
     *
     * @param scenarioScopeVariables
     * @return
     */
    protected Path getEvidenceDirectoryPath(final Map<String, Object> scenarioScopeVariables) {
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
    protected Path getEvidencePath(
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
    protected void registrationFileEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Path evidence) {
        FileEvidenceInfo evidenceInfo = new FileEvidenceInfo();
        evidenceInfo.setFqsn(scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(flowScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(getEvidenceBaseName(flowScopeVariables));
        evidenceInfo.setExecuteProcessId(flowScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName()).toString());
        evidenceInfo.setPath(evidence);
        evidences.put(getEvidenceBaseName(flowScopeVariables), evidenceInfo);
    }

    /**
     * 名前を明示的に指定してファイルエビデンスを登録する.
     *
     * @param scenarioScopeVariables
     * @param evidences
     * @param evidence
     * @param name
     */
    protected void registrationFileEvidenceWithName(
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
    protected void registrationObjectEvidence(
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Object evidence) {
        ObjectEvidenceInfo evidenceInfo = new ObjectEvidenceInfo();
        // Full Query Scenario Name として現在実行シナリオ名を設定
        evidenceInfo.setFqsn(scenarioScopeVariables.get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        // Full Query Process Name として現在実行プロセス名を設定
        evidenceInfo.setFqpn(flowScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(getEvidenceBaseName(flowScopeVariables));
        evidenceInfo.setExecuteProcessId(flowScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName()).toString());
        evidenceInfo.setObject(evidence);
        evidences.put(getEvidenceBaseName(flowScopeVariables), evidenceInfo);
    }

    /**
     * 名前を明示的に指定してオブジェクトエビデンスを登録する.
     *
     * @param scenarioScopeVariables
     * @param evidences
     * @param evidence
     * @param name
     */
    protected void registrationObjectEvidenceWithName(
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
     * @param flowScopeVariables フロー
     * @return
     */
    protected String getEvidenceBaseName(
            final Map<String, Object> flowScopeVariables) {
        return flowScopeVariables.get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString() + "_evidence";
    }
}
