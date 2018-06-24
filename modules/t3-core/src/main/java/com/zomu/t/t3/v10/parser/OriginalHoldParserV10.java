package com.zomu.t.t3.v10.parser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zomu.t.t3.core.exception.T3ScenarioParseException;
import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.scenario.parser.IndividualTargetParser;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import com.zomu.t.t3.v10.model.scenario.Process;
import com.zomu.t.t3.v10.model.scenario.T3Base;
import com.zomu.t.t3.v10.type.ScenarioType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * カスタム機能の定義を解析するクラス.
 *
 * @author takashno
 */
@Slf4j
public class OriginalHoldParserV10 implements IndividualTargetParser {

    /**
     * シナリオファイルパターン（正規表現）.
     */
    public static final String FILENAME_REGEXP_PATTERN = ".*\\.yaml";

    /**
     * インスタンス作るのが面倒だったので作っただけの処理.
     *
     * @param context
     */
    public static void parseOriginal(T3Context context) {
        new OriginalHoldParserV10().parse(context);
    }

    @Override
    public void parse(final T3Context context) {
        parse(context, FILENAME_REGEXP_PATTERN);
    }

    @Override
    public void parse(final T3Context context, String fileNamePattern) {

        final T3ContextV10 t3ContextV10 = T3ContextV10.class.cast(context);

        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                log.debug("visit file: {}", file);

                T3Base t3Base = null;
                try {
                    t3Base = context.getObjectMapper().readValue(file.toFile(), T3Base.class);
                } catch (JsonParseException | JsonMappingException e) {
                    log.warn("file is not t3 format: {} -> ignore...", file);
                    return FileVisitResult.CONTINUE;
                }

                if (t3Base.getInfo() != null) {

                    // ファイル原本の完全保存
                    t3ContextV10.getOriginal().getOriginals().put(t3Base.getInfo().getId(), t3Base);

                    ScenarioType scenarioType = ScenarioType.valueOfByValue(t3Base.getType());

                    if (scenarioType != null) {
                        // type別に分割
                        switch (scenarioType) {
                            case SCENARIO:
                                t3ContextV10.getOriginal().getScenarios().put(t3Base.getInfo().getId(), t3Base);
                                break;
                            case PARTS:
                                t3ContextV10.getOriginal().getParts().put(t3Base.getInfo().getId(), t3Base);
                                break;
                            case CONFIG:
                                t3ContextV10.getOriginal().getConfigs().put(t3Base.getInfo().getId(), t3Base);
                                break;
                            default:
                                // Do Nothing...
                                break;
                        }
                    }

                    // t3BaseがfinalでないのでLambdaが利用できない・・・
                    // なんかやり方あるのかね・・・
                    for (Process process : t3Base.getProcesses()) {
                        t3ContextV10.getOriginal().getProcesses().put(
                                t3Base.getInfo().getId() + "-" + process.getId(), process);
                    }

                }

                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(context.getScenarioRootPath(), visitor);
        } catch (IOException e) {
            throw new T3ScenarioParseException(e);
        }

    }


}
