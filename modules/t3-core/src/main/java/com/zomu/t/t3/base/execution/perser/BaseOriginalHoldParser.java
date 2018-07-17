package com.zomu.t.t3.base.execution.perser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.core.exception.ScenarioParseException;
import com.zomu.t.t3.core.exception.SystemException;
import com.zomu.t.t3.core.exception.bean.ScenarioParseError;
import com.zomu.t.t3.core.execution.parser.IndividualTargetParser;
import com.zomu.t.t3.core.type.ScenarioPaseErrorType;
import com.zomu.t.t3.core.type.ScenarioType;
import com.zomu.t.t3.model.scenario.Process;
import com.zomu.t.t3.model.scenario.T3Base;
import lombok.extern.slf4j.Slf4j;
import org.apache.bval.jsr.ApacheValidationProvider;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * カスタム機能の定義を解析するクラス.
 *
 * @author takashno
 */
@Slf4j
public final class BaseOriginalHoldParser implements IndividualTargetParser<BaseContext> {

    /**
     * インスタンス.
     */
    private static final BaseOriginalHoldParser instance = new BaseOriginalHoldParser();

    /**
     * シナリオファイルパターン（正規表現）.
     */
    public static final String FILENAME_REGEXP_PATTERN = "t3_.*\\.yaml";

    /**
     * 単項目チェックValidatorFactory.
     */
    private ValidatorFactory validationFactory =
            Validation.byProvider(ApacheValidationProvider.class).configure().buildValidatorFactory();

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
    public void parse(final BaseContext context) {
        parse(context, null);
    }

    @Override
    public void parse(final BaseContext context, final String fileNamePattern) {

        final BaseContext baseContext = BaseContext.class.cast(context);

        // 正規表現パターンを作成
        final Pattern scenarioFileNamePattern = Pattern.compile(fileNamePattern == null ? FILENAME_REGEXP_PATTERN : fileNamePattern);

        // Bean Validator
        Validator validator = validationFactory.getValidator();

        // シナリオ解析エラー
        final List<ScenarioParseError> errors = new ArrayList<>();

        FileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                Matcher fileNameMatcher = scenarioFileNamePattern.matcher(file.getFileName().toString());

                if (!fileNameMatcher.matches()) {
                    log.debug("skip file: {}, scenario file must be name pattern: {}", file, fileNamePattern == null ? FILENAME_REGEXP_PATTERN : fileNamePattern);
                    return FileVisitResult.CONTINUE;
                }

                log.debug("visit file: {}", file);

                T3Base t3Base = null;
                try {

                    // YAML -> Object
                    t3Base = context.getObjectMapper().readValue(file.toFile(), T3Base.class);

                    // Bean Validation
                    Set<ConstraintViolation<T3Base>> validationErrors = validator.validate(t3Base);

                    validationErrors.stream().forEach(
                            x -> {
                                errors.add(ScenarioParseError.builder()
                                        .filePath(file)
                                        .type(ScenarioPaseErrorType.VALIDATION_ERROR)
                                        .message(x.getMessage())
                                        .target(x.getPropertyPath().toString())
                                        .value(x.getInvalidValue())
                                        .build());
                            });

                } catch (JsonParseException | JsonMappingException e) {
                    log.warn("file is not t3 format: {} -> ignore...", file);
                    errors.add(ScenarioParseError.builder().filePath(file).type(ScenarioPaseErrorType.PARSE_ERROR).message("").build());
                    return FileVisitResult.CONTINUE;
                }

                if (t3Base.getInfo() != null) {

                    // process識別子とPathを紐付ける
                    baseContext.getOriginal().getScenarioPlacePaths().put(t3Base.getInfo().getId(), file.getParent());

                    // ファイル原本の完全保存
                    baseContext.getOriginal().getOriginals().put(t3Base.getInfo().getId(), t3Base);

                    ScenarioType scenarioType = ScenarioType.valueOfByValue(t3Base.getType());

                    if (scenarioType != null) {
                        // type別に分割
                        switch (scenarioType) {
                            case SCENARIO:
                                baseContext.getOriginal().getScenarios().put(t3Base.getInfo().getId(), t3Base);
                                break;
                            case PARTS:
                                baseContext.getOriginal().getParts().put(t3Base.getInfo().getId(), t3Base);
                                break;
                            case CONFIG:
                                baseContext.getOriginal().getConfigs().put(t3Base.getInfo().getId(), t3Base);
                                break;
                            default:
                                // Do Nothing...
                                break;
                        }
                    }

                    // t3BaseがfinalでないのでLambdaが利用できない・・・
                    // なんかやり方あるのかね・・・
                    for (Process process : t3Base.getProcesses()) {

                        // process識別子を作成
                        String fullProcessId = t3Base.getInfo().getId() + "." + process.getId();

                        // process定義を追加
                        baseContext.getOriginal().getProcesses().put(
                                fullProcessId, process);

                        // process識別子とシナリオIDを紐付ける
                        baseContext.getOriginal().getProcessScenarioRelations().put(fullProcessId, t3Base.getInfo().getId());

                        // process識別子とPathを紐付ける
                        baseContext.getOriginal().getProcessPlacePaths().put(fullProcessId, file);
                    }

                }

                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(Paths.get(context.getOption().getRootPath()), visitor);
        } catch (IOException e) {
            throw new SystemException(e);
        } finally {
            // シナリオに大してなんらかの不備ある場合は、この時点でエラーとする.
            if (!errors.isEmpty()) {
                throw new ScenarioParseException(errors);
            }
        }

    }


}
