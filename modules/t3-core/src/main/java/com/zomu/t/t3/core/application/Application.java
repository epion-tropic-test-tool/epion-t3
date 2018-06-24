package com.zomu.t.t3.core.application;

import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.scenario.parser.ScenarioParser;
import com.zomu.t.t3.core.type.ArgsType;
import com.zomu.t.t3.core.type.ExitCodeType;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import com.zomu.t.t3.v10.parser.ScenarioParserV10;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;

@Slf4j
public class Application {

    private static final Options OPTIONS = new Options();

    static {
        Arrays.stream(ArgsType.values()).forEach(x -> {
            if (x.isRequired()) {
                OPTIONS.addRequiredOption(x.getShortName(), x.getLongName(), x.isHasArg(), x.getDescription());
            } else {
                OPTIONS.addOption(x.getShortName(), x.getLongName(), x.isHasArg(), x.getDescription());
            }
        });
    }


    public static void main(String[] args) throws IOException {

        // バージョンの解決
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(OPTIONS, args);
        } catch (ParseException e) {
            log.error("args error...", e);
            System.exit(ExitCodeType.ERROR.getExitCode());
        }



    }

}
