package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectByIndexElements;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectByValueElements;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.List;

public class WDSelectByValueElementsRunner extends AbstractCommandRunner<WDSelectByValueElements> {
    @Override
    public CommandResult execute(
            final WDSelectByValueElements command,
            final Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());

        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }

        int targetIndex = command.getElementIndex() == null ? 0 : command.getElementIndex();

        List<WebElement> elements =
                WebElementUtils.getInstance().findWebElements(driver, command.getSelector(), command.getTarget());

        if (elements == null || elements.isEmpty()) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9008);
        }

        WebElement element = elements.get(targetIndex);

        Select select = new Select(element);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        select.selectByValue(command.getValue());

        return CommandResult.getSuccess();
    }
}
