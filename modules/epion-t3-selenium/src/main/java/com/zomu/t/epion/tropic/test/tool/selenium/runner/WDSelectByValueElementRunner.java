package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectByIndexElement;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDSelectByValueElement;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

public class WDSelectByValueElementRunner extends AbstractCommandRunner<WDSelectByValueElement> {
    @Override
    public CommandResult execute(
            final WDSelectByValueElement command,
            final Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());

        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }

        WebElement element =
                WebElementUtils.getInstance().findWebElement(
                        driver, command.getSelector(), command.getTarget());

        Select select = new Select(element);

        if (!element.isDisplayed()) {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
        }

        select.selectByValue(command.getValue());

        return CommandResult.getSuccess();
    }
}
