package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSendKeysElements;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;

public class WDSendKeysElementsRunner extends AbstractWDCommandRunner<WDSendKeysElements> {
    @Override
    public CommandResult execute(WDSendKeysElements command, Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = getWebDriver(command);

        // 対象のWebElementsを取得
        List<WebElement> elements = findWebElements(driver, command);

        int targetIndex = command.getElementIndex() == null ? 0 : command.getElementIndex();

        WebElement element = elements.get(targetIndex);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        element.sendKeys(command.getValue());

        return CommandResult.getSuccess();
    }
}
