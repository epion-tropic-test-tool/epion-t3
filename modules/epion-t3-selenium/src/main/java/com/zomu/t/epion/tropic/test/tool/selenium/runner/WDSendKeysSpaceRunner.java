package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSendKeysSpace;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

/**
 * Selenium-WebDriver
 * スペースキーを指定された場所に入力する.
 *
 * @author takashno
 */
public class WDSendKeysSpaceRunner extends AbstractCommandRunner<WDSendKeysSpace> {
    @Override
    public CommandResult execute(
            WDSendKeysSpace command,
            Logger logger) throws Exception {
        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());
        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }
        WebElement element =
                WebElementUtils.getInstance().findWebElement(driver, command.getSelector(), command.getTarget());

        element.sendKeys(Keys.SPACE);
        return CommandResult.getSuccess();
    }
}
