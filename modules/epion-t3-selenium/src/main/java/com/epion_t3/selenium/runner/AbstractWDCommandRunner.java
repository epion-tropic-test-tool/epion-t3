package com.epion_t3.selenium.runner;

import com.epion_t3.core.command.runner.impl.AbstractCommandRunner;
import com.epion_t3.core.exception.SystemException;
import com.epion_t3.selenium.command.AbstractWDCommand;
import com.epion_t3.selenium.message.SeleniumMessages;
import com.epion_t3.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class AbstractWDCommandRunner<COMMAND extends AbstractWDCommand> extends AbstractCommandRunner<COMMAND> {

    protected WebDriver getWebDriver(COMMAND command) {
        // WebDriverを取得
        WebDriver driver = resolveVariables(command.getRefWebDriver());
        // WebDriverが解決できない場合はエラー
        if (driver == null) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9007, command.getRefWebDriver());
        }
        return driver;
    }

    protected WebElement findWebElement(WebDriver driver, COMMAND command) {
        WebElement element =
                WebElementUtils.getInstance().findWebElement(
                        driver, command.getSelector(), command.getTarget());
        return element;
    }

    protected List<WebElement> findWebElements(WebDriver driver, COMMAND command) {
        List<WebElement> elements =
                WebElementUtils.getInstance().findWebElements(driver, command.getSelector(), command.getTarget());
        if (elements == null || elements.isEmpty()) {
            throw new SystemException(SeleniumMessages.SELENIUM_ERR_9008);
        }
        return elements;
    }

}
