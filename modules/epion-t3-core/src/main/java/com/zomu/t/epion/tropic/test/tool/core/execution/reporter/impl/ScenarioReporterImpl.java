package com.zomu.t.epion.tropic.test.tool.core.execution.reporter.impl;

import com.zomu.t.epion.tropic.test.tool.core.context.Context;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteContext;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteFlow;
import com.zomu.t.epion.tropic.test.tool.core.execution.reporter.ScenarioReporter;
import com.zomu.t.epion.tropic.test.tool.core.message.impl.BaseMessages;
import com.zomu.t.epion.tropic.test.tool.core.annotation.OriginalProcessField;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteCommand;
import com.zomu.t.epion.tropic.test.tool.core.context.execute.ExecuteScenario;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.util.DateTimeUtils;
import com.zomu.t.epion.tropic.test.tool.core.util.ExecutionFileUtils;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.WordUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * シナリオレポート出力処理.
 *
 * @author takashno
 */
@Slf4j
public final class ScenarioReporterImpl implements ScenarioReporter {

    /**
     * シングルトンインスタンス.
     */
    private static final ScenarioReporterImpl instance = new ScenarioReporterImpl();

    /**
     * テンプレート接頭辞.
     */
    public static final String TEMPLATE_PREFIX = "/templates/";

    /**
     * テンプレート接尾辞
     */
    public static final String TEMPLATE_SUFFIX = ".html";

    /**
     * テンプレートエンコード.
     */
    public static final String TEMPLATE_ENCODING = "UTF-8";

    /**
     * Thymeleafテンプレートエンジン
     */
    private final TemplateEngine templateEngine;

    /**
     * プライベートコンストラクタ.
     */
    private ScenarioReporterImpl() {
        templateEngine = new TemplateEngine();
        AbstractConfigurableTemplateResolver tr = new ClassLoaderTemplateResolver();
        tr.setCheckExistence(true);
        tr.setTemplateMode(TemplateMode.HTML);
        tr.setPrefix(TEMPLATE_PREFIX);
        tr.setSuffix(TEMPLATE_SUFFIX);
        templateEngine.setTemplateResolver(tr);
    }

    /**
     * インスタンスを取得する.
     *
     * @return
     */
    public static ScenarioReporterImpl getInstance() {
        return instance;
    }

    /**
     * 総合レポートを作成する.
     *
     * @param context コンテキスト
     */
    public void allReport(final Context context, final ExecuteContext executeContext) {

        try {
            org.thymeleaf.context.Context icontext = new org.thymeleaf.context.Context();
            Map<String, Object> variable = new HashMap<>();

            variable.put("executeContext", executeContext);

            // DateTimeUtilsを利用できるように設定
            variable.put("dateTimeUtils", DateTimeUtils.getInstance());

            icontext.setVariables(variable);

            // HTML変換＆出力
            Files.write(ExecutionFileUtils.getAllReportPath(context, executeContext),
                    templateEngine.process("report", icontext).getBytes(TEMPLATE_ENCODING));

            // YAML出力
            //String yamlReport = context.getObjectMapper().writeValueAsString(context.getExecuteContext());
            //Files.write(ExecutionFileUtils.getAllReportYamlPath(context),
            //        yamlReport.getBytes(TEMPLATE_ENCODING));

        } catch (IOException e) {
            throw new SystemException(BaseMessages.BASE_ERR_1001);
        }

    }

    /**
     * シナリオ個別レポートを作成する.
     *
     * @param context         コンテキスト
     * @param executeScenario シナリオ実行情報
     */
    public void scenarioReport(
            final Context context,
            final ExecuteContext executeContext,
            final ExecuteScenario executeScenario) {

        try {
            org.thymeleaf.context.Context icontext = new org.thymeleaf.context.Context();
            Map<String, Object> variable = new HashMap<>();


            for (ExecuteFlow executeFlow : executeScenario.getFlows()) {
                for (ExecuteCommand executeCommand : executeFlow.getCommands()) {
                    for (Field field : executeCommand.getCommand().getClass().getDeclaredFields()) {
                        if (field.getDeclaredAnnotation(OriginalProcessField.class) == null) {
                            if (executeCommand.getExtensionProcessFields() == null) {
                                executeCommand.setExtensionProcessFields(new HashMap<>());
                            }
                            try {
                                // Getterメソッドを参照
                                Method getterMethod = executeCommand.getCommand().getClass().getDeclaredMethod("get" + WordUtils.capitalize(field.getName()), null);
                                // 値抽出
                                Object value = getterMethod.invoke(executeCommand.getCommand());
                                executeCommand.getExtensionProcessFields().put(field.getName(), value);
                            } catch (IllegalAccessException e) {
                                log.debug("error occurred...", e);
                                // Ignore
                            } catch (NoSuchMethodException e) {
                                log.debug("error occurred...", e);
                                // Ignore
                            } catch (InvocationTargetException e) {
                                log.debug("error occurred...", e);
                                // Ignore
                            }
                        }
                    }
                }
            }

            variable.put("activity", generateSvg(executeScenario));

            variable.put("executeScenario", executeScenario);

            // DateTimeUtilsを利用できるように設定
            variable.put("dateTimeUtils", DateTimeUtils.getInstance());

            icontext.setVariables(variable);

            Path scenarioReportPath = ExecutionFileUtils.getScenarioReportPath(context, executeContext, executeScenario);

            // HTML変換＆出力
            Files.write(scenarioReportPath,
                    templateEngine.process("scenario", icontext).getBytes(TEMPLATE_ENCODING));

        } catch (IOException e) {
            throw new SystemException(BaseMessages.BASE_ERR_1002);
        }
    }

    /**
     * 実行Flowのアクティビティ図のSVGを出力する.
     *
     * @param executeScenario シナリオ実行情報
     * @return PlantUMLのアクティビティ図の文字列表現
     */
    private String generateSvg(ExecuteScenario executeScenario) {

        StringBuilder activity = new StringBuilder();
        boolean first = true;
        for (ExecuteFlow executeFlow : executeScenario.getFlows()) {
            if (first) {
                activity.append("(*)-->");
                activity.append(executeFlow.getFlow().getId());
                first = false;
            } else {
                activity.append("-->");
                activity.append(executeFlow.getFlow().getId());
            }
            switch (executeFlow.getStatus()) {
                case WAIT:
                    activity.append("<<WAIT>>");
                    break;
                case SKIP:
                    activity.append("<<SKIP>>");
                    break;
                case SUCCESS:
                    activity.append("<<SUCCESS>>");
                    break;
                case ERROR:
                    activity.append("<<ERROR>>");
                    break;
                case WARN:
                    activity.append("<<WARN>>");
                    break;
            }
            activity.append("\n");
        }
        activity.append("-->(*)\n");

        List<String> templates = null;
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("activity/activity_template.puml")) {
            templates = IOUtils.readLines(is, Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new SystemException(e);
        }

        String activityString = activity.toString();
        StringBuilder result = new StringBuilder();
        for (String row : templates) {
            result.append(row.replace("'$ACTIVITY$", activityString));
            result.append("\n");
        }

        String plantUmlSrc = result.toString();
        SourceStringReader reader = new SourceStringReader(plantUmlSrc);
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            reader.generateImage(os, new FileFormatOption(FileFormat.SVG));
            return new String(os.toByteArray(), Charset.forName("UTF-8"));
        } catch (IOException e) {
            throw new SystemException(e);
        }
    }

}
