package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverClick;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverSelectRunner extends AbstractCommandRunner<WebDriverClick> {
    @Override
    public void execute(
            final WebDriverClick process,
            final Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        WebElement element =
                WebElementUtils.getInstance().findWebElement(driver, process.getSelector(), process.getTarget());
        if (element.isEnabled()) {
            Select select = new Select(element);
            select.selectByVisibleText(process.getValue());

        }
    }
}
