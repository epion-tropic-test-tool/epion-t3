package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverSendKeys;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import com.zomu.t.epion.tropic.test.tool.selenium.util.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverSendKeysRunner implements CommandRunner<WebDriverSendKeys> {
    @Override
    public void execute(WebDriverSendKeys process, Map<String, Object> globalScopeVariables, final Map<String, Object> flowScopeVariables,Map<String, Object> scenarioScopeVariables, Map<String, EvidenceInfo> evidences, Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        WebElement element =
                WebElementUtils.getInstance().findWebElement(driver, process.getSelector(), process.getTarget());

        element.sendKeys(process.getValue());
    }
}
