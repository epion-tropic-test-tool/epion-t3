package com.zomu.t.t3.core.util;

import com.zomu.t.t3.core.context.Context;
import com.zomu.t.t3.core.context.execute.ExecuteScenario;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

/**
 *
 */
public final class ExecutionFileUtils {

    /**
     * 結果ディレクトリのフォーマット.
     */
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

    /**
     * エビデンス格納ディレクトリ名.
     */
    public static final String EVIDENCE_DIR_NAME = "evidence";

    private ExecutionFileUtils() {
        // Do Nothing...
    }

    /**
     * @param context
     */
    public static void createResultDirectory(final Context context) {

        Path resultRootPath = null;

        if (StringUtils.isNotEmpty(context.getOption().getResultRootPath())) {
            resultRootPath = Paths.get(context.getOption().getResultRootPath());
        } else {

            resultRootPath = Paths.get(
                    SystemUtils.getUserDir()
                            + File.separator
                            + "result"
                            + File.separator
                            + DTF.format(context.getExecuteContext().getStart()));
        }

        try {
            Files.createDirectories(resultRootPath);
            context.getExecuteContext().setResultRootPath(resultRootPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param context
     */
    public static void createScenarioResultDirectory(final Context context, final ExecuteScenario scenario) {

        try {
            Path resultPath = Paths.get(
                    context.getExecuteContext().getResultRootPath().toFile().getPath()
                            + File.separator
                            + scenario.getInfo().getId()
                            + "_"
                            + DTF.format(scenario.getStart()));

            Files.createDirectories(resultPath);
            scenario.setResultPath(resultPath);

            Path evidencePath = Paths.get(
                    scenario.getResultPath().toFile().getPath()
                            + File.separator
                            + EVIDENCE_DIR_NAME);

            Files.createDirectories(evidencePath);
            scenario.setEvidencePath(evidencePath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path getAllReportPath(Context context) {
        return Paths.get(context.getExecuteContext().getResultRootPath() + File.separator + "report.html");
    }

    public static Path getAllReportYamlPath(Context context) {
        return Paths.get(context.getExecuteContext().getResultRootPath() + File.separator + "report.yaml");
    }

    public static Path getScenarioReportPath(final Context context, final ExecuteScenario scenario) {
        return Paths.get(scenario.getResultPath().toString() + File.separator + "scenario_report.html");
    }
}
