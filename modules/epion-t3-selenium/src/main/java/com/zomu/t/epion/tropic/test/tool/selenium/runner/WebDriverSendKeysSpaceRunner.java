package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverSendKeysSpace;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.Map;

/**
 * Selenium-WebDriver
 * スペースキーを指定された場所に入力する.
 *
 * @author takashno
 */
public class WebDriverSendKeysSpaceRunner extends AbstractCommandRunner<WebDriverSendKeysSpace> {
    @Override
    public void execute(
            WebDriverSendKeysSpace process,
            Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        WebElement element =
                WebElementUtils.getInstance().findWebElement(driver, process.getSelector(), process.getTarget());

        element.sendKeys(Keys.SPACE);
    }
}
