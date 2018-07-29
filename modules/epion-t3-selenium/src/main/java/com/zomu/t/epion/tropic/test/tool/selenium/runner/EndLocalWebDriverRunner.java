package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.selenium.command.EndLocalWebDriver;
import com.zomu.t.epion.tropic.test.tool.core.execution.runner.CommandRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.Map;

public class EndLocalWebDriverRunner implements CommandRunner<EndLocalWebDriver> {

    @Override
    public void execute(
            EndLocalWebDriver process,
            Map<String, Object> globalScopeVariables,
            Map<String, Object> scenarioScopeVariables,
            Logger logger) throws Exception {

        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        driver.close();
        driver.quit();
    }
}
