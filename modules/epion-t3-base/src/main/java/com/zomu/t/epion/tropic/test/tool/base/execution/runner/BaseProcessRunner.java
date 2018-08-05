package com.zomu.t.epion.tropic.test.tool.base.execution.runner;

import com.zomu.t.epion.tropic.test.tool.base.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.base.execution.resolver.BaseCommandResolver;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteProcess;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.ProcessRunner;
import com.zomu.t.epion.tropic.test.tool.core.holder.ProcessLog;
import com.zomu.t.epion.tropic.test.tool.core.holder.ProcessLoggingHolder;
import com.zomu.t.epion.tropic.test.tool.core.type.ProcessStatus;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioScopeVariables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
public class BaseProcessRunner implements ProcessRunner<BaseContext, ExecuteScenario, ExecuteProcess> {


    @Override
    public void execute(BaseContext context, ExecuteScenario executeScenario, ExecuteProcess executeProcess) {

        // シナリオ実行開始時間を設定
        executeProcess.setStart(LocalDateTime.now());

        try {

            // プロセス開始ログ出力
            outputStartProcessLog(
                    context,
                    executeScenario,
                    executeProcess);

            // シナリオスコープ変数の設定
            settingScenarioVariables(
                    context,
                    executeScenario,
                    executeProcess);

            // コマンド解決
            String commandId = executeProcess.getProcess().getCommand();

            // コマンド実行クラスを解決
            CommandRunner runner = BaseCommandResolver.getInstance().getCommandRunner(commandId);

            // コマンド実行
            runner.execute(executeProcess.getProcess(),
                    context.getExecuteContext().getGlobalVariables(),
                    executeScenario.getScenarioVariables(),
                    executeScenario.getEvidences(),
                    LoggerFactory.getLogger("ProcessLog"));

            // プロセス成功
            executeProcess.setStatus(ProcessStatus.SUCCESS);

        } catch (Throwable t) {

            // 発生したエラーを設定
            executeProcess.setError(t);
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.flush();
            executeProcess.setStackTrace(sw.toString());

            // プロセス失敗
            executeProcess.setStatus(ProcessStatus.FAIL);

        } finally {

            // 掃除
            cleanScenarioVariables(context, executeScenario, executeProcess);

            // シナリオ実行終了時間を設定
            executeProcess.setEnd(LocalDateTime.now());

            // 所用時間を設定
            executeProcess.setDuration(Duration.between(executeProcess.getStart(), executeProcess.getEnd()));

            // プロセス終了ログ出力
            outputEndProcessLog(context, executeScenario, executeProcess);

            // プロセスのログを収集
            List<ProcessLog> processLogs = SerializationUtils.clone(ProcessLoggingHolder.get());
            executeProcess.setProcessLogs(processLogs);

            // プロセスのログは収集し終えたらクリアする（ThreadLocalにて保持）
            ProcessLoggingHolder.clear();

        }


    }

    /**
     * シナリオスコープの変数を設定する.
     *
     * @param context
     * @param scenario
     */
    private void settingScenarioVariables(final BaseContext context,
                                          final ExecuteScenario scenario,
                                          final ExecuteProcess executeProcess) {
        scenario.getScenarioVariables().put(
                ScenarioScopeVariables.CURRENT_PROCESS.getName(),
                executeProcess.getFqpn());
        scenario.getScenarioVariables().put(
                ScenarioScopeVariables.CURRENT_PROCESS_EXECUTEID.getName(),
                executeProcess.getExecuteProcessId());
    }

    /**
     * シナリオスコープの変数を設定する.
     *
     * @param context
     * @param scenario
     */
    private void cleanScenarioVariables(final BaseContext context,
                                        final ExecuteScenario scenario,
                                        final ExecuteProcess executeProcess) {
        scenario.getScenarioVariables().remove(
                ScenarioScopeVariables.CURRENT_PROCESS.getName());
        scenario.getScenarioVariables().remove(
                ScenarioScopeVariables.CURRENT_PROCESS_EXECUTEID.getName());
    }


    /**
     * プロセス開始ログ出力.
     *
     * @param context
     * @param executeScenario
     * @param executeProcess
     */
    private void outputStartProcessLog(BaseContext context, ExecuteScenario executeScenario, ExecuteProcess executeProcess) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("<<Start Process>>\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Process  ID         : ");
        sb.append(executeProcess.getProcess().getId());
        sb.append("\n");
        sb.append("Execute Process ID  : ");
        sb.append(executeProcess.getExecuteProcessId());
        sb.append("\n");
        //sb.append("--------------------------------------------------------------------------------------");
        log.info(sb.toString());
    }

    /**
     * プロセス終了ログ出力.
     *
     * @param context
     * @param executeScenario
     * @param executeProcess
     */
    private void outputEndProcessLog(BaseContext context, ExecuteScenario executeScenario, ExecuteProcess executeProcess) {
        StringBuilder sb = new StringBuilder();
        //sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("\n<<End Process>>\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Process ID          : ");
        sb.append(executeProcess.getProcess().getId());
        sb.append("\n");
        sb.append("Execute Process ID  : ");
        sb.append(executeProcess.getExecuteProcessId());
        sb.append("\n");
        sb.append("Process Status      : ");
        sb.append(executeProcess.getStatus().name());
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------------");
        if (executeProcess.getStatus() == ProcessStatus.SUCCESS) {
            log.info(sb.toString());
        } else if (executeProcess.getStatus() == ProcessStatus.FAIL) {
            log.error(sb.toString());
        } else {
            log.warn(sb.toString());
        }

    }


}
