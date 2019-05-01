package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSendKeysSpaceElement;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
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
public class WDSendKeysSpaceElementRunner extends AbstractWDCommandRunner<WDSendKeysSpaceElement> {
    @Override
    public CommandResult execute(
            WDSendKeysSpaceElement command,
            Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = getWebDriver(command);

        // 対象のWebElementを取得
        WebElement element =
                findWebElement(driver, command);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        element.sendKeys(Keys.SPACE);
        return CommandResult.getSuccess();
    }
}
