package com.zomu.t.epion.tropic.test.tool.core.execution.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.core.exception.handler.BaseExceptionHandler;
import com.zomu.t.epion.tropic.test.tool.core.execution.parser.impl.BaseScenarioParser;
import com.zomu.t.epion.tropic.test.tool.core.annotation.ApplicationVersion;
import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.execution.reporter.impl.BaseScenarioReporter;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.ApplicationRunner;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.T3Base;
import com.zomu.t.epion.tropic.test.tool.core.type.Args;
import com.zomu.t.epion.tropic.test.tool.core.type.ExitCode;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioExecuteStatus;
import com.zomu.t.epion.tropic.test.tool.core.util.ExecutionFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@ApplicationVersion(version = "v1.0")
@Slf4j
public class BaseApplicationRunner implements ApplicationRunner<BaseContext> {

    /**
     * CLIオプション.
     */
    private static final Options OPTIONS = new Options();

    static {
        // 引数定義をCLIオプション化する
        // Base(v1.0)については、coreをそのまま引き継ぐ
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute(String[] args) {

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(OPTIONS, args);
        } catch (ParseException e) {
            log.error("args error...", e);
            return ExitCode.ERROR.getExitCode();
        }

        // コンテキストの生成
        BaseContext context = new BaseContext();

        try {

            // 開始
            context.getExecuteContext().setStart(LocalDateTime.now());

            // 引数設定
            setOptions(context, cmd);

            // シナリオの解析（パース処理）
            BaseScenarioParser.getInstance().parse(context);

            // 結果ディレクトリの作成
            createResultDirectory(context);

            // 実行
            BaseScenarioRunner scenarioRunner = new BaseScenarioRunner();
            scenarioRunner.execute(context);

            // 正常終了
            context.getExecuteContext().setStatus(ScenarioExecuteStatus.SUCCESS);
            context.getExecuteContext().setExitCode(ExitCode.NORMAL);

        } catch (Throwable t) {

            // シナリオ失敗
            if (context.getExecuteContext() != null) {
                context.getExecuteContext().setStatus(ScenarioExecuteStatus.FAIL);
            }

            // 例外ハンドリング
            handleGlobalException(context, t);

        } finally {

            // 終了
            if (context.getExecuteContext() != null) {

                context.getExecuteContext().setEnd(LocalDateTime.now());

                // 所用時間を設定
                context.getExecuteContext().setDuration(
                        Duration.between(
                                context.getExecuteContext().getStart(),
                                context.getExecuteContext().getEnd()));

                // レポート出力
                report(context);
            }

        }

        if (context.getExecuteContext() == null) {
            return ExitCode.ERROR.getExitCode();
        }
        return context.getExecuteContext().getExitCode().getExitCode();

    }

    @Override
    public void handleGlobalException(final BaseContext context, final Throwable t) {

        BaseExceptionHandler.getInstance().handle(context, t);

    }

    /**
     * 実行引数オプションをコンテキストへ設定する.
     *
     * @param context
     * @param commandLine
     */
    private void setOptions(final Context context, final CommandLine commandLine) {
        String rootPath = commandLine.getOptionValue(Args.ROOT_PATH.getShortName());
        String target = commandLine.getOptionValue(Args.SCENARIO.getShortName());

        // 必須パラメータの取得
        context.getOption().setRootPath(rootPath);
        context.getOption().setTarget(target);

        // プロファイルの取得
        if (commandLine.hasOption(Args.PROFILE.getShortName())) {
            context.getOption().setProfile(commandLine.getOptionValue(Args.PROFILE.getShortName()));
        }

        // 実行結果出力ディレクトリの取得
        if (commandLine.hasOption(Args.RESULT_ROOT_PATH.getShortName())) {
            context.getOption().setResultRootPath(commandLine.getOptionValue(Args.RESULT_ROOT_PATH.getShortName()));
        }
    }

    /**
     * @param context
     * @param scenario
     */
    private void buildExecuteScenario(final BaseContext context, final T3Base scenario) {

//        ExecuteScenario executeScenario = new ExecuteScenario();
//        executeScenario.setInfo(scenario.getInfo());
//        executeScenario.setFqsn(scenario.getInfo().getId());
//        //context.getExecuteOriginal().getScenarios().add(executeScenario);
//
//        for (Flow f : scenario.getFlows()) {
//
//            FlowType flowType = FlowType.valueOfByValue(f.getType());
//
//            if (flowType != null) {
//                switch (flowType) {
//                    case PROCESS:
//                        // プロセスの場合は実行フローに追加していく
//
//                        // 優先度-1:自シナリオ内を参照
//                        Process process = scenario.getProcesses().stream().filter(x -> x.getId().equals(f.getRef())).findFirst().orElse(null);
//                        if (process == null) {
//                            // 優先度-2:全体のプロセスから参照
//                            process = context.getOriginal().getProcesses().get(f.getRef());
//                        }
//                        if (process == null) {
//                            log.error("not found process: {}", f.getRef());
//                            throw new ProcessNotFoundException(f.getRef());
//                        }
//
//                        // 原本の参照は利用しないのでクローンする
//                        Process cloneProcess = SerializationUtils.clone(process);
//
//                        // process実行情報を作成
//                        ExecuteProcess executeProcess = new ExecuteProcess();
//                        executeProcess.setFqpn(f.getRef());
//                        executeProcess.setProcess(cloneProcess);
//
//                        // プロセスが属するシナリオのIDを取得
//                        // XXX : バグ以外でここが取れないことはあり得ない
//                        String belongScenarioId = context.getOriginal().getProcessScenarioRelations().get(f.getRef());
//                        if (StringUtils.isEmpty(belongScenarioId)) {
//                            belongScenarioId = context.getOriginal().getProcessScenarioRelations().get(scenario.getInfo().getId() + "." + f.getRef());
//                        }
//
//                        // 全原本から属するシナリオを取得
//                        T3Base belongScenario = context.getOriginal().getOriginals().get(belongScenarioId);
//
//                        // 各スコープ変数の設定
//                        if (belongScenario.getVariables() != null) {
//                            if (belongScenario.getVariables().getGlobal() != null) {
//                                context.getExecuteOriginal().getGlobalVariables().putAll(belongScenario.getVariables().getGlobal());
//                            }
//                            if (belongScenario.getVariables().getScenario() != null) {
//                                executeScenario.getScenarioVariables().putAll(belongScenario.getVariables().getScenario());
//                            }
//                            if (belongScenario.getVariables().getLocal() != null) {
//                                executeProcess.getLocalVariables().putAll(belongScenario.getVariables().getLocal());
//                            }
//                        }
//
//                        // 実行フローに追加
//                        executeScenario.getProcesses().add(executeProcess);
//                        break;
//                    case SCENARIO:
//                        // シナリオの場合は別実行フロー扱いとする
//                        T3Base nestScenario = context.getOriginal().getScenarios().get(f.getRef());
//                        buildExecuteScenario(context, nestScenario);
//                        break;
//                    default:
//                        log.debug("no support execute flow... type:{}, id:{}", scenario.getType(), scenario.getInfo().getId());
//                        break;
//
//                }
//            }
//        }

    }

    /**
     * 実行シナリオをコンパイルする.
     * コンパイルとは以下の処理のことを指す.
     * ・変数の参照関係の整合性が保たれているか.（存在しない変数が使われている等）
     *
     * @param context
     */
    private void scenarioCompile(final BaseContext context) {
        // TODO:チェック処理
    }

    /**
     * 原本から実行コンテキストへコピーする.
     *
     * @param context
     */
//    private void cloneExecuteContext(final BaseContext context) {
//        // 原本をそのまま残すためクローンする
//        ExecuteContext cloneExecuteContextV10 = SerializationUtils.clone(context.getExecuteOriginal());
//        context.setExecuteContext(cloneExecuteContextV10);
//    }

    /**
     * 結果ディレクトリが未作成であった場合に、作成します.
     *
     * @param context
     */
    private void createResultDirectory(Context context) {
        ExecutionFileUtils.createResultDirectory(context);
    }

    /**
     * レポート出力を行う.
     *
     * @param context
     */
    private void report(final BaseContext context) {

        // レポーターに処理を移譲
        BaseScenarioReporter.getInstance().allReport(context);

    }
}
