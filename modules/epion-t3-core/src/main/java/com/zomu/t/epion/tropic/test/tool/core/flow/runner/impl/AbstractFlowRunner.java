package com.zomu.t.epion.tropic.test.tool.core.flow.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.flow.model.FlowResult;
import com.zomu.t.epion.tropic.test.tool.core.flow.runner.FlowRunner;
import com.zomu.t.epion.tropic.test.tool.core.holder.FlowLog;
import com.zomu.t.epion.tropic.test.tool.core.holder.FlowLoggingHolder;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Flow;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowScopeVariables;
import com.zomu.t.epion.tropic.test.tool.core.type.FlowStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @param <CONTEXT>
 * @param <EXECUTESCENARIO>
 * @param <FLOW>
 */
@Slf4j
public abstract class AbstractFlowRunner<CONTEXT extends Context, EXECUTESCENARIO extends ExecuteScenario, EXECUTE_FLOW extends ExecuteFlow, FLOW extends Flow>
        implements FlowRunner<CONTEXT, EXECUTESCENARIO, FLOW> {

    /**
     * {@inheritDoc}
     */
    @Override
    public FlowResult execute(CONTEXT context, EXECUTESCENARIO executeScenario, FLOW flow, Logger logger) {

        // process実行情報を作成
        EXECUTE_FLOW executeFlow = getExecuteFlowInstance();
        executeScenario.getFlows().add(executeFlow);
        executeFlow.setFlow(flow);

        // Flow実行開始時間を設定
        executeFlow.setStart(LocalDateTime.now());

        FlowResult flowResult = null;

        try {

            // Flow開始ログ出力
            outputStartFlowLog(
                    context,
                    executeScenario,
                    executeFlow);

            // シナリオスコープ変数の設定
            settingFlowVariables(
                    context,
                    executeScenario,
                    executeFlow);

            // 実行
            flowResult = execute(
                    context,
                    executeScenario,
                    executeFlow,
                    flow,
                    logger);

            // プロセス成功
            executeFlow.setStatus(FlowStatus.SUCCESS);

        } catch (Throwable t) {

            // 解析用
            log.debug("error occurred...", t);

            // 発生したエラーを設定
            executeFlow.setError(t);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.flush();
            executeFlow.setStackTrace(sw.toString());

            // プロセス失敗
            executeFlow.setStatus(FlowStatus.ERROR);

            // エラー処理
            onError(context, executeScenario, executeFlow,
                    flow, t, logger);

        } finally {

            // 掃除
            cleanFlowVariables(context, executeScenario, executeFlow);

            // シナリオ実行終了時間を設定
            executeFlow.setEnd(LocalDateTime.now());

            // 所用時間を設定
            executeFlow.setDuration(Duration.between(
                    executeFlow.getStart(), executeFlow.getEnd()));

            // プロセスのログを収集
            List<FlowLog> flowLogs = SerializationUtils.clone(FlowLoggingHolder.get());
            executeFlow.setFlowLogs(flowLogs);

            // プロセスのログは収集し終えたらクリアする（ThreadLocalにて保持）
            FlowLoggingHolder.clear();

            // プロセス終了ログ出力
            outputEndFlowLog(context, executeScenario, executeFlow);

            // エラー処理
            onFinally(context, executeScenario, executeFlow,
                    flow, logger);

        }

        return flowResult;
    }

    /**
     * @return
     */
    private EXECUTE_FLOW getExecuteFlowInstance() {
        try {
            Class<?> clazz = this.getClass();
            Type type = clazz.getGenericSuperclass();
            ParameterizedType pt = (ParameterizedType) type;
            Type[] actualTypeArguments = pt.getActualTypeArguments();
            Class<?> entityClass = (Class<?>) actualTypeArguments[2];
            return (EXECUTE_FLOW) entityClass.newInstance();
        } catch (ReflectiveOperationException e) {
            // TODO:ErrorProcess
            throw new RuntimeException(e);
        }
    }


    /**
     * @param context
     * @param executeScenario
     * @param executeFlow
     * @param flow
     */
    protected abstract FlowResult execute(
            CONTEXT context,
            EXECUTESCENARIO executeScenario,
            EXECUTE_FLOW executeFlow,
            FLOW flow,
            Logger logger);


    /**
     * エラー処理を行う.
     * この処理は、Flowの処理結果が失敗の場合に実行される.
     *
     * @param context
     * @param executeScenario
     * @param executeFlow
     * @param flow
     * @param t
     */
    protected void onError(
            CONTEXT context,
            EXECUTESCENARIO executeScenario,
            EXECUTE_FLOW executeFlow,
            FLOW flow,
            Throwable t,
            Logger logger) {
        // 必要に応じてオーバーライド実装すること.
    }

    /**
     * 終了処理を行う.
     * この処理は、Flowの処理結果が成功・失敗に関わらず実行される.
     *
     * @param context
     * @param executeScenario
     * @param executeFlow
     * @param flow
     */
    protected void onFinally(
            CONTEXT context,
            EXECUTESCENARIO executeScenario,
            EXECUTE_FLOW executeFlow,
            FLOW flow,
            Logger logger) {
        // 必要に応じてオーバーライド実装すること.
    }

    ;

    /**
     * シナリオスコープの変数を設定する.
     * プロセス実行時に指定を行うべきシナリオスコープ変数の設定処理.
     *
     * @param context         コンテキスト
     * @param executeScenario シナリオ実行情報
     * @param executeFlow     FLOW実行情報
     */
    private void settingFlowVariables(final CONTEXT context,
                                      final EXECUTESCENARIO executeScenario,
                                      final ExecuteFlow executeFlow) {

        // 現在の処理Flow
//        executeFlow.getFlowVariables().put(
//                ScenarioScopeVariables.CURRENT_FLOW.getName(),
//                "TODO?");

        // 現在の処理FLOWの実行ID
        executeFlow.getFlowVariables().put(
                FlowScopeVariables.CURRENT_FLOW_EXECUTE_ID.getName(),
                executeFlow.getExecuteId());
    }

    /**
     * シナリオスコープの変数を掃除する.
     * プロセス実行時にのみ指定すべきシナリオスコープの変数を確実に除去するための処理.
     *
     * @param context         コンテキスト
     * @param executeScenario シナリオ実行情報
     * @param executeFlow     FLOW実行情報
     */
    private void cleanFlowVariables(final CONTEXT context,
                                    final EXECUTESCENARIO executeScenario,
                                    final ExecuteFlow executeFlow) {

        // 現在の処理Flow
//        executeFlow.getFlowVariables().put(
//                FlowScopeVariables.CURRENT_FLOW.getName(),
//                "TODO?");

        // 現在の処理FLOWの実行ID
        executeFlow.getFlowVariables().remove(
                FlowScopeVariables.CURRENT_FLOW_EXECUTE_ID.getName());
    }


    /**
     * Flow開始ログ出力.
     *
     * @param context         コンテキスト
     * @param executeScenario 実行シナリオ
     * @param executeFlow     実行Flow
     */
    protected void outputStartFlowLog(CONTEXT context, ExecuteScenario executeScenario, ExecuteFlow executeFlow) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("<<Start Flow>>\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Execute Flow ID  : ");
        sb.append(executeFlow.getExecuteId());
        sb.append("\n");
        //sb.append("--------------------------------------------------------------------------------------");
        log.info(sb.toString());
    }

    /**
     * Flow終了ログ出力.
     *
     * @param context         コンテキスト
     * @param executeScenario 実行シナリオ
     * @param executeFlow     実行Flow
     */
    protected void outputEndFlowLog(CONTEXT context, ExecuteScenario executeScenario, ExecuteFlow executeFlow) {
        StringBuilder sb = new StringBuilder();
        //sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("\n<<End Flow>>\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Execute Flow ID  : ");
        sb.append(executeFlow.getExecuteId());
        sb.append("\n");
        sb.append("Flow Status      : ");
        sb.append(executeFlow.getStatus().name());
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------------");
        if (executeFlow.getStatus() == FlowStatus.SUCCESS) {
            log.info(sb.toString());
        } else if (executeFlow.getStatus() == FlowStatus.ERROR) {
            log.error(sb.toString());
        } else {
            log.warn(sb.toString());
        }

    }

}
