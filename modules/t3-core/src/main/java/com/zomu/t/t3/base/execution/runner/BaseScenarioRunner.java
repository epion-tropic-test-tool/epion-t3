package com.zomu.t.t3.base.execution.runner;

import com.zomu.t.t3.base.message.BaseMessages;
import com.zomu.t.t3.core.exception.SystemException;
import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.core.context.execute.ExecuteProcess;
import com.zomu.t.t3.core.context.execute.ExecuteScenario;
import com.zomu.t.t3.core.type.ScenarioExecuteStatus;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author takashno
 */
@Slf4j
public class BaseScenarioRunner implements com.zomu.t.t3.core.execution.runner.ScenarioRunner<BaseContext> {

    /**
     * プロセス実行処理.
     */
    private final com.zomu.t.t3.core.execution.runner.ProcessRunner processRunner;

    /**
     * コンストラクタ.
     */
    public BaseScenarioRunner() {
        this.processRunner = new BaseProcessRunner();
    }


    /**
     * {@inheritDoc}
     *
     * @param context
     */
    @Override
    public void execute(BaseContext context) {

        // 存在チェック
        if (context.getExecute() == null || context.getExecute().getScenarios() == null) {
            // システムエラー扱い（きちんとしたルートでの実行ではない）
            throw new SystemException(BaseMessages.BASE_ERR_9001);
        }


        // 全てのシナリオを実行する
        for (ExecuteScenario scenario : context.getExecute().getScenarios()) {

            runScenario(context, scenario);

        }


    }

    private void runScenario(final BaseContext context, final ExecuteScenario scenario) {

        // シナリオ実行開始時間を設定
        scenario.setStart(LocalDateTime.now());

        try {

            // シナリオ開始ログ出力
            outputStartScenarioLog(context, scenario);

            for (ExecuteProcess process : scenario.getProcesses()) {
                this.processRunner.execute(context, scenario, process);
            }

        } finally {
            // シナリオ実行終了時間を設定
            scenario.setEnd(LocalDateTime.now());

            // 所用時間を設定
            scenario.setDuration(Duration.between(scenario.getStart(), scenario.getEnd()));

            // シナリオ終了ログ出力
            outputEndScenarioLog(context, scenario);
        }

    }

    /**
     * シナリオ開始ログ出力.
     *
     * @param context
     * @param scenario
     */
    protected void outputStartScenarioLog(BaseContext context, ExecuteScenario scenario) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("Start Scenario.\n");
        sb.append("Scenario ID         : ");
        sb.append(scenario.getInfo().getId());
        sb.append("\n");
        sb.append("Execute Scenario ID : ");
        sb.append(scenario.getExecuteScenarioId());
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------------");
        log.info(sb.toString());
    }

    /**
     * シナリオ終了ログ出力.
     *
     * @param context
     * @param scenario
     */
    protected void outputEndScenarioLog(BaseContext context, ExecuteScenario scenario) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--------------------------------------------------------------------------------------\n");
        sb.append("End Scenario.\n");
        sb.append("Scenario ID         : ");
        sb.append(scenario.getInfo().getId());
        sb.append("\n");
        sb.append("Execute Scenario ID : ");
        sb.append(scenario.getExecuteScenarioId());
        sb.append("\n");
        sb.append("--------------------------------------------------------------------------------------");
        if (scenario.getStatus() == ScenarioExecuteStatus.SUUCESS) {
            log.info(sb.toString());
        } else if (scenario.getStatus() == ScenarioExecuteStatus.FAIL) {
            log.error(sb.toString());
        } else {
            log.warn(sb.toString());
        }

    }

}
