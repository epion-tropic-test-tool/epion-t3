package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverClick;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

public class WebDriverClickRunner extends AbstractCommandRunner<WebDriverClick> {
    @Override
    public void execute(WebDriverClick process, Logger logger) throws Exception {
        WebDriver driver =
                WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        WebElement element =
                WebElementUtils.getInstance().findWebElement(driver, process.getSelector(), process.getTarget());
        if (element.isEnabled()) {
            element.click();
        }
    }
}
