package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectByIndexElements;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.List;

public class WDSelectByIndexElementsRunner extends AbstractWDCommandRunner<WDSelectByIndexElements> {
    @Override
    public CommandResult execute(
            final WDSelectByIndexElements command,
            final Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = getWebDriver(command);

        // 対象のWebElementsを取得
        List<WebElement> elements = findWebElements(driver, command);

        int targetIndex = command.getElementIndex() == null ? 0 : command.getElementIndex();

        WebElement element = elements.get(targetIndex);

        Select select = new Select(element);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        if (command.getIndex() == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9010);
        }

        select.selectByIndex(command.getIndex());

        return CommandResult.getSuccess();
    }
}
