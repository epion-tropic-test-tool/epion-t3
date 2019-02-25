package com.zomu.t.epion.tropic.test.tool.selenium.runner;

import com.zomu.t.epion.tropic.test.tool.core.command.model.CommandResult;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.impl.AbstractCommandRunner;
import com.zomu.t.epion.tropic.test.tool.core.context.EvidenceInfo;
import com.zomu.t.epion.tropic.test.tool.selenium.command.WebDriverGet;
import com.zomu.t.epion.tropic.test.tool.core.command.runner.CommandRunner;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.util.Map;

public class WebDriverGetRunner extends AbstractCommandRunner<WebDriverGet> {
    @Override
    public CommandResult execute(WebDriverGet process, Logger logger) throws Exception {
        WebDriver driver = WebDriver.class.cast(getGlobalScopeVariables().get(process.getRefWebDriver()));
        driver.get(process.getTarget());
        return CommandResult.getSuccess();
    }
}
