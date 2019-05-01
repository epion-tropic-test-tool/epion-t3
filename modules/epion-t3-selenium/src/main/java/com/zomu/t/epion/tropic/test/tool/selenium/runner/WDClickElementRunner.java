package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDClickElement;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

public class WDClickElementRunner extends AbstractWDCommandRunner<WDClickElement> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(WDClickElement command, Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = getWebDriver(command);

        // 対象のWebElementを取得
        WebElement element =
                findWebElement(driver, command);

        if (element == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9008);
        }

        if (element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        element.click();

        return CommandResult.getSuccess();
    }
}
