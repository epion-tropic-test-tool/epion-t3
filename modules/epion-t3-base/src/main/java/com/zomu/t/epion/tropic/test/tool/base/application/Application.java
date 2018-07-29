package com.zomu.t.epion.tropic.test.tool.base.application;

import com.google.common.reflect.ClassPath;
import com.zomu.t.epion.tropic.test.tool.core.annotation.ApplicationVersion;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.ApplicationRunner;
import com.zomu.t.epion.tropic.test.tool.core.message.CoreMessages;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.core.type.Args;
import com.zomu.t.epion.tropic.test.tool.core.type.ExitCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class Application {

    private static final String BASE_PACKAGE = "com.zomu.t.epion.tropic.test.tool";

    private static final Options OPTIONS = new Options();

    static {
        Arrays.stream(Args.values()).forEach(x -> {
            if (x.isRequired()) {
                OPTIONS.addRequiredOption(x.getShortName(), x.getLongName(), x.isHasArg(), x.getDescription());
            } else {
                OPTIONS.addOption(x.getShortName(), x.getLongName(), x.isHasArg(), x.getDescription());
            }
        });
    }


    public static void main(String[] args) throws IOException {

        // バナー出力
        outputBanner();

        MessageManager messageManager = MessageManager.getInstance();

        args = new String[]{
                "-v", "v1.0",
                "-m", "test",
                "-t", "sample-scenario-001",
                "-r", "/Users/takashimanozomu/work/30_pgworkspaces/intellij/t3-core/modules/epion-t3-core/src/main/resources/sample"
        };

        // バージョンの解決
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            // この判定は、versionのみ取得できればよいため緩く解析する
            cmd = parser.parse(OPTIONS, args, true);
        } catch (ParseException e) {
            log.error("args error...", e);
            System.exit(ExitCode.ERROR.getExitCode());
        }

        // 全てのApplicationRunnerを取得する
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Map<String, Class<?>> applicationRunnerClasses
                = ClassPath.from(loader)
                .getTopLevelClassesRecursive(BASE_PACKAGE).stream()
                // パッケージ名にバージョンを含むこと or base のみ許可する
                .filter(
                        info -> {
                            String[] packages = info.getPackageName().replace(BASE_PACKAGE + ".", "").split("\\.");
                            return packages[0].matches("v[0-9]+") || "base".equals(packages[0]);
                        })
                // ApplicationRunnerを実装していること かつ ApplicationVersionのアノテーションを付与していること
                .filter(
                        info -> {
                            return ApplicationRunner.class.isAssignableFrom(info.load())
                                    && info.load().getDeclaredAnnotation(ApplicationVersion.class) != null;
                        })
                .map(info -> info.load())
                .collect(Collectors.toMap(x -> x.getDeclaredAnnotation(ApplicationVersion.class).version(), x -> x));

        // 指定されたツールバージョン
        String version = cmd.getOptionValue(Args.VERSION.getShortName());

        // ツールバージョンに対応したアプリケーション実行クラス
        Class applicationRunnerClass =
                applicationRunnerClasses.get(version);

        if (applicationRunnerClass != null) {
            // 存在していればメソッド決め打ちで実行する
            try {
                Method execute = applicationRunnerClass.getDeclaredMethod("execute", String[].class);
                Object applicationInstance = applicationRunnerClass.newInstance();
                int exitCode = (int) execute.invoke(applicationInstance, new Object[]{args});
                System.exit(exitCode);
            } catch (NoSuchMethodException e) {
                log.error(messageManager.getMessage(CoreMessages.CORE_ERR_9001), e);
                System.exit(ExitCode.ERROR.getExitCode());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                log.error(messageManager.getMessage(CoreMessages.CORE_ERR_9001), e);
                System.exit(ExitCode.ERROR.getExitCode());
            }
        } else {
            // 存在していないならエラーとする
            log.error(messageManager.getMessage(CoreMessages.CORE_ERR_9002, version));
            System.exit(ExitCode.ERROR.getExitCode());
        }

    }

    /**
     * 標準出力に対してバナーを出力する.
     */
    private static void outputBanner() {

        ClassLoader classLoader = Application.class.getClassLoader();
        File file = new File(classLoader.getResource("banners/banner.txt").getFile());
        try {
            String banner = FileUtils.readFileToString(file);
            System.out.println(banner);
        } catch (IOException e) {
            // ignore
        }

    }

}
