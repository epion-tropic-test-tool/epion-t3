package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverSendKeys;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverSendKeysSpace;
import com.zomu.t.epion.tropic.test.tool.selenium.type.SelectorType;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverSendKeysSpaceRunner implements CommandRunner<WebDriverSendKeysSpace> {
    @Override
    public void execute(WebDriverSendKeysSpace process, Map<String, Object> globalScopeVariables, Map<String, Object> scenarioScopeVariables, Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        WebElement element =
                WebElementUtils.getInstance().findWebElement(driver, process.getSelector(), process.getTarget());

        element.sendKeys(Keys.SPACE);
    }
}
