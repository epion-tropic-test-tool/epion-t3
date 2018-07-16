package com.zomu.t.t3.base.execution.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.base.exception.handler.BaseExceptionHandler;
import com.zomu.t.t3.base.execution.perser.BaseScenarioParser;
import com.zomu.t.t3.base.execution.reporter.BaseScenarioReporter;
import com.zomu.t.t3.core.annotation.ApplicationVersion;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.context.execute.ExecuteContext;
import com.zomu.t.t3.core.context.execute.ExecuteProcess;
import com.zomu.t.t3.core.context.execute.ExecuteScenario;
import com.zomu.t.t3.core.exception.ProcessNotFoundException;
import com.zomu.t.t3.core.exception.ScenarioNotFoundException;
import com.zomu.t.t3.core.execution.runner.ApplicationRunner;
import com.zomu.t.t3.core.type.Args;
import com.zomu.t.t3.core.type.ExitCode;
import com.zomu.t.t3.core.type.FlowType;
import com.zomu.t.t3.core.util.ExecutionFileUtils;
import com.zomu.t.t3.model.scenario.Flow;
import com.zomu.t.t3.model.scenario.Process;
import com.zomu.t.t3.model.scenario.T3Base;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@ApplicationVersion(version = "v1.0")
@Slf4j
public class BaseApplicationRunner implements com.zomu.t.t3.core.execution.runner.ApplicationRunner<BaseContext> {

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
            // 引数設定
            setOptions(context, cmd);

            // シナリオの解析（パース処理）
            BaseScenarioParser.getInstance().parse(context);

            // 実行シナリオの選択
            T3Base scenario = context.getOriginal().getScenarios().get(context.getOption().getTarget());

            if (scenario == null) {
                throw new ScenarioNotFoundException(context.getOption().getTarget());
            }

            // 実行フローの構築
            buildExecuteScenario(context, scenario);

            // 実行シナリオのコンパイル
            scenarioCompile(context);

            // 実行コンテキストのクローン生成
            cloneExecuteContext(context);

            // プロファイルの値をバインド
            bindProfileValues(context);

            // 開始
            context.getExecuteContext().setStart(LocalDateTime.now());

            // 結果ディレクトリの作成
            createResultDirectory(context);

            // 実行
            BaseScenarioRunner scenarioRunner = new BaseScenarioRunner();
            scenarioRunner.execute(context);

        } catch (Throwable t) {
            handleGlobalException(context, t);
        } finally {

            // 終了
            context.getExecuteContext().setEnd(LocalDateTime.now());

            // 所用時間を設定
            context.getExecuteContext().setDuration(
                    Duration.between(
                            context.getExecuteContext().getStart(),
                            context.getExecuteContext().getEnd()));

            // レポート出力
            report(context);
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

        ExecuteScenario executeScenario = new ExecuteScenario();
        executeScenario.setInfo(scenario.getInfo());
        context.getExecuteOriginal().getScenarios().add(executeScenario);

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
                            throw new ProcessNotFoundException(f.getRef());
                        }

                        // 原本の参照は利用しないのでクローンする
                        Process cloneProcess = SerializationUtils.clone(process);

                        // process実行情報を作成
                        ExecuteProcess executeProcess = new ExecuteProcess();
                        executeProcess.setProcess(cloneProcess);

                        // プロセスが属するシナリオのIDを取得
                        // XXX : バグ以外でここが取れないことはあり得ない
                        String belongScenarioId = context.getOriginal().getProcessScenarioRelations().get(f.getRef());
                        if (StringUtils.isEmpty(belongScenarioId)) {
                            belongScenarioId = context.getOriginal().getProcessScenarioRelations().get(scenario.getInfo().getId() + "." + f.getRef());
                        }

                        // 全原本から属するシナリオを取得
                        T3Base belongScenario = context.getOriginal().getOriginals().get(belongScenarioId);

                        // 各スコープ変数の設定
                        if (belongScenario.getVariables() != null) {
                            if (belongScenario.getVariables().getGlobal() != null) {
                                context.getExecuteOriginal().getGlobalVariables().putAll(belongScenario.getVariables().getGlobal());
                            }
                            if (belongScenario.getVariables().getScenario() != null) {
                                executeScenario.getScenarioVariables().putAll(belongScenario.getVariables().getScenario());
                            }
                            if (belongScenario.getVariables().getLocal() != null) {
                                executeProcess.getLocalVariables().putAll(belongScenario.getVariables().getLocal());
                            }
                        }

                        // 実行フローに追加
                        executeScenario.getProcesses().add(executeProcess);
                        break;
                    case SCENARIO:
                        // シナリオの場合は別実行フロー扱いとする
                        T3Base nestScenario = context.getOriginal().getScenarios().get(f.getRef());
                        buildExecuteScenario(context, nestScenario);
                        break;
                    default:
                        log.debug("no support execute flow... type:{}, id:{}", scenario.getType(), scenario.getInfo().getId());
                        break;

                }
            }
        }

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
    private void cloneExecuteContext(final BaseContext context) {
        // 原本をそのまま残すためクローンする
        ExecuteContext cloneExecuteContextV10 = SerializationUtils.clone(context.getExecuteOriginal());
        context.setExecuteContext(cloneExecuteContextV10);
    }

    /**
     * 実行時にプロファイルが指定されている場合には、実行シナリオに対してプロファイル値を適用する.
     *
     * @param context
     */
    private void bindProfileValues(final BaseContext context) {

        if (StringUtils.isNotEmpty(context.getOption().getProfile())) {
            // TODO:バインド処理
        }

    }


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
        BaseScenarioReporter.getInstance().report(context);

    }
}
