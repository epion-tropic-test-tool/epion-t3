package com.zomu.t.epion.tropic.test.tool.core.execution.runner.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.core.exception.handler.BaseExceptionHandler;
import com.zomu.t.epion.tropic.test.tool.core.execution.parser.impl.BaseScenarioParser;
import com.zomu.t.epion.tropic.test.tool.core.annotation.ApplicationVersion;
import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.execution.reporter.impl.ScenarioReporterImpl;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.ApplicationRunner;
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
public class ApplicationRunnerImpl implements ApplicationRunner<BaseContext> {

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
            ScenarioRunnerImpl scenarioRunner = new ScenarioRunnerImpl();
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
                if (!cmd.hasOption(Args.NOREPORT.getShortName())) {
                    report(context);
                }

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
        ScenarioReporterImpl.getInstance().allReport(context);

    }
}
