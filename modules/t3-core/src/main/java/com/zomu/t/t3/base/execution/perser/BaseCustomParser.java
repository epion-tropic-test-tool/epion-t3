package com.zomu.t.t3.base.execution.perser;

import com.google.common.reflect.ClassPath;
import com.zomu.t.t3.base.context.BaseContext;
import com.zomu.t.t3.core.annotation.Command;
import com.zomu.t.t3.core.context.CommandInfo;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.exception.ScenarioParseException;
import com.zomu.t.t3.core.execution.parser.IndividualTargetParser;
import com.zomu.t.t3.core.holder.CustomConfigHolder;
import com.zomu.t.t3.model.scenario.Process;
import com.zomu.t.t3.model.scenario.T3Base;
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
     * インスタンス.
     */
    private static final BaseCustomParser instance = new BaseCustomParser();

    /**
     * カスタム機能定義のファイルパターン（正規表現）.
     */
    public static final String CUSTOM_FILENAME_REGEXP_PATTERN = ".*[\\-]?custom.yaml";

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

        BaseContext t3ContextV10 = BaseContext.class.cast(context);

        findCustom(t3ContextV10, fileNamePattern);

        parseCustom(t3ContextV10);

    }

    private void findCustom(final BaseContext context, final String fileNamePattern) {

        try {
            Files.find(Paths.get(context.getOption().getRootPath()), Integer.MAX_VALUE, (p, attr) -> p.toFile().getName().matches(fileNamePattern)).forEach(x -> {
                try {
                    context.getOriginal().getCustom().getPackages().putAll(context.getObjectMapper().readValue(x.toFile(), T3Base.class).getCustoms().getPackages());
                } catch (IOException e) {
                    throw new ScenarioParseException(e);
                }
            });
        } catch (IOException e) {
            throw new ScenarioParseException(e);
        }

    }

    private void parseCustom(BaseContext t3ContextV10) {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        // カスタムコマンド
        t3ContextV10.getOriginal().getCustom().getPackages().forEach((k, v) -> CustomConfigHolder.addCustomPackage(k, v));

        // カスタム機能のパッケージを走査
        for (Map.Entry<String, String> entry : t3ContextV10.getOriginal().getCustom().getPackages().entrySet()) {


            log.debug("start parse custom function packages -> {}:{}", entry.getKey(), entry.getValue());

            // カスタム機能の指定パッケージ配下の全てのクラスを取得
            Set<Class<?>> allClasses = null;
            try {
                allClasses = ClassPath.from(loader)
                        .getTopLevelClassesRecursive(entry.getValue()).stream()
                        .map(info -> info.load())
                        .collect(Collectors.toSet());
            } catch (IOException e) {
                throw new ScenarioParseException(e);
            }

            // カスタムコマンドを解析
            allClasses.stream()
                    .filter(x -> x.getDeclaredAnnotation(Command.class) != null)
                    .filter(x -> Process.class.isAssignableFrom(x))
                    .forEach(x -> {
                        Command command = x.getDeclaredAnnotation(Command.class);
                        CommandInfo commandInfo = CommandInfo.builder().id(command.id()).scenarioModel(x).runner(command.runner()).build();
                        CustomConfigHolder.addCustomCommandInfo(
                                command.id(), commandInfo);
                        t3ContextV10.getCustomCommands().put(command.id(), commandInfo);
                    });

            // 他機能のカスタムがあれば随時追加

            log.debug("end parse custom function packages -> {}:{}", entry.getKey(), entry.getValue());
        }

    }

}
