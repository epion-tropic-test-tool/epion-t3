package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverScreenShot;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.nio.file.Path;
import java.util.Map;

/**
 *
 */
public class WebDriverScreenShotRunner implements CommandRunner<WebDriverScreenShot> {
    @Override
    public void execute(
            WebDriverScreenShot process,
            Map<String, Object> globalScopeVariables,
            Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {

        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        Screenshot screenshot = new AShot().takeScreenshot(driver);

        getEvidenceDirectory(scenarioScopeVariables);

        Path evidence = getEvidencePath(
                scenarioScopeVariables,
                flowScopeVariables,
                "PNG");

        // 保管したイメージを任意の場所に書き出す(1行)
        ImageIO.write(
                screenshot.getImage(),
                "PNG",
                evidence.toFile());

        // エビデンスを登録
        registEvidence(scenarioScopeVariables, flowScopeVariables, evidences, evidence);
    }
}
