package com.epion_t3.selenium.runner;

import com.epion_t3.core.command.bean.CommandResult;
import com.epion_t3.core.message.MessageManager;
import com.epion_t3.selenium.command.WDSendKeysElement;
import com.epion_t3.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

public class WDSendKeysElementRunner extends AbstractWDCommandRunner<WDSendKeysElement> {
    @Override
    public CommandResult execute(WDSendKeysElement command, Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = getWebDriver(command);

        // 対象のWebElementを取得
        WebElement element =
                findWebElement(driver, command);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        element.sendKeys(command.getValue());

        return CommandResult.getSuccess();
    }
}
