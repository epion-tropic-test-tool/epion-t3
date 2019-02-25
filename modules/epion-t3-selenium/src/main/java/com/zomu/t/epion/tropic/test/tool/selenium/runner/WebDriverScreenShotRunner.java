package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
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
public class WebDriverScreenShotRunner extends AbstractCommandRunner<WebDriverScreenShot> {
    @Override
    public CommandResult execute(
            WebDriverScreenShot process,
            Logger logger) throws Exception {

        WebDriver driver = WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        Screenshot screenshot = new AShot().takeScreenshot(driver);

        getEvidenceDirectory();

        Path evidence = getEvidencePath("PNG");

        // 保管したイメージを任意の場所に書き出す(1行)
        ImageIO.write(
                screenshot.getImage(),
                "PNG",
                evidence.toFile());

        // エビデンスを登録
        registrationFileEvidence(evidence);

        return CommandResult.getSuccess();
    }
}
