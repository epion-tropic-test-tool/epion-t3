package com.zomu.t.epion.tropic.test.tool.core.execution.parser.impl;

import com.google.common.reflect.ClassPath;
import com.zomu.t.epion.tropic.test.tool.core.context.BaseContext;
import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.FlowInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.exception.bean.ScenarioParseError;
import com.zomu.t.epion.tropic.test.tool.core.execution.parser.IndividualTargetParser;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Flow;
import com.zomu.t.epion.tropic.test.tool.core.type.ScenarioPaseErrorType;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.Command;
import com.zomu.t.epion.tropic.test.tool.core.context.CommandInfo;
import com.zomu.t.epion.tropic.test.tool.core.exception.ScenarioParseException;
import com.zomu.t.epion.tropic.test.tool.core.holder.CustomConfigHolder;
import com.zomu.t.epion.tropic.test.tool.core.model.scenario.T3Base;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * カスタム機能の定義を解析するクラス.
 *
 * @author takashno
 */
@Slf4j
public final class BaseCustomParser implements IndividualTargetParser {

    /**
     * シングルトンインスタンス.
     */
    private static final BaseCustomParser instance = new BaseCustomParser();

    /**
     * カスタム機能定義のファイルパターン（正規表現）.
     */
    public static final String CUSTOM_FILENAME_REGEXP_PATTERN = "t3_.*[\\-]?custom.yaml";

    /**
     * プライベートコンストラクタ.
     */
    private BaseCustomParser() {
        // Do Nothing...
    }

    /**
     * インスタンスを取得する.
     *
     * @return
     */
    public static BaseCustomParser getInstance() {
        return instance;
    }

    /**
     * {@inheritDoc}
     *
     * @param context
     */
    @Override
    public void parse(Context context) {
        parse(context, CUSTOM_FILENAME_REGEXP_PATTERN);
    }

    /**
     * {@inheritDoc}
     *
     * @param context
     * @param fileNamePattern
     */
    @Override
    public void parse(final Context context, String fileNamePattern) {

        BaseContext baseContext = BaseContext.class.cast(context);

        findCustom(baseContext, fileNamePattern);

        parseCustom(baseContext);

    }

    private void findCustom(final BaseContext context, final String fileNamePattern) {

        try {
            Files.find(Paths.get(context.getOption().getRootPath()), Integer.MAX_VALUE, (p, attr) -> p.toFile().getName().matches(fileNamePattern)).forEach(x -> {
                try {
                    context.getOriginal().getCustom().getPackages().putAll(context.getObjectMapper().readValue(x.toFile(), T3Base.class).getCustoms().getPackages());
                } catch (IOException e) {
                    throw new ScenarioParseException(
                            ScenarioParseError.builder().filePath(x).type(ScenarioPaseErrorType.PARSE_ERROR)
                                    .message("Custom Package Config Parse Error Occurred.").build());
                }
            });
        } catch (IOException e) {
            throw new SystemException(e);
        }

    }

    private void parseCustom(BaseContext baseContext) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        // カスタムコマンド
        baseContext.getOriginal().getCustom().getPackages().forEach(
                (k, v) -> CustomConfigHolder.getInstance().addCustomPackage(k, v));

        // カスタム機能のパッケージを走査
        for (Map.Entry<String, String> entry : baseContext.getOriginal().getCustom().getPackages().entrySet()) {


            log.debug("start parse custom function packages -> {}:{}", entry.getKey(), entry.getValue());

            // カスタム機能の指定パッケージ配下の全てのクラスを取得
            Set<Class<?>> allClasses = null;
            try {
                allClasses = ClassPath.from(loader)
                        .getTopLevelClassesRecursive(entry.getValue()).stream()
                        .map(info -> info.load())
                        .collect(Collectors.toSet());
            } catch (IOException e) {
                throw new SystemException(e);
            }

            // カスタムコマンドを解析
            allClasses.stream()
                    .filter(x -> x.getDeclaredAnnotation(com.zomu.t.epion.tropic.test.tool.core.annotation.Command.class) != null)
                    .filter(x -> Command.class.isAssignableFrom(x))
                    .forEach(x -> {
                        com.zomu.t.epion.tropic.test.tool.core.annotation.Command command = x.getDeclaredAnnotation(com.zomu.t.epion.tropic.test.tool.core.annotation.Command.class);
                        CommandInfo commandInfo = CommandInfo.builder().id(command.id()).model(x).runner(command.runner()).build();
                        CustomConfigHolder.getInstance().addCustomCommandInfo(
                                command.id(), commandInfo);
                        // TODO:シナリオを動かすときに使うが、果たして重複保持が必要か？
                        baseContext.getCustomCommands().put(command.id(), commandInfo);
                    });


            // カスタムFlowを解析
            allClasses.stream()
                    .filter(x -> x.getDeclaredAnnotation(com.zomu.t.epion.tropic.test.tool.core.annotation.Flow.class) != null)
                    .filter(x -> Flow.class.isAssignableFrom(x))
                    .forEach(x -> {
                        com.zomu.t.epion.tropic.test.tool.core.annotation.Flow flow =
                                x.getDeclaredAnnotation(com.zomu.t.epion.tropic.test.tool.core.annotation.Flow.class);
                        FlowInfo flowInfo = FlowInfo.builder().id(flow.id()).model(x).runner(flow.runner()).build();
                        CustomConfigHolder.getInstance().addCustomFlowInfo(
                                flow.id(), flowInfo);
                        // TODO:シナリオを動かすときに使うが、果たして重複保持が必要か？
                        baseContext.getCustomFlows().put(flow.id(), flowInfo);
                    });


            // >>他機能のカスタムがあれば随時追加<<

            log.debug("end parse custom function packages -> {}:{}", entry.getKey(), entry.getValue());
        }

    }

}
