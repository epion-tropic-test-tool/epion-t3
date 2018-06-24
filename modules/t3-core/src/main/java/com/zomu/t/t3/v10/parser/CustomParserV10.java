package com.zomu.t.t3.v10.parser;

import com.google.common.reflect.ClassPath;
import com.zomu.t.t3.core.annotation.Command;
import com.zomu.t.t3.core.exception.T3ScenarioParseException;
import com.zomu.t.t3.core.model.context.CommandInfo;
import com.zomu.t.t3.core.model.context.T3Context;
import com.zomu.t.t3.core.model.context.holder.CustomConfigHolder;
import com.zomu.t.t3.core.scenario.parser.CustomParser;
import com.zomu.t.t3.v10.model.context.T3ContextV10;
import com.zomu.t.t3.v10.model.scenario.Custom;
import com.zomu.t.t3.v10.model.scenario.Process;
import com.zomu.t.t3.v10.model.scenario.T3Base;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * カスタム機能の定義を解析するクラス.
 *
 * @author takashno
 */
@Slf4j
public class CustomParserV10 implements CustomParser {

    /**
     * カスタム機能定義のファイルパターン（正規表現）.
     */
    public static final String CUSTOM_FILENAME_REGEXP_PATTERN = ".*[\\-]?custom.yaml";

    /**
     * インスタンス作るのが面倒だったので作っただけの処理.
     *
     * @param context
     */
    public static void parseCustom(T3Context context) {
        new CustomParserV10().parse(context);
    }

    @Override
    public void parse(T3Context context) {
        parse(context, CUSTOM_FILENAME_REGEXP_PATTERN);
    }

    @Override
    public void parse(T3Context context, String fileNamePattern) {

        T3ContextV10 t3ContextV10 = T3ContextV10.class.cast(context);

        findCustom(t3ContextV10, fileNamePattern);

        parseCustom(t3ContextV10);

    }

    private void findCustom(T3ContextV10 context, String fileNamePattern) {

        Custom customs = new Custom();

        try {
            Files.find(context.getScenarioRootPath(), Integer.MAX_VALUE, (p, attr) -> p.toFile().getName().matches(fileNamePattern)).forEach(x -> {
                try {
                    customs.getPackages().putAll(context.getObjectMapper().readValue(x.toFile(), T3Base.class).getCustoms().getPackages());
                } catch (IOException e) {
                    // ignore
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        context.getOriginal().setCustom(customs);

    }

    private void parseCustom(T3ContextV10 t3ContextV10) {

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
                throw new T3ScenarioParseException(e);
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
