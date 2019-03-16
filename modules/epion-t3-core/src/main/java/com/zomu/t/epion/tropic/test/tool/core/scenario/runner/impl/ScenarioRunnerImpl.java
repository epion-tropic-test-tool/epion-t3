package com.zomu.t.epion.tropic.test.tool.core.scenario.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.exception.ScenarioNotFoundException;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.scenario.reporter.impl.ScenarioReporterImpl;
import com.zomu.t.epion.tropic.test.tool.core.flow.resolver.impl.FlowRunnerResolverImpl;
import com.zomu.t.epion.tropic.test.tool.core.scenario.runner.ScenarioRunner;
import com.zomu.t.epion.tropic.test.tool.core.flow.model.FlowResult;
import com.zomu.t.epion.tropic.test.tool.core.flow.runner.FlowRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Flow;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.T3Base;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioExecuteStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioScopeVariables;
import com.zomu.t.epion.tropic.test.tool.core.util.BindUtils;
import com.zomu.t.epion.tropic.test.tool.core.util.ExecutionFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * シナリオ実行処理.
 *
 * @author takashno
 */
@Slf4j
public class ScenarioRunnerImpl implements ScenarioRunner<Context, ExecuteContext> {

    /**
     * {@inheritDoc}
     *
     * @param context コンテキスト
     */
    @Override
    public void execute(final Context context, final ExecuteContext executeContext) {

        // 実行シナリオの選択
        T3Base scenario = context.getOriginal().getOriginals().get(context.getOption().getTarget());

        if (scenario == null) {
            throw new ScenarioNotFoundException(context.getOption().getTarget());
        }

        ExecuteScenario executeScenario = new ExecuteScenario();
        executeScenario.setInfo(scenario.getInfo());
        executeScenario.setFqsn(scenario.getInfo().getId());
        executeContext.getScenarios().add(executeScenario);

        // エラー
        Throwable error = null;

        try {

            // シナリオ実行開始時間を設定
            executeScenario.setStart(LocalDateTime.now());

            // シナリオ開始ログ出力
            outputStartScenarioLog(context, executeScenario);

            // 結果ディレクトリの作成
            ExecutionFileUtils.createScenarioResultDirectory(context, executeContext, executeScenario);

            // シナリオスコープの変数を設定
            settingScenarioVariables(context, executeScenario);

            FlowResult flowResult = null;

            boolean exitFlg = false;

            // 全てのフローを実行
            for (Flow flow : scenario.getFlows()) {

                if (flowResult != null) {
                    // 前Flowの結果によって処理を振り分ける
                    switch (flowResult.getStatus()) {
                        case NEXT:
                            // 単純に次のFlowへ遷移
                            log.debug("Execute Next Flow.");
                            break;
                        case CHOICE:
                            log.debug("Choice Execute Next Flow.");
                            // 指定された後続Flowへ遷移
                            if (StringUtils.equals(flowResult.getChoiceId(), flow.getId())) {
                                // 合致したため実行する
                                log.debug("Find To Be Executed Flow.");
                            } else {
                                // SKIP扱いとする
                                log.debug("Can't Find Execute Flow. -> SKIP");
                                // TODO:ちょっと微妙だな・・・
                                ExecuteFlow executeFlow = new ExecuteFlow();
                                executeFlow.setStatus(FlowStatus.SKIP);
                                executeFlow.setFlow(flow);
                                executeScenario.getFlows().add(executeFlow);
                                // 次のループまで
                                continue;
                            }
                            break;
                        case EXIT:
                            // 即時終了
                            log.debug("Force Exit Scenario.");
                            exitFlg = true;
                            break;
                    }
                }

                if (exitFlg) {
                    // 即時終了のためループを抜ける
                    break;
                }

                // Flowの実行処理を解決
                FlowRunner runner = FlowRunnerResolverImpl.getInstance().getFlowRunner(flow.getType());

                // バインド
                bind(context, executeContext, executeScenario, flow);

                // 実行
                flowResult = runner.execute(
                        context,
                        executeContext,
                        executeScenario,
                        flow,
                        LoggerFactory.getLogger("FlowLog"));

                ExecuteFlow executeFlow = executeScenario.getFlows().get(executeScenario.getFlows().size() - 1);

                if (FlowStatus.ERROR == executeFlow.getStatus()) {
                    log.error("Error Occurred...");
                    // シナリオ失敗
                    executeScenario.setStatus(ScenarioExecuteStatus.ERROR);
                    break;
                }

            }

            // プロセス成功
            if (executeScenario.getStatus() != ScenarioExecuteStatus.ERROR) {
                executeScenario.setStatus(ScenarioExecuteStatus.SUCCESS);
            }

        } catch (Throwable t) {

            log.debug("Error Occurred...", t);

            // 発生したエラーを設定
            error = t;
            executeScenario.setError(t);

            // シナリオ失敗
            executeScenario.setStatus(ScenarioExecuteStatus.ERROR);

        } finally {

            // 掃除
            cleanScenarioVariables(context, executeScenario);

            // シナリオ実行終了時間を設定
            executeScenario.setEnd(LocalDateTime.now());

            // 所用時間を設定
            executeScenario.setDuration(Duration.between(executeScenario.getStart(), executeScenario.getEnd()));

            // シナリオ終了ログ出力
            outputEndScenarioLog(context, executeScenario);

            // レポート出力
            if (!context.getOption().getNoreport()) {
                report(context, executeContext, executeScenario, error);
            }
        }

    }


    /**
     * Flowに対して、変数をバインドする.
     *
     * @param context
     * @param executeScenario
     * @param flow
     */
    private void bind(final Context context,
                      final ExecuteContext executeContext,
                      final ExecuteScenario executeScenario,
                      final Flow flow) {

        BindUtils.getInstance().bind(
                flow,
                executeContext.getProfileConstants(),
                executeContext.getGlobalVariables(),
                executeScenario.getScenarioVariables(),
                null);
    }


    /**
     * シナリオスコープの変数を設定する.
     *
     * @param context
     * @param scenario
     */
    private void settingScenarioVariables(final Context context, final ExecuteScenario scenario) {
        scenario.getScenarioVariables().put(
                ScenarioScopeVariables.SCENARIO_DIR.getName(),
                context.getOriginal().getScenarioPlacePaths().get(scenario.getInfo().getId()));
        scenario.getScenarioVariables().put(
                ScenarioScopeVariables.EVIDENCE_DIR.getName(),
                scenario.getEvidencePath());
        scenario.getScenarioVariables().put(
                ScenarioScopeVariables.CURRENT_SCENARIO.getName(),
                scenario.getFqsn());
    }

    /**
     * シナリオスコープの変数を掃除する.
     *
     * @param context
     * @param scenario
     */
    private void cleanScenarioVariables(final Context context, final ExecuteScenario scenario) {
        scenario.getScenarioVariables().remove(
                ScenarioScopeVariables.SCENARIO_DIR.getName());
        scenario.getScenarioVariables().remove(
                ScenarioScopeVariables.EVIDENCE_DIR.getName());
        scenario.getScenarioVariables().remove(
                ScenarioScopeVariables.CURRENT_SCENARIO.getName());
        scenario.getScenarioVariables().entrySet().forEach(x -> {
            if (x.getKey().contains(ExecuteScenario.FLOW_START_VARIABLE_SUFFIX)
                    || x.getKey().contains(ExecuteScenario.FLOW_END_VARIABLE_SUFFIX)) {
                scenario.getScenarioVariables().remove(x.getKey());
            }
        });
    }

    /**
     * 結果ディレクトリが未作成であった場合に、作成する.
     *
     * @param context
     */
    private void createResultDirectory(final Context context, final ExecuteContext executeContext, final ExecuteScenario scenario) {
        ExecutionFileUtils.createResultDirectory(context, executeContext);
    }

    /**
     * レポート出力.
     *
     * @param context         コンテキスト
     * @param executeContext  実行情報
     * @param executeScenario シナリオ実行情報
     * @param t               エラー
     */
    private void report(
            final Context context,
            final ExecuteContext executeContext,
            final ExecuteScenario executeScenario,
            final Throwable t) {
        ScenarioReporterImpl.getInstance().report(
                context, executeContext, executeScenario, t);
    }


    /**
     * シナリオ開始ログ出力.
     *
     * @param context
     * @param scenario
     */
    protected void outputStartScenarioLog(Context context, ExecuteScenario scenario) {
        log.info("######################################################################################");
        log.info("Start Scenario.");
        log.info("Scenario ID         : {}", scenario.getInfo().getId());
        log.info("Execute Scenario ID : {}", scenario.getExecuteScenarioId());
        log.info("######################################################################################");
    }

    /**
     * シナリオ終了ログ出力.
     *
     * @param context
     * @param scenario
     */
    protected void outputEndScenarioLog(Context context, ExecuteScenario scenario) {
        if (scenario.getStatus() == ScenarioExecuteStatus.SUCCESS) {
            log.info("######################################################################################");
            log.info("End Scenario.");
            log.info("Scenario ID         : {}", scenario.getInfo().getId());
            log.info("Execute Scenario ID : {}", scenario.getExecuteScenarioId());
            log.info("######################################################################################");
        } else if (scenario.getStatus() == ScenarioExecuteStatus.ERROR) {
            log.error("######################################################################################");
            log.error("End Scenario.");
            log.error("Scenario ID         : {}", scenario.getInfo().getId());
            log.error("Execute Scenario ID : {}", scenario.getExecuteScenarioId());
            log.error("######################################################################################");
        } else {
            log.warn("######################################################################################");
            log.warn("End Scenario.");
            log.warn("Scenario ID         : {}", scenario.getInfo().getId());
            log.warn("Execute Scenario ID : {}", scenario.getExecuteScenarioId());
            log.warn("######################################################################################");
        }

    }

}
