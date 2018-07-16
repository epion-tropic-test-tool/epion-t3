package com.zomu.t.t3.base.execution.reporter;

import com.zomu.t.t3.base.message.BaseMessages;
import com.zomu.t.t3.core.annotation.OriginalProcessField;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.context.execute.ExecuteProcess;
import com.zomu.t.t3.core.context.execute.ExecuteScenario;
import com.zomu.t.t3.core.exception.SystemException;
import com.zomu.t.t3.core.execution.reporter.ScenarioReporter;
import com.zomu.t.t3.core.util.DateTimeUtils;
import com.zomu.t.t3.core.util.ExecutionFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.WordUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Slf4j
public final class BaseScenarioReporter implements ScenarioReporter {

    private static final BaseScenarioReporter instance = new BaseScenarioReporter();

    public static final String TEMPLATE_PREFIX = "/templates/";

    public static final String TEMPLATE_SUFFIX = ".html";

    public static final String TEMPLATE_ENCODING = "UTF-8";

    /**
     * Thymeleafテンプレートエンジン
     */
    private final TemplateEngine templateEngine;

    /**
     * プライベートコンストラクタ.
     */
    private BaseScenarioReporter() {
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
    public static BaseScenarioReporter getInstance() {
        return instance;
    }

    public void allReport(final Context context) {

        try {
            org.thymeleaf.context.Context icontext = new org.thymeleaf.context.Context();
            Map<String, Object> variable = new HashMap<>();

            variable.put("executeContext", context.getExecuteContext());

            // DateTimeUtilsを利用できるように設定
            variable.put("dateTimeUtils", DateTimeUtils.getInstance());

            icontext.setVariables(variable);

            // HTML変換＆出力
            Files.write(ExecutionFileUtils.getAllReportPath(context),
                    templateEngine.process("report", icontext).getBytes(TEMPLATE_ENCODING));

            // YAML出力
            String yamlReport = context.getObjectMapper().writeValueAsString(context.getExecuteContext());
            Files.write(ExecutionFileUtils.getAllReportYamlPath(context),
                    yamlReport.getBytes(TEMPLATE_ENCODING));

        } catch (IOException e) {
            throw new SystemException(BaseMessages.BASE_ERR_9101);
        }

    }

    public void scenarioReport(
            final Context context,
            final ExecuteScenario executeScenario) {

        try {
            org.thymeleaf.context.Context icontext = new org.thymeleaf.context.Context();
            Map<String, Object> variable = new HashMap<>();


            for (ExecuteProcess executeProcess : executeScenario.getProcesses()) {
                for (Field field : executeProcess.getProcess().getClass().getDeclaredFields()) {
                    if (field.getDeclaredAnnotation(OriginalProcessField.class) == null) {
                        if (executeProcess.getExtensionProcessFields() == null) {
                            executeProcess.setExtensionProcessFields(new HashMap<>());
                        }
                        try {
                            // Getterメソッドを参照
                            Method getterMethod = executeProcess.getProcess().getClass().getDeclaredMethod("get" + WordUtils.capitalize(field.getName()), null);
                            // 値抽出
                            Object value = getterMethod.invoke(executeProcess.getProcess());
                            executeProcess.getExtensionProcessFields().put(field.getName(), value);
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


            variable.put("executeScenario", executeScenario);

            // DateTimeUtilsを利用できるように設定
            variable.put("dateTimeUtils", DateTimeUtils.getInstance());

            icontext.setVariables(variable);

            Path scenarioReportPath = ExecutionFileUtils.getScenarioReportPath(context, executeScenario);

            // HTML変換＆出力
            Files.write(scenarioReportPath,
                    templateEngine.process("scenario", icontext).getBytes(TEMPLATE_ENCODING));

        } catch (IOException e) {
            throw new SystemException(BaseMessages.BASE_ERR_9102);
        }
    }


}
