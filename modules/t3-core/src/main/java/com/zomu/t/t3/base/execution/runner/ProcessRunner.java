package com.zomu.t.t3.base.execution.runner;

import com.zomu.t.t3.base.execution.resolver.CommandResolver;
import com.zomu.t.t3.core.execution.runner.CommandRunner;
import com.zomu.t.t3.core.model.context.BaseContext;
import com.zomu.t.t3.core.model.context.execute.ExecuteProcess;
import com.zomu.t.t3.core.model.context.execute.ExecuteScenario;
import com.zomu.t.t3.core.type.ProcessStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
public class ProcessRunner implements com.zomu.t.t3.core.execution.runner.ProcessRunner<BaseContext, ExecuteScenario, ExecuteProcess> {


    @Override
    public void execute(BaseContext context, ExecuteScenario executeScenario, ExecuteProcess executeProcess) {

        // シナリオ実行開始時間を設定
        executeProcess.setStart(LocalDateTime.now());

        try {

            // プロセス開始ログ出力
            outputStartProcessLog(context, executeScenario, executeProcess);

            // コマンド解決
            String commandId = executeProcess.getProcess().getCommand();

            // コマンド実行クラスを解決
            CommandRunner runner = CommandResolver.getInstance().getCommandRunner(commandId);

            // コマンド実行
            runner.execute(executeProcess.getProcess(),
                    context.getExecute().getGlobalVariables(),
                    executeScenario.getScenarioVariables());

            // プロセス成功
            executeProcess.setStatus(ProcessStatus.SUUCESS);

        } catch (Throwable t) {

            t.printStackTrace();

            // 発生したエラーを設定
            executeProcess.setError(t);

            // プロセス失敗
            executeProcess.setStatus(ProcessStatus.FAIL);

        } finally {

            // シナリオ実行終了時間を設定
            executeProcess.setEnd(LocalDateTime.now());

            // 所用時間を設定
            executeProcess.setDuration(Duration.between(executeProcess.getStart(), executeProcess.getEnd()));

            // プロセス終了ログ出力
            outputEndProcessLog(context, executeScenario, executeProcess);

        }


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
        sb.append("Start Process.\n");
        sb.append("Scenario ID         : ");
        sb.append(executeScenario.getInfo().getId());
        sb.append("\n");
        sb.append("Process  ID         : ");
        sb.append(executeProcess.getProcess().getId());
        sb.append("\n");
        sb.append("Execute Process ID  : ");
        sb.append(executeProcess.getExecuteProcessId());
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------------");
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
        sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("End Process.\n");
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
        if (executeProcess.getStatus() == ProcessStatus.SUUCESS) {
            log.info(sb.toString());
        } else if (executeProcess.getStatus() == ProcessStatus.FAIL) {
            log.error(sb.toString());
        } else {
            log.warn(sb.toString());
        }

    }


}
