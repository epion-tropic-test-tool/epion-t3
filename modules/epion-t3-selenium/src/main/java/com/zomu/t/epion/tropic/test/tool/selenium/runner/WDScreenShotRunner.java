package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDScreenShot;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.nio.file.Path;

/**
 *
 */
public class WDScreenShotRunner extends AbstractCommandRunner<WDScreenShot> {
    @Override
    public CommandResult execute(
            WDScreenShot command,
            Logger logger) throws Exception {
        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());
        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }
        Screenshot screenshot = new AShot().takeScreenshot(driver);
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
