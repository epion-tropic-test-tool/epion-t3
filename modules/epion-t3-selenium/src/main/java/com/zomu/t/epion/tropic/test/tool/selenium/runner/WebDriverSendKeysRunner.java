package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverSendKeys;
import com.zomu.t.epion.tropic.test.tool.selenium.type.SelectorType;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverSendKeysRunner implements CommandRunner<WebDriverSendKeys> {
    @Override
    public void execute(WebDriverSendKeys process, Map<String, Object> globalScopeVariables, Map<String, Object> scenarioScopeVariables, Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        SelectorType selectorType = SelectorType.valueOfByValue(process.getSelector());

        WebElement element = null;
        switch (selectorType) {

            case ID:
                element = driver.findElement(By.id(process.getTarget()));
                break;
            case CLASS_NAME:
                element = driver.findElement(By.className(process.getTarget()));
                break;
            case CSS_SELECTOR:
                element = driver.findElement(By.cssSelector(process.getTarget()));
                break;
            case NAME:
                element = driver.findElement(By.name(process.getTarget()));
                break;
        }

        element.sendKeys(process.getValue());
    }
}
