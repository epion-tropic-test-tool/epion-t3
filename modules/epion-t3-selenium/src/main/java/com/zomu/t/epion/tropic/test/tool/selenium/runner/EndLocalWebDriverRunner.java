package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.EndLocalWebDriver;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.Map;

public class EndLocalWebDriverRunner implements CommandRunner<EndLocalWebDriver> {

    @Override
    public void execute(
            final EndLocalWebDriver process,
            final Map<String, Object> globalScopeVariables,
            final Map<String, Object> scenarioScopeVariables,
            final Map<String, Object> flowScopeVariables,
            final Map<String, EvidenceInfo> evidences,
            Logger logger) throws Exception {

        WebDriver driver = WebDriver.class.cast(globalScopeVariables.get(process.getRefWebDriver()));
        driver.close();
        driver.quit();
    }
}
