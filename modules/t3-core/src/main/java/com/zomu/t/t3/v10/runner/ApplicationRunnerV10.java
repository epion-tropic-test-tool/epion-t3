package com.zomu.t.t3.v10.runner;

import com.zomu.t.t3.core.annotation.ApplicationVersion;
import com.zomu.t.t3.core.runner.ApplicationRunner;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.core.type.ExitCode;
import com.zomu.t.t3.core.type.ProcessStatus;
import com.zomu.t.t3.v10.model.context.ContextV10;
import com.zomu.t.t3.v10.model.execute.ExecuteContextV10;
import com.zomu.t.t3.v10.model.execute.ExecuteFlow;
import com.zomu.t.t3.v10.model.execute.ExecuteProcess;
import com.zomu.t.t3.v10.model.scenario.Flow;
import com.zomu.t.t3.v10.model.scenario.Process;
import com.zomu.t.t3.v10.model.scenario.T3Base;
import com.zomu.t.t3.v10.parser.ScenarioParserV10;
import com.zomu.t.t3.v10.type.Args;
import com.zomu.t.t3.v10.type.FlowType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.SerializationUtils;

import java.nio.file.Paths;
import java.util.Arrays;

@ApplicationVersion(version = "v1.0")
@Slf4j
public class ApplicationRunnerV10 implements ApplicationRunner {

    private static final Options OPTIONS = new Options();

    static {
        Arrays.stream(Args.values()).forEach(
                x -> {
                    if (x.isRequired()) {
                        OPTIONS.addRequiredOption(x.getShortName(), x.getLongName(), x.isHasArg(), x.getDescription());
                    } else {
                        OPTIONS.addOption(x.getShortName(), x.getLongName(), x.isHasArg(), x.getDescription());
                    }
                }
        );
    }

    @Override
    public void execute(String[] args) {

        // バージョンの解決
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(OPTIONS, args);
        } catch (ParseException e) {
            log.error("args error...", e);
            System.exit(ExitCode.ERROR.getExitCode());
        }


        String rootPath = cmd.getOptionValue(Args.ROOT_PATH.getShortName());
        String target = cmd.getOptionValue(Args.SCENARIO.getShortName());


        // コンテキストの生成
        ContextV10 context = new ContextV10(
                Paths.get(rootPath));

        // シナリオの解析（パース処理）
        ScenarioParser scenarioParser = new ScenarioParserV10();
        scenarioParser.parse(context);

        // 実行シナリオの選択
        T3Base scenario = context.getOriginal().getScenarios().get(target);

        // 実行フローの構築
        ExecuteContextV10 executeContextV10 = new ExecuteContextV10();
        buildExecuteFlow(context, executeContextV10, scenario);


        System.out.println(scenario);
    }

    private void buildExecuteFlow(final ContextV10 context, final ExecuteContextV10 executeContextV10, final T3Base scenario) {

        ExecuteFlow flow = new ExecuteFlow();
        executeContextV10.getFlows().add(flow);

        for (Flow f : scenario.getFlows()) {

            FlowType flowType = FlowType.valueOfByValue(f.getType());

            if (flowType != null) {
                switch (flowType) {
                    case PROCESS:
                        // プロセスの場合は実行フローに追加していく

                        // 優先度-1:自シナリオ内を参照
                        Process process = scenario.getProcesses().stream().filter(x -> x.getId().equals(f.getRef())).findFirst().orElse(null);
                        if (process == null) {
                            // 優先度-2:全体のプロセスから参照
                            process = context.getOriginal().getProcesses().get(f.getRef());
                        }
                        if (process == null) {
                            log.error("not found process: {}", f.getRef());
                        }

                        // 原本の参照は利用しないのでクローンする
                        Process cloneProcess = SerializationUtils.clone(process);

                        flow.getProcesses().add(
                                ExecuteProcess.builder()
                                        .status(ProcessStatus.WAIT)
                                        .process(cloneProcess).build());
                        break;
                    case SCENARIO:
                        // シナリオの場合は別実行フロー扱いとする
                        T3Base nestScenario = context.getOriginal().getScenarios().get(f.getRef());
                        buildExecuteFlow(context, executeContextV10, nestScenario);
                        break;
                    default:
                        log.debug("no support execute flow... type:{}, id:{}", scenario.getType(), scenario.getInfo().getId());
                        break;

                }
            }


        }

    }

}
