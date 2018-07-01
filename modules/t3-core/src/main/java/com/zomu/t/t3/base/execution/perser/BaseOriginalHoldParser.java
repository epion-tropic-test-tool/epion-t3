package com.zomu.t.t3.base.execution.perser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zomu.t.t3.core.exception.ScenarioParseException;
import com.zomu.t.t3.core.execution.parser.IndividualTargetParser;
import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.model.scenario.Process;
import com.zomu.t.t3.model.scenario.T3Base;
import com.zomu.t.t3.core.type.ScenarioType;
import lombok.extern.slf4j.Slf4j;
import org.apache.bval.jsr.ApacheValidationProvider;
import org.apache.bval.jsr.ConstraintValidation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * カスタム機能の定義を解析するクラス.
 *
 * @author takashno
 */
@Slf4j
public final class BaseOriginalHoldParser implements IndividualTargetParser {

    /**
     * インスタンス.
     */
    private static final BaseOriginalHoldParser instance = new BaseOriginalHoldParser();

    /**
     * シナリオファイルパターン（正規表現）.
     */
    public static final String FILENAME_REGEXP_PATTERN = ".*\\.yaml";


    /**
     * プライベートコンストラクタ.
     */
    private BaseOriginalHoldParser() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得する.
     *
     * @return
     */
    public static BaseOriginalHoldParser getInstance() {
        return instance;
    }


    @Override
    public void parse(final Context context) {
        parse(context, FILENAME_REGEXP_PATTERN);
    }

    @Override
    public void parse(final Context context, String fileNamePattern) {

        final BaseContext t3ContextV10 = BaseContext.class.cast(context);

        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                log.debug("visit file: {}", file);

                T3Base t3Base = null;
                try {
                    t3Base = context.getObjectMapper().readValue(file.toFile(), T3Base.class);

                    ValidatorFactory avf =
                            Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();

                    Set<ConstraintViolation<T3Base>> re = avf.getValidator().validate(t3Base);
                    System.out.println(re);


//                    for (Process process : t3Base.getProcesses()) {
//                        Set<ConstraintViolation<Process>> re2 = avf.getValidator().validate(process);
//                        System.out.println(re2);
//                    }


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

                        String fullProcessId = t3Base.getInfo().getId() + "." + process.getId();

                        t3ContextV10.getOriginal().getProcesses().put(
                                fullProcessId, process);

                        t3ContextV10.getOriginal().getProcessScenarioRelations().put(fullProcessId, t3Base.getInfo().getId());
                    }

                }

                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(Paths.get(context.getOption().getRootPath()), visitor);
        } catch (IOException e) {
            throw new ScenarioParseException(e);
        }

    }


}
