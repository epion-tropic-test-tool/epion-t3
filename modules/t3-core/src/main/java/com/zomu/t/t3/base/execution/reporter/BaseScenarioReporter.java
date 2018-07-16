package com.zomu.t.t3.base.execution.reporter;

import com.zomu.t.t3.base.message.BaseMessages;
import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.context.ExecuteContext;
import com.zomu.t.t3.core.context.execute.ExecuteScenario;
import com.zomu.t.t3.core.exception.SystemException;
import com.zomu.t.t3.core.execution.reporter.ScenarioReporter;
import com.zomu.t.t3.core.util.ExecutionFileUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.AbstractTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 *
 */
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

    @Override
    public void report(Context context) {

        // 総合レポートの出力
        allReport(context);

    }

    public void allReport(final Context context) {

        try {
            org.thymeleaf.context.Context icontext = new org.thymeleaf.context.Context();
            Map<String, Object> variable = new HashMap<>();

            variable.put("executeContext", context.getExecuteContext());

            icontext.setVariables(variable);
            Files.write(ExecutionFileUtils.getAllReportPath(context),
                    templateEngine.process("all", icontext).getBytes(TEMPLATE_ENCODING));
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

            variable.put("executeScenario", executeScenario);

            icontext.setVariables(variable);

            Path scenarioReportPath = ExecutionFileUtils.getScenarioReportPath(context, executeScenario);
            Files.write(scenarioReportPath,
                    templateEngine.process("scenario", icontext).getBytes(TEMPLATE_ENCODING));
        } catch (IOException e) {
            throw new SystemException(BaseMessages.BASE_ERR_9101);
        }
    }


}
