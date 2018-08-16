package com.zomu.t.epion.tropic.test.tool.core.execution.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.ProcessRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * プロセス実行処理.
 */
@Slf4j
@Deprecated
public class BaseProcessRunner implements ProcessRunner<BaseContext, ExecuteScenario, ExecuteCommand> {
    @Override
    public void execute(BaseContext context, ExecuteScenario executeScenario, ExecuteCommand executeProcess) {

    }


//    /**
//     * @param context
//     * @param executeScenario
//     * @param executeProcess
//     */
//    @Override
//    public void execute(BaseContext context, ExecuteScenario executeScenario, ExecuteCommand executeProcess) {
//
//        // シナリオ実行開始時間を設定
//        executeProcess.setStart(LocalDateTime.now());
//
//        try {
//
//            // プロセス開始ログ出力
//            outputStartProcessLog(
//                    context,
//                    executeScenario,
//                    executeProcess);
//
//            // シナリオスコープ変数の設定
//            settingScenarioVariables(
//                    context,
//                    executeScenario,
//                    executeProcess);
//
//            // コマンド解決
//            String commandId = executeProcess.getProcess().getCommand();
//
//            // コマンド実行クラスを解決
//            CommandRunner runner = BaseCommandRunnerResolver.getInstance().getCommandRunner(commandId);
//
//            // 変数バインド
//            bind(
//                    context,
//                    executeScenario,
//                    executeProcess);
//
//            // コマンド実行
//            runner.execute(executeProcess.getProcess(),
//                    context.getExecuteContext().getGlobalVariables(),
//                    executeScenario.getScenarioVariables(),
//                    executeScenario.getEvidences(),
//                    LoggerFactory.getLogger("ProcessLog"));
//
//            // プロセス成功
//            executeProcess.setStatus(CommandStatus.SUCCESS);
//
//        } catch (Throwable t) {
//
//            // 発生したエラーを設定
//            executeProcess.setError(t);
//            StringWriter sw = new StringWriter();
//            PrintWriter pw = new PrintWriter(sw);
//            t.printStackTrace(pw);
//            pw.flush();
//            executeProcess.setStackTrace(sw.toString());
//
//            // プロセス失敗
//            executeProcess.setStatus(CommandStatus.FAIL);
//
//        } finally {
//
//            // 掃除
//            cleanScenarioVariables(context, executeScenario, executeProcess);
//
//            // シナリオ実行終了時間を設定
//            executeProcess.setEnd(LocalDateTime.now());
//
//            // 所用時間を設定
//            executeProcess.setDuration(Duration.between(executeProcess.getStart(), executeProcess.getEnd()));
//
//            // プロセス終了ログ出力
//            outputEndProcessLog(context, executeScenario, executeProcess);
//
//            // プロセスのログを収集
//            List<ProcessLog> processLogs = SerializationUtils.clone(ProcessLoggingHolder.get());
//            executeProcess.setProcessLogs(processLogs);
//
//            // プロセスのログは収集し終えたらクリアする（ThreadLocalにて保持）
//            ProcessLoggingHolder.clear();
//
//        }
//
//
//    }
//
//    /**
//     * プロセスに対して、変数をバインドする.
//     *
//     * @param context
//     * @param executeScenario
//     * @param executeProcess
//     */
//    private void bind(final BaseContext context,
//                      final ExecuteScenario executeScenario,
//                      final ExecuteCommand executeProcess) {
//
//        final Map<String, String> profiles = new ConcurrentHashMap<>();
//
//        if (StringUtils.isNotEmpty(context.getOption().getProfile())) {
//            // プロファイルを抽出
//            Arrays.stream(context.getOption().getProfile().split(","))
//                    .forEach(x -> {
//                        if (context.getOriginal().getProfiles().containsKey(x)) {
//                            profiles.putAll(context.getOriginal().getProfiles().get(x));
//                        } else {
//
//                        }
//                    });
//        }
//
//        BindUtils.getInstance().bind(
//                executeProcess.getProcess(),
//                profiles,
//                context.getExecuteContext().getGlobalVariables(),
//                executeScenario.getScenarioVariables());
//    }
//
//    /**
//     * シナリオスコープの変数を設定する.
//     * プロセス実行時に指定を行うべきシナリオスコープ変数の設定処理.
//     *
//     * @param context
//     * @param scenario
//     */
//    private void settingScenarioVariables(final BaseContext context,
//                                          final ExecuteScenario scenario,
//                                          final ExecuteCommand executeProcess) {
//
//        // 現在の処理プロセス
//        scenario.getScenarioVariables().put(
//                ScenarioScopeVariables.CURRENT_PROCESS.getName(),
//                executeProcess.getFqpn());
//
//        // 現在の処理プロセスの実行ID
//        scenario.getScenarioVariables().put(
//                ScenarioScopeVariables.CURRENT_PROCESS_EXECUTEID.getName(),
//                executeProcess.getExecuteId());
//    }
//
//    /**
//     * シナリオスコープの変数を掃除する.
//     * プロセス実行時にのみ指定すべきシナリオスコープの変数を確実に除去するための処理.
//     *
//     * @param context
//     * @param scenario
//     */
//    private void cleanScenarioVariables(final BaseContext context,
//                                        final ExecuteScenario scenario,
//                                        final ExecuteCommand executeProcess) {
//
//        // 現在の処理プロセス
//        scenario.getScenarioVariables().remove(
//                ScenarioScopeVariables.CURRENT_PROCESS.getName());
//
//        // 現在の処理プロセスの実行ID
//        scenario.getScenarioVariables().remove(
//                ScenarioScopeVariables.CURRENT_PROCESS_EXECUTEID.getName());
//    }
//
//
//    /**
//     * プロセス開始ログ出力.
//     *
//     * @param context
//     * @param executeScenario
//     * @param executeProcess
//     */
//    private void outputStartProcessLog(BaseContext context, ExecuteScenario executeScenario, ExecuteCommand executeProcess) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("\n--------------------------------------------------------------------------------------\n");
//        sb.append("<<Start Process>>\n");
//        sb.append("Scenario ID         : ");
//        sb.append(executeScenario.getInfo().getId());
//        sb.append("\n");
//        sb.append("Process  ID         : ");
//        sb.append(executeProcess.getProcess().getId());
//        sb.append("\n");
//        sb.append("Execute Process ID  : ");
//        sb.append(executeProcess.getExecuteId());
//        sb.append("\n");
//        //sb.append("--------------------------------------------------------------------------------------");
//        log.info(sb.toString());
//    }
//
//    /**
//     * プロセス終了ログ出力.
//     *
//     * @param context
//     * @param executeScenario
//     * @param executeProcess
//     */
//    private void outputEndProcessLog(BaseContext context, ExecuteScenario executeScenario, ExecuteCommand executeProcess) {
//        StringBuilder sb = new StringBuilder();
//        //sb.append("\n--------------------------------------------------------------------------------------\n");
//        sb.append("\n<<End Process>>\n");
//        sb.append("Scenario ID         : ");
//        sb.append(executeScenario.getInfo().getId());
//        sb.append("\n");
//        sb.append("Process ID          : ");
//        sb.append(executeProcess.getProcess().getId());
//        sb.append("\n");
//        sb.append("Execute Process ID  : ");
//        sb.append(executeProcess.getExecuteId());
//        sb.append("\n");
//        sb.append("Process Status      : ");
//        sb.append(executeProcess.getStatus().name());
//        sb.append("\n");
//        sb.append("--------------------------------------------------------------------------------------");
//        if (executeProcess.getStatus() == CommandStatus.SUCCESS) {
//            log.info(sb.toString());
//        } else if (executeProcess.getStatus() == CommandStatus.FAIL) {
//            log.error(sb.toString());
//        } else {
//            log.warn(sb.toString());
//        }
//
//    }


}
