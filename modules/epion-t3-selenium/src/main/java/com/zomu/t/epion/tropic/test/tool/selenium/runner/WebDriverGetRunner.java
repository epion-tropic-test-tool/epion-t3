package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverGet;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverGetRunner implements CommandRunner<WebDriverGet> {
    @Override
    public void execute(WebDriverGet process, Map<String, Object> globalScopeVariables, Map<String, Object> scenarioScopeVariables, Map<String, EvidenceInfo> evidences, Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        driver.get(process.getTarget());
    }
}
