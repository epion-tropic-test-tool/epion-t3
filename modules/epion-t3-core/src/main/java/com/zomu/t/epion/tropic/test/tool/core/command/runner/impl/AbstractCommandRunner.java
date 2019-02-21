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
        COMMAND extends Command> implements CommandRunner<COMMAND, Context, ExecuteContext, ExecuteScenario, ExecuteFlow> {

    private COMMAND command;
    private Context context;
    private ExecuteContext executeContext;
    private ExecuteScenario executeScenario;
    private ExecuteFlow executeFlow;

    @Override
    public void execute(
            final COMMAND command,
            final Context context,
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

    protected Map<String, Object> getGlobalScopeVariables() {
        return executeContext.getGlobalVariables();
    }

    protected Map<String, Object> getScenarioScopeVariables() {
        return executeScenario.getScenarioVariables();
    }

    protected Map<String, Object> getFlowScopeVariables() {
        return executeFlow.getFlowVariables();
    }

    protected Map<String, EvidenceInfo> getEvidences() {
        return executeScenario.getEvidences();
    }

    /**
     * 変数を解決する.
     *
     * @param referenceVariable 参照変数
     * @return 解決した値
     */
    protected Object resolveVariables(
            final String referenceVariable) {

        Matcher m = EXTRACT_PATTERN.matcher(referenceVariable);

        if (m.find()) {
            ReferenceVariableType referenceVariableType = ReferenceVariableType.valueOfByName(m.group(1));
            if (referenceVariableType != null) {
                switch (referenceVariableType) {
                    case FIX:
                        return m.group(2);
                    case GLOBAL:
                        return executeContext.getGlobalVariables().get(m.group(2));
                    case SCENARIO:
                        return executeScenario.getScenarioVariables().get(m.group(2));
                    case FLOW:
                        return executeFlow.getFlowVariables().get(m.group(2));
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
     * @return
     */
    protected String getScenarioDirectory() {
        return executeScenario.getScenarioVariables()
                .get(ScenarioScopeVariables.SCENARIO_DIR.getName()).toString();
    }

    /**
     * シナリオ格納ディレクトリを取得する.
     *
     * @return
     */
    protected Path getScenarioDirectoryPath() {
        return Path.class.cast(executeScenario.getScenarioVariables()
                .get(ScenarioScopeVariables.SCENARIO_DIR.getName()));
    }

    /**
     * エビデンス格納ディレクトリを取得する.
     *
     * @return
     */
    protected String getEvidenceDirectory() {
        return executeScenario.getScenarioVariables()
                .get(ScenarioScopeVariables.EVIDENCE_DIR.getName()).toString();
    }

    /**
     * エビデンス格納ディレクトリを取得する.
     *
     * @return
     */
    protected Path getEvidenceDirectoryPath() {
        return Path.class.cast(executeScenario.getScenarioVariables()
                .get(ScenarioScopeVariables.EVIDENCE_DIR.getName()));
    }

    /**
     * エビデンスのパスを取得する.
     * ファイルの拡張子を指定することで、Runnerが保存すべきエビデンスの場所を取得するために利用する.
     *
     * @param fileExtension
     * @return
     */
    protected Path getEvidencePath(
            String fileExtension) {
        return Paths.get(
                getEvidenceDirectoryPath().toString(),
                getEvidenceBaseName() + "." + fileExtension);
    }

    /**
     * ファイルエビデンスを登録する.
     *
     * @param evidence
     */
    protected void registrationFileEvidence(
            Path evidence) {
        FileEvidenceInfo evidenceInfo = new FileEvidenceInfo();
        evidenceInfo.setFqsn(executeScenario.getScenarioVariables().get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(executeFlow.getFlowVariables().get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(getEvidenceBaseName());
        evidenceInfo.setExecuteProcessId(executeFlow.getFlowVariables().get(FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName()).toString());
        evidenceInfo.setPath(evidence);
        executeScenario.getEvidences().put(getEvidenceBaseName(), evidenceInfo);
    }

    /**
     * 名前を明示的に指定してファイルエビデンスを登録する.
     *
     * @param evidence
     * @param name
     */
    protected void registrationFileEvidenceWithName(
            Path evidence,
            String name) {
        FileEvidenceInfo evidenceInfo = new FileEvidenceInfo();
        evidenceInfo.setFqsn(executeScenario.getScenarioVariables().get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(executeScenario.getScenarioVariables().get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(name);
        evidenceInfo.setPath(evidence);
        executeScenario.getEvidences().put(name, evidenceInfo);
    }

    /**
     * オブジェクトエビデンスを登録する.
     *
     * @param evidence
     */
    protected void registrationObjectEvidence(
            Object evidence) {
        ObjectEvidenceInfo evidenceInfo = new ObjectEvidenceInfo();
        // Full Query Scenario Name として現在実行シナリオ名を設定
        evidenceInfo.setFqsn(executeScenario.getScenarioVariables().get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        // Full Query Process Name として現在実行プロセス名を設定
        evidenceInfo.setFqpn(executeFlow.getFlowVariables().get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(getEvidenceBaseName());
        evidenceInfo.setExecuteProcessId(executeFlow.getFlowVariables().get(FlowScopeVariables.CURRENT_COMMAND_EXECUTE_ID.getName()).toString());
        evidenceInfo.setObject(evidence);
        executeScenario.getEvidences().put(getEvidenceBaseName(), evidenceInfo);
    }

    /**
     * 名前を明示的に指定してオブジェクトエビデンスを登録する.
     *
     * @param evidence
     * @param name
     */
    protected void registrationObjectEvidenceWithName(
            Object evidence,
            String name) {
        ObjectEvidenceInfo evidenceInfo = new ObjectEvidenceInfo();
        evidenceInfo.setFqsn(executeScenario.getScenarioVariables().get(ScenarioScopeVariables.CURRENT_SCENARIO.getName()).toString());
        evidenceInfo.setFqpn(executeScenario.getScenarioVariables().get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString());
        evidenceInfo.setName(name);
        evidenceInfo.setObject(evidence);
        executeScenario.getEvidences().put(name, evidenceInfo);
    }


    /**
     * エビデンス名を取得する.
     *
     * @return
     */
    protected String getEvidenceBaseName() {
        return executeFlow.getFlowVariables().get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString() + "_evidence";
    }
}
