package com.zomu.t.epion.tropic.test.tool.core.command.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.CommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.command.reporter.impl.NoneCommandReporter;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.FileEvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.ObjectEvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.CoreMessages;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Configuration;
import com.zomu.t.epion.tropic.test.tool.core.type.CommandStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowScopeVariables;
import com.zomu.t.epion.tropic.test.tool.core.type.ReferenceVariableType;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioScopeVariables;
import com.zomu.t.epion.tropic.test.tool.core.util.BindUtils;
import com.zomu.t.epion.tropic.test.tool.core.util.IDUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * コマンド実行の基底クラス.
 *
 * @param <COMMAND>
 * @author takashno
 */
public abstract class AbstractCommandRunner<COMMAND extends Command>
        implements CommandRunner<
        COMMAND, Context, ExecuteContext, ExecuteScenario, ExecuteFlow, ExecuteCommand> {

    /**
     * コマンド.
     */
    private COMMAND command;

    /**
     * コンテキスト.
     */
    private Context context;

    /**
     * 実行情報.
     */
    private ExecuteContext executeContext;

    /**
     * シナリオ実行情報.
     */
    private ExecuteScenario executeScenario;

    /**
     * Flow実行情報.
     */
    private ExecuteFlow executeFlow;

    /**
     * コマンド実行情報.
     */
    private ExecuteCommand executeCommand;

    /**
     * コマンド実行処理.
     *
     * @param command         実行するコマンド
     * @param context         コンテキスト
     * @param executeContext  実行コンテキスト
     * @param executeScenario 実行シナリオ情報
     * @param executeFlow     実行Flow情報
     * @param executeCommand  実行コマンド情報
     * @param logger          ロガー
     * @throws Exception 例外
     */
    @Override
    public void execute(
            final COMMAND command,
            final Context context,
            final ExecuteContext executeContext,
            final ExecuteScenario executeScenario,
            final ExecuteFlow executeFlow,
            final ExecuteCommand executeCommand,
            final Logger logger) throws Exception {

        // インスタンス変数に登録
        this.command = command;
        this.context = context;
        this.executeContext = executeContext;
        this.executeScenario = executeScenario;
        this.executeFlow = executeFlow;
        this.executeCommand = executeCommand;

        // エラー
        Throwable error = null;

        // コマンド実行
        CommandResult result = null;

        try {

            result = execute(command, logger);

        } catch (Throwable t) {

            logger.debug("Error Occurred...", t);

            error = t;

            result = new CommandResult();
            result.setStatus(CommandStatus.FAIL);

            throw t;

        } finally {

            // コマンド結果を設定
            executeCommand.setCommandResult(result);

            if (!context.getOption().getNoreport()) {

                Class reporterClazz = executeCommand.getCommandInfo().getReporter();

                if (!NoneCommandReporter.class.isAssignableFrom(reporterClazz)) {

                    CommandReporter reporter = (CommandReporter) reporterClazz.newInstance();

                    // レポート出力
                    reporter.report(
                            command,
                            context,
                            executeContext,
                            executeScenario,
                            executeFlow,
                            executeCommand,
                            error);

                } else {
                    logger.debug("not exists CommandReporter.");
                }

            }

        }

    }

    /**
     * グローバル変数マップを取得.
     *
     * @return グローバル変数マップ
     */
    protected Map<String, Object> getGlobalScopeVariables() {
        return executeContext.getGlobalVariables();
    }

    /**
     * シナリオ変数マップを取得.
     *
     * @return シナリオ変数マップ
     */
    protected Map<String, Object> getScenarioScopeVariables() {
        return executeScenario.getScenarioVariables();
    }

    /**
     * Flow変数マップを取得.
     *
     * @return Flow変数マップ
     */
    protected Map<String, Object> getFlowScopeVariables() {
        return executeFlow.getFlowVariables();
    }

    /**
     * エビデンスマップ取得.
     *
     * @return エビデンスマップ
     */
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

        // 正規表現からスコープと変数名に分割して解析する
        Matcher m = EXTRACT_PATTERN.matcher(referenceVariable);

        if (m.find()) {

            // 変数参照スコープを解決
            ReferenceVariableType referenceVariableType = ReferenceVariableType.valueOfByName(m.group(1));

            if (referenceVariableType != null) {
                switch (referenceVariableType) {
                    case FIX:
                        // 固定値の場合はそのまま返却
                        return m.group(2);
                    case GLOBAL:
                        // グローバル変数から解決
                        return executeContext.getGlobalVariables().get(m.group(2));
                    case SCENARIO:
                        // シナリオ変数から解決
                        return executeScenario.getScenarioVariables().get(m.group(2));
                    case FLOW:
                        // Flow変数から解決
                        return executeFlow.getFlowVariables().get(m.group(2));
                    default:
                        // 変数解決できない場合は、エラー
                        throw new SystemException(CoreMessages.CORE_ERR_0005, m.group(1));
                }
            } else {
                throw new SystemException(CoreMessages.CORE_ERR_0005, m.group(1));
            }
        }
        return null;
    }

    /**
     * 実行中のコマンドが属するシナリオ格納ディレクトリを取得する.
     * コマンドに定義されているファイル等を参照する場合には、このメソッドで解決したパスからの相対パスを利用する.
     *
     * @return
     */
    protected String getCommandBelongScenarioDirectory() {
        String belongScenarioId = IDUtils.getInstance().extractBelongScenarioIdFromFqcn(executeCommand.getFqcn());
        return context.getOriginal().getScenarioPlacePaths().get(belongScenarioId).toString();
    }

    /**
     * 実行中のコマンドが属するシナリオ格納ディレクトリを取得する.
     * コマンドに定義されているファイル等を参照する場合には、このメソッドで解決したパスからの相対パスを利用する.
     *
     * @return
     */
    protected Path getCommandBelongScenarioDirectoryPath() {
        String belongScenarioId = IDUtils.getInstance().extractBelongScenarioIdFromFqcn(executeCommand.getFqcn());
        return context.getOriginal().getScenarioPlacePaths().get(belongScenarioId);
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
     * 基本的に、エビデンスの保存を行うパスについては、本メソッドで取得したパスを利用すること.
     *
     * @param fileExtension ファイルの拡張子
     * @return エビデンスの格納パス
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
        String evidenceId = getEvidenceBaseName();
        executeScenario.getEvidences().put(getEvidenceBaseName(), evidenceInfo);
        if (executeScenario.getFlowId2EvidenceId().containsKey(executeFlow.getFlow().getId())) {
            executeScenario.getFlowId2EvidenceId().get(executeFlow.getFlow().getId()).add(evidenceId);
        } else {
            executeScenario.getFlowId2EvidenceId().put(executeFlow.getFlow().getId(), new LinkedList<>());
            executeScenario.getFlowId2EvidenceId().get(executeFlow.getFlow().getId()).add(evidenceId);
        }
    }

    /**
     * FlowIDからファイルエビデンスのPathを参照.
     *
     * @param flowId
     * @return
     */
    protected Path referFileEvidence(String flowId) {
        if (executeScenario.getFlowId2EvidenceId().containsKey(flowId)) {
            String evidenceId = executeScenario.getFlowId2EvidenceId().get(flowId).getLast();
            EvidenceInfo evidenceInfo = executeScenario.getEvidences().get(evidenceId);
            if (evidenceInfo != null
                    && ObjectEvidenceInfo.class.isAssignableFrom(evidenceInfo.getClass())) {
                FileEvidenceInfo objectEvidenceInfo = FileEvidenceInfo.class.cast(evidenceInfo);
                return objectEvidenceInfo.getPath();
            } else {
                throw new SystemException(CoreMessages.CORE_ERR_0007, flowId);
            }
        } else {
            throw new SystemException(CoreMessages.CORE_ERR_0007, flowId);
        }
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
        String evidenceId = getEvidenceBaseName();
        executeScenario.getEvidences().put(evidenceId, evidenceInfo);
        if (executeScenario.getFlowId2EvidenceId().containsKey(executeFlow.getFlow().getId())) {
            executeScenario.getFlowId2EvidenceId().get(executeFlow.getFlow().getId()).add(evidenceId);
        } else {
            executeScenario.getFlowId2EvidenceId().put(executeFlow.getFlow().getId(), new LinkedList<>());
            executeScenario.getFlowId2EvidenceId().get(executeFlow.getFlow().getId()).add(evidenceId);
        }
    }

    /**
     * FlowIDからオブジェクトエビデンスを参照.
     * このオブジェクトはクローンであるためエビデンス原本ではない.
     *
     * @param flowId
     * @param <O>
     * @return
     */
    protected <O extends Serializable> O referObjectEvidence(String flowId) {
        if (executeScenario.getFlowId2EvidenceId().containsKey(flowId)) {
            String evidenceId = executeScenario.getFlowId2EvidenceId().get(flowId).getLast();
            EvidenceInfo evidenceInfo = executeScenario.getEvidences().get(evidenceId);
            if (evidenceInfo != null
                    && ObjectEvidenceInfo.class.isAssignableFrom(evidenceInfo.getClass())) {
                ObjectEvidenceInfo objectEvidenceInfo = ObjectEvidenceInfo.class.cast(evidenceInfo);
                O object = (O) objectEvidenceInfo.getObject();
                return SerializationUtils.clone(object);
            } else {
                throw new SystemException(CoreMessages.CORE_ERR_0008, flowId);
            }
        } else {
            throw new SystemException(CoreMessages.CORE_ERR_0008, flowId);
        }
    }

    /**
     * エビデンス名を取得する.
     *
     * @return
     */
    protected String getEvidenceBaseName() {
        return executeFlow.getFlowVariables().get(FlowScopeVariables.CURRENT_COMMAND.getName()).toString() + "_evidence";
    }

    /**
     * 設定情報を参照.
     *
     * @param <C>
     * @return 設定情報
     */
    protected <C extends Configuration> C referConfiguration(String configurationId) {

        // 参照用の設定ID
        String referConfigurationId = configurationId;

        // 設定識別子かどうか判断
        if (!IDUtils.getInstance().isFullQueryConfigurationId(configurationId)) {
            // 設定識別子を作成（シナリオID＋設定ID）
            // シナリオIDは現在実行しているシナリオから取得
            referConfigurationId = IDUtils.getInstance().createFullConfigurationId(
                    executeScenario.getInfo().getId(), referConfigurationId);
        }
        Configuration configuration =
                context.getOriginal().getConfigurations().get(configurationId);
        if (configuration == null) {
            throw new SystemException(CoreMessages.CORE_ERR_0006, configurationId);
        }

        Configuration cloneConfiguration = SerializationUtils.clone(configuration);

        // 変数バインド
        BindUtils.getInstance().bind(
                cloneConfiguration,
                executeContext.getProfileConstants(),
                executeContext.getGlobalVariables(),
                executeScenario.getScenarioVariables(),
                executeFlow.getFlowVariables()
        );

        return (C) cloneConfiguration;
    }
}
