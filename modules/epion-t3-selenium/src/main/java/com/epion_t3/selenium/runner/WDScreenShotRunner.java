package com.epion_t3.selenium.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.selenium.command.WDScreenShot;
import com.epion_t3.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

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
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(3000))
                .takeScreenshot(driver);
        Path evidence = getEvidencePath("ScreenShot.PNG");
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
