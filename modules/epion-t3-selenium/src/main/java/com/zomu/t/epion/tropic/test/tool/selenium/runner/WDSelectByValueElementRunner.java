package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectByValueElement;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

public class WDSelectByValueElementRunner extends AbstractWDCommandRunner<WDSelectByValueElement> {
    @Override
    public CommandResult execute(
            final WDSelectByValueElement command,
            final Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = getWebDriver(command);

        // 対象のWebElementを取得
        WebElement element =
                findWebElement(driver, command);

        Select select = new Select(element);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        select.selectByValue(command.getValue());

        return CommandResult.getSuccess();
    }
}
