package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.exception.SystemException;
import com.zomu.t.epion.tropic.test.tool.core.message.MessageManager;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WDClickElements;
import com.zomu.t.epion.tropic.test.tool.selenium.message.SeleniumMessages;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.List;

@Slf4j
public class WDClickElementsRunner extends AbstractCommandRunner<WDClickElements> {

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(WDClickElements command, Logger logger) throws Exception {

        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());
        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }

        int targetIndex = command.getIndex() == null ? 0 : command.getIndex();

        List<WebElement> elements =
                WebElementUtils.getInstance().findWebElements(driver, command.getSelector(), command.getTarget());

        if (elements == null || elements.isEmpty()) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9008);
        }

        WebElement element = elements.get(targetIndex);

        if (element.isEnabled()) {
            element.click();
        } else {
            logger.warn(MessageManager.getInstance().getMessage(SeleniumMessages.SELENIUM_WRN_2001));
            element.click();
        }

        return CommandResult.getSuccess();
    }
}
