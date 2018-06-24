package com.zomu.t.t3.v10.runner;

import com.zomu.t.t3.core.annotation.ApplicationVersion;
import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.runner.ApplicationRunner;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.core.type.ExitCodeType;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import com.zomu.t.t3.v10.parser.ScenarioParserV10;
import com.zomu.t.t3.v10.type.ArgsType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.nio.file.Paths;
import java.util.Arrays;

@ApplicationVersion(version = "v1.0")
@Slf4j
public class ApplicationRunnerV10 implements ApplicationRunner {

    private static final Options OPTIONS = new Options();

    static {
        Arrays.stream(ArgsType.values()).forEach(
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
            System.exit(ExitCodeType.ERROR.getExitCode());
        }


        // コンテキストの生成
        T3Context context = new T3ContextV10(
                Paths.get(cmd.getOptionValue(ArgsType.ROOT_PATH.getShortName())));

        // シナリオの解析（パース処理）
        ScenarioParser scenarioParser = new ScenarioParserV10();
        scenarioParser.parse(context);

        // 実行シナリオの選択

    }

}
