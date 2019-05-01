package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.bean.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectVisibleTextElements;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.List;

public class WDSelectVisibleTextElementsRunner extends AbstractWDCommandRunner<WDSelectVisibleTextElements> {
    @Override
    public CommandResult execute(
            final WDSelectVisibleTextElements command,
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
        select.selectByVisibleText(command.getValue());

        return CommandResult.getSuccess();
    }
}
